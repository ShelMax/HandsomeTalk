package kr.sofac.handsometalk.dto;

/**
 * Created by Maxim on 08.11.2017.
 */

public class UserDTO {
    String id;
    String email;
    String password;
    String name;
    String phone;
    Boolean sex; // true - male
    String avatar;
    String date;
    Boolean visible; //
    String access;
    String kakao_id;

    public UserDTO(String id, String email, String password, String name, String phone, Boolean sex, String avatar, String date, Boolean visible, String access, String kakao_id) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.sex = sex;
        this.avatar = avatar;
        this.date = date;
        this.visible = visible;
        this.access = access;
        this.kakao_id = kakao_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getKakao_id() {
        return kakao_id;
    }

    public void setKakao_id(String kakao_id) {
        this.kakao_id = kakao_id;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", sex=" + sex +
                ", avatar='" + avatar + '\'' +
                ", date='" + date + '\'' +
                ", visible=" + visible +
                ", access='" + access + '\'' +
                ", kakao_id='" + kakao_id + '\'' +
                '}';
    }
}

/*
{
        "responseStatus": "SERVER_RESPONSE_SUCCESS",
        "dataTransferObject": {
        "id": "1",
        "email": "mr_jeka@bk.ru",
        "password": "1234",
        "name": "Evgeniy Murenko",
        "phone": "+380939091062",
        "sex": "1",
        "avatar": null,
        "date": "2017-11-01 10:20:30",
        "visible": "1",
        "access": "message;event",
        "kakao_id": null
        }
        }
*/
