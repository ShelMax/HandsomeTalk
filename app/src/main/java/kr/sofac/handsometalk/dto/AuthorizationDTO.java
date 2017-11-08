package kr.sofac.handsometalk.dto;

/**
 * Created by Maxim on 08.11.2017.
 */

public class AuthorizationDTO {
    String password;
    String login;
    String googleCloudKey;

    public AuthorizationDTO(String password, String login, String googleCloudKey) {
        this.password = password;
        this.login = login;
        this.googleCloudKey = googleCloudKey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getGoogleCloudKey() {
        return googleCloudKey;
    }

    public void setGoogleCloudKey(String googleCloudKey) {
        this.googleCloudKey = googleCloudKey;
    }

    @Override
    public String toString() {
        return "AuthorizationDTO{" +
                "password='" + password + '\'' +
                ", login='" + login + '\'' +
                ", googleCloudKey='" + googleCloudKey + '\'' +
                '}';
    }

}

//{"dataTransferObject":{"password":"1234", "login":"mr_jeka@bk.ru", "googleCloudKey":"1234"}, "requestType":"authorizationUser"}