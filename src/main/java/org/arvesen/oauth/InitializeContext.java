package org.arvesen.oauth;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by kjella on 31/03/16.
 */
public class InitializeContext {

    private String oAutchClientName;
    private String serverAuthorizeTokenURL;
    private String serverAccessTokenURL;
    private String oAuthClientAuthorizeCallbackURL;

    public String getoAutchClientName() {
        return oAutchClientName;
    }

    public void setoAutchClientName(String oAutchClientName) {
        this.oAutchClientName = oAutchClientName;
    }

    public String getServerAuthorizeTokenURL() {
        return serverAuthorizeTokenURL;
    }

    public void setServerAuthorizeTokenURL(String serverAuthorizeTokenURL) {
        this.serverAuthorizeTokenURL = serverAuthorizeTokenURL;
    }

    public String getServerAccessTokenURL() {
        return serverAccessTokenURL;
    }

    public void setServerAccessTokenURL(String serverAccessTokenURL) {
        this.serverAccessTokenURL = serverAccessTokenURL;
    }

    public String getoAuthClientAuthorizeCallbackURL() {
        if (StringUtils.isEmpty(oAuthClientAuthorizeCallbackURL)) {
            return "/callback";
        }
        return oAuthClientAuthorizeCallbackURL;
    }

    public void setoAuthClientAuthorizeCallbackURL(String oAuthClientAuthorizeCallbackURL) {
        this.oAuthClientAuthorizeCallbackURL = oAuthClientAuthorizeCallbackURL;
    }
}

