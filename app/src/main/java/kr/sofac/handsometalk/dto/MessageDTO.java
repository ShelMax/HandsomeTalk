package kr.sofac.handsometalk.dto;

import java.util.Locale;

/**
 * Created by Maxim on 13.11.2017.
 */

public class MessageDTO {

    private String id;
    private String estimate_id;
    private String user_id;
    private String manager_id;
    private String message;
    private String date;
    private String type;
    private String user_name;
    private String kakao_id;
    private String manager_name;

    public MessageDTO(String id, String estimate_id, String user_id, String manager_id, String message, String date, String type, String user_name, String kakao_id, String manager_name) {
        this.id = id;
        this.estimate_id = estimate_id;
        this.user_id = user_id;
        this.manager_id = manager_id;
        this.message = message;
        this.date = date;
        this.type = type;
        this.user_name = user_name;
        this.kakao_id = kakao_id;
        this.manager_name = manager_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstimate_id() {
        return estimate_id;
    }

    public void setEstimate_id(String estimate_id) {
        this.estimate_id = estimate_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getManager_id() {
        return manager_id;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

    public String getMessage() {
        return message.toLowerCase(Locale.ENGLISH);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getKakao_id() {
        return kakao_id;
    }

    public void setKakao_id(String kakao_id) {
        this.kakao_id = kakao_id;
    }

    public String getManager_name() {
        return manager_name;
    }

    public void setManager_name(String manager_name) {
        this.manager_name = manager_name;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "id='" + id + '\'' +
                ", estimate_id='" + estimate_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", manager_id='" + manager_id + '\'' +
                ", message='" + message + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", user_name='" + user_name + '\'' +
                ", kakao_id='" + kakao_id + '\'' +
                ", manager_name='" + manager_name + '\'' +
                '}';
    }
}
