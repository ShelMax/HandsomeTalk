package kr.sofac.handsometalk.dto;

/**
 * Created by Maxim on 13.11.2017.
 */

public class GetListMessageDTO {

    String estimate_id;

    public GetListMessageDTO(String estimate_id) {
        this.estimate_id = estimate_id;
    }

    public String getEstimate_id() {
        return estimate_id;
    }

    public void setEstimate_id(String estimate_id) {
        this.estimate_id = estimate_id;
    }

    @Override
    public String toString() {
        return "GetListMessageDTO{" +
                "estimate_id='" + estimate_id + '\'' +
                '}';
    }
}
