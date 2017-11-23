package kr.sofac.handsometalk.dto;

/**
 * Created by Maxim on 08.11.2017.
 */

public class RegistrationDTO {
    private String kakao_id;
    private String password;
    private String name;
    private Boolean sex;
    private String phone;
    private String email;
    private String googleCloudKey;

    public RegistrationDTO(String password, String name, Boolean sex, String phone, String email, String googleCloudKey) {
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.googleCloudKey = googleCloudKey;
    }

    public RegistrationDTO(String kakao_id,String googleCloudKey) {
        this.googleCloudKey = googleCloudKey;
        this.kakao_id = kakao_id;
    }

    public RegistrationDTO(String kakao_id, String name, String phone, String email, String googleCloudKey) {
        this.kakao_id = kakao_id;
        this.name = name;
        this.phone = phone;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        return "RegistrationDTO{" +
                "kakao_id='" + kakao_id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", googleCloudKey='" + googleCloudKey + '\'' +
                '}';
    }
}

//{"dataTransferObject":{"password":"1234", "name":"Evgeniy Murenko", "sex":"1", "phone":"+380939091062", "email":"mr_jeka@bk.ru", "googleCloudKey":"123"}, "requestType":"registrationUser"}