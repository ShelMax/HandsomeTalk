package kr.sofac.handsometalk.dto;

/**
 * Created by Maxim on 08.11.2017.
 */

public class AuthorizationDTO {
    String kakao_id;
    String password;
    String email;
    String googleCloudKey;

    public AuthorizationDTO(String kakao_id, String password, String email, String googleCloudKey) {
        this.kakao_id = kakao_id;
        this.password = password;
        this.email = email;
        this.googleCloudKey = googleCloudKey;
    }

    public AuthorizationDTO(String kakao_id, String googleCloudKey) {
        this.kakao_id = kakao_id;
        this.googleCloudKey = googleCloudKey;
    }

    public AuthorizationDTO(String password, String email, String googleCloudKey) {
        this.password = password;
        this.email = email;
        this.googleCloudKey = googleCloudKey;
    }

    public String getKakao_id() {
        return kakao_id;
    }

    public void setKakao_id(String kakao_id) {
        this.kakao_id = kakao_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                "kakao_id='" + kakao_id + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", googleCloudKey='" + googleCloudKey + '\'' +
                '}';
    }
}

//{"dataTransferObject":{"password":"1234", "login":"mr_jeka@bk.ru", "googleCloudKey":"1234"}, "requestType":"authorizationUser"}