package org.arvesen.oauth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.arvesen.oauth.circlekid.dto.OAuthClientDTO;
import org.arvesen.oauth.circlekid.dto.OAuthClientDTOImpl;
import org.arvesen.oauth.circlekid.dto.OAuthClientSecrets;
import org.arvesen.oauth.circlekid.dto.UserDTO;
import org.arvesen.oauth.circlekid.dto.UserDTOImpl;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

public class App {
    private static final String LEGAL_HOSTNAME = "legalHostname";
    private static final String INITIAL_CONTEXT = "initialContextURL";
    private static final String BASE_USER_NEW_ENDPOINT_URL = "/api/v1/unres/user/new";
    private static final String BASE_OAUTH_CLIENT_ENDPOINT_URL = "/api/v1/res/oauth/oauth_clients";
    private static final String REGISTER_NEW_OAUTH_CLIENT_URL = "/api/v1/res/oauth/oauth_clients/new";
    private static final String BASE_LOGIN_URL = "/api/v1/unres/login";

    private static InitializeContext context;
    private static Logger logger = LoggerFactory.getLogger(App.class);
    private String sessionCookie;
    private static final String developerEmail = "test24@gmail.com";

    public static void main(String[] vars) throws Exception {

        System.out.println("Reading yaml configuration...");
        //readYamlConfiguration("instructions.yml");
        System.out.println("Legal hostname: " + System.getProperty(LEGAL_HOSTNAME));
        // Example of a URL that will give us an initial context
        // http://localhost:8080/api/v1/unres/oauth-client-initialization
        System.out.println("Initial context URL: " + System.getProperty(INITIAL_CONTEXT));
        createObjectMapper();

        App app = new App();
        app.start();

    }

    private void start() throws Exception {
        final Optional<UserDTO> developer = Optional.of(registerDeveloper(developerEmail));

        if ( !developer.isPresent() ) {
            logger.warn("Did not manage to register developer.. exiting");
            return;
        }

        final UserDTO loggedInDeveloper = loginUser(developer.get());
        final OAuthClientDTO client = developerRegistersOAuthClient(loggedInDeveloper);

        final Optional<UserDTO> regularUser = Optional.of(registerUser("user5@gmail.com"));

    }

    private OAuthClientDTO developerRegistersOAuthClient(final UserDTO developerUser) throws UnirestException {
        System.out.println("Registering oAuth Client");

        OAuthClientSecrets secrets = getOAuthClientSecrets(developerUser);

        OAuthClientDTO oAuthClient = new OAuthClientDTOImpl();
        oAuthClient.setName("Party App 2");
        oAuthClient.setDescription("Cool demo app");
        oAuthClient.setDeveloperId(developerUser.getUUID().toString());
        oAuthClient.setPublicClientId(secrets.getOAuthPublicId());
        oAuthClient.setSecretKey(secrets.getOAuthSecretKey());

        confirmRegisterOAuthClient(oAuthClient);
        return oAuthClient;
    }

    private static OAuthClientSecrets getOAuthClientSecrets(UserDTO developerUser) throws UnirestException {
        final HttpResponse<JsonNode> json = Unirest.get(compileURL(BASE_OAUTH_CLIENT_ENDPOINT_URL + '/' + developerUser.getUUID()))
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson();

        OAuthClientSecrets secrets = new OAuthClientSecrets();
        JSONObject object = json.getBody().getObject();
        secrets.setOAuthSecretKey(object.get("secretToken").toString());
        secrets.setOAuthPublicId(object.get("publicClientId").toString());
        return secrets;
    }

    private void confirmRegisterOAuthClient(OAuthClientDTO oAuthClient) throws UnirestException {
        String jsonData = new Gson().toJson(oAuthClient);
        HttpResponse<JsonNode> response = Unirest.post(compileURL(REGISTER_NEW_OAUTH_CLIENT_URL))
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(jsonData)
                .asJson();
        System.out.println(response);
    }

    private static UserDTO registerUser(final String email) throws URISyntaxException, UnirestException {
        final UserDTO newUser = new UserDTOImpl();
        newUser.setEmail(email);
        newUser.setFirstName("Developer");
        newUser.setLastName("l33t");
        newUser.setPassword("super_secret_yai!");
        newUser.setPhoneNumber("40601122");
        newUser.setCountryCode("47");
        final String jsonData = new Gson().toJson(newUser);

        HttpResponse<JsonNode> registerUserResponse = Unirest.post(compileURL(BASE_USER_NEW_ENDPOINT_URL))
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(jsonData)
                .asJson();
        logger.debug("registerUserResponse: " + registerUserResponse);

        if ( registerUserResponse.getStatus() == 200 ) {
            return newUser;
        }

        return null;
    }

    private static String compileURL(String s) {
        return "http://localhost:8080" + s;
    }

    private static UserDTO registerDeveloper(String email) throws URISyntaxException, UnirestException {

        final UserDTO developerUser = registerUser(email);

        // Register user as a developer
        // Missing step
        return developerUser;
    }

    private static void readYamlConfiguration(String s) throws URISyntaxException {
        YAMLParser parser;
        final YAMLFactory factory = new YAMLFactory();
        File file = new File(ClassLoader.getSystemResource("instructions.yml").toURI());
        StringBuilder configuration = new StringBuilder();

        //JsonParser parser = factory.createJsonParser(); // don't be fooled by method name...
        try {
            parser = factory.createParser(file);
            while (parser.nextToken() != null ) {
                System.out.println("" + parser.getCurrentName());
                //System.out.println("" + parser.getCurrent);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.debug(configuration.toString());
    }

    private UserDTO loginUser(UserDTO user) throws UnirestException {
        UserDTO loggedInUser = null;
        final String jsonData = new Gson().toJson(user.getLoginDTO());
        HttpResponse<JsonNode> loginResponse = Unirest.post(compileURL(BASE_LOGIN_URL))
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(jsonData)
                .asJson();
        if ( loginResponse.getStatus() == 200 ) {
            loggedInUser = new Gson().fromJson(loginResponse.getBody().toString(), UserDTOImpl.class);
            sessionCookie = loginResponse.getHeaders().get("set-cookie").get(0);
            loggedInUser.setSessionCookie(sessionCookie);
        }

        return loggedInUser;
    }

    private static void createObjectMapper() {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
