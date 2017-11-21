package kr.sofac.handsometalk.dto;

/**
 * Created by Maxim on 21.11.2017.
 */

public class FeedbackDTO {

    String id;
    String user_id;
    String date;
    String status;
    String comment;
    String name;
    String kakao_id;
    String phone;

    public FeedbackDTO(String id, String user_id, String date, String status, String comment, String name, String kakao_id, String phone) {
        this.id = id;
        this.user_id = user_id;
        this.date = date;
        this.status = status;
        this.comment = comment;
        this.name = name;
        this.kakao_id = kakao_id;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKakao_id() {
        return kakao_id;
    }

    public void setKakao_id(String kakao_id) {
        this.kakao_id = kakao_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "FeedbackDTO{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                ", comment='" + comment + '\'' +
                ", name='" + name + '\'' +
                ", kakao_id='" + kakao_id + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
