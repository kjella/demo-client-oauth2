package org.arvesen.oauth.circlekid.dto;

/**
 * Created by kjella on 19/07/16.
 */
public class OAuthClientSecrets {
    // {"oauth_secret_key":"3303afb921e177d3e4b322adbb4d28d8834086786c1ad301714a326ac66a5485","oauth_public_id":"b6c326624820cca33254c40916a50e38693635f19841d50b9dd2d0cb9f405a71"}
    private String OAuthSecretKey;
    private String OAuthPublicId;

    public String getOAuthSecretKey() {
        return OAuthSecretKey;
    }

    public void setOAuthSecretKey(String OAuthSecretKey) {
        this.OAuthSecretKey = OAuthSecretKey;
    }

    public String getOAuthPublicId() {
        return OAuthPublicId;
    }

    public void setOAuthPublicId(String OAuthPublicId) {
        this.OAuthPublicId = OAuthPublicId;
    }
}
