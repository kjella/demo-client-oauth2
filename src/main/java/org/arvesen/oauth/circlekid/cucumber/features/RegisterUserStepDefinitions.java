package org.arvesen.oauth.circlekid.cucumber.features;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;
import org.apache.commons.lang3.StringUtils;
import org.arvesen.oauth.circlekid.dto.UserDTO;
import org.arvesen.oauth.circlekid.dto.UserDTOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;

/**
 * Created by kjella on 27/07/16.
 */

@ScenarioScoped
public class RegisterUserStepDefinitions {
    private static final String BASE_USER_NEW_ENDPOINT_URL = "/api/v1/unres/user/new";
    private static final Logger logger = LoggerFactory.getLogger(RegisterUserStepDefinitions.class);
    private String emailToCheck;
    private UserDTO storedUser;

    public RegisterUserStepDefinitions() {

    }

    @When("^I register my user details with (.*)$")
    public void i_register_my_user_details_with_email(final String email) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Registering a user with email: " + email);
        this.emailToCheck = email;
        this.storedUser = registerUser(email);

    }

    @Then("^I should be able to return user with the same email$")
    public void i_should_be_able_to_return_user_with_email() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("I should be able to return a user with the email: " + emailToCheck);
        if (StringUtils.equals(storedUser.getEmail(), emailToCheck)) {
            System.out.println("Yeah!!!");
        } else {
            System.out.println("Fuck it!");
        }
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
}

