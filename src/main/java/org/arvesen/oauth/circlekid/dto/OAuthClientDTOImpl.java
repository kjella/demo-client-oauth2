package org.arvesen.oauth.circlekid.dto;

/**
 * Created by kjella on 19/07/16.
 */
public class OAuthClientDTOImpl implements OAuthClientDTO {
    private String name;
    private String description;
    private String publicClientId;
    private String secretKey;
    private String developerId;
    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getPublicClientId() {
        return publicClientId;
    }

    public void setPublicClientId(String publicClientId) {
        this.publicClientId = publicClientId;
    }

    @Override
    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String getDeveloperId() {
        return developerId;
    }

    @Override
    public void setDeveloperId(String developerId) {
        this.developerId = developerId;
    }

}
