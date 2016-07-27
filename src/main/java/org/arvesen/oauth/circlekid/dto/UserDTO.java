package org.arvesen.oauth.circlekid.dto;

import java.util.UUID;

/**
 * Created by kjella on 18/07/16.
 */
public interface UserDTO {
    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getPhoneNumber();

    void setPhoneNumber(String phoneNumber);

    String getCountryCode();

    void setCountryCode(String areaCode);

    UserLoginDTO getLoginDTO();

    UUID getUUID();

    String getSessionCookie();
    void setSessionCookie(String sessionCookie);
}
