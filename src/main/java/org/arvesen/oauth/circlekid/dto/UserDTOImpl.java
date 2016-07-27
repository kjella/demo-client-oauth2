package org.arvesen.oauth.circlekid.dto;

import java.util.UUID;

/**
 * Created by kjella on 04/04/16.
 */
public class UserDTOImpl implements UserDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String countryCode;
    private UUID uuid;

    private String sessionCookie;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(final String areaCode) {
        this.countryCode = areaCode;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public String getSessionCookie() {
        return sessionCookie;
    }

    public void setSessionCookie(String sessionCookie) {
        this.sessionCookie = sessionCookie;
    }


    @Override
    public UserLoginDTO getLoginDTO() {
        UserLoginDTO loginDTO = new UserLoginDTOImpl();
        loginDTO.setEmail(this.getEmail());
        loginDTO.setPassword(this.getPassword());
        return loginDTO;
    }
}
