package kr.sofac.handsometalk.dto;

/**
 * Created by Maxim on 13.11.2017.
 */

public class GetEstimationsDTO {

    String user_id;

    public GetEstimationsDTO(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "GetEstimationDTO{" +
                "user_id='" + user_id + '\'' +
                '}';
    }
}
