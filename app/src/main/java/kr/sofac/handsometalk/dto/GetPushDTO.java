package kr.sofac.handsometalk.dto;

/**
 * Created by Maxim on 13.11.2017.
 */

public class GetPushDTO {

    String user_id;
    String google_key;

    public GetPushDTO(String user_id, String google_key) {
        this.user_id = user_id;
        this.google_key = google_key;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGoogle_key() {
        return google_key;
    }

    public void setGoogle_key(String google_key) {
        this.google_key = google_key;
    }

    @Override
    public String toString() {
        return "GetPushDTO{" +
                "user_id='" + user_id + '\'' +
                ", google_key='" + google_key + '\'' +
                '}';
    }
}
