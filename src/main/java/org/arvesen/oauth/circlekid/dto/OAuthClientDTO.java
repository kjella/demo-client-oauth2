package org.arvesen.oauth.circlekid.dto;

/**
 * Created by kjella on 19/07/16.
 */
public interface OAuthClientDTO {
    String getName();
    void setName(String name);
    String getDescription();
    void setDescription(String description);
    String getPublicClientId();
    void setPublicClientId(String clientId);
    String getSecretKey();
    void setSecretKey(String secretKey);
    String getDeveloperId();
    void setDeveloperId(String developerId);
}
