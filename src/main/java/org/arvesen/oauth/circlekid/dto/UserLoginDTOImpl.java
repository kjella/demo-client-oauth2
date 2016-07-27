package org.arvesen.oauth.circlekid.dto;

import org.arvesen.oauth.circlekid.dto.UserLoginDTO;

/**
 * Created by kjella on 18/07/16.
 */
public class UserLoginDTOImpl implements UserLoginDTO {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override

    public void setEmail(final String email) {
        this.email = email;
    }

    @Override
    public void setPassword(final String password) {
        this.password = password;
    }
}
