package kr.sofac.handsometalk.dto;

/**
 * Created by Maxim on 14.11.2017.
 */

public class NewEstimateRequestDTO {

    String user_id;
    String message;

    public NewEstimateRequestDTO(String user_id, String message) {
        this.user_id = user_id;
        this.message = message;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "NewEstimateRequestDTO{" +
                "user_id='" + user_id + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
