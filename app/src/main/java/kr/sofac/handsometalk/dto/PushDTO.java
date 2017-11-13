package kr.sofac.handsometalk.dto;

/**
 * Created by Maxim on 13.11.2017.
 */

public class PushDTO {
    String id;
    String title;
    String body;
    String date;
    String google_key_id;

    public PushDTO(String id, String title, String body, String date, String google_key_id) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.date = date;
        this.google_key_id = google_key_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGoogle_key_id() {
        return google_key_id;
    }

    public void setGoogle_key_id(String google_key_id) {
        this.google_key_id = google_key_id;
    }

    @Override
    public String toString() {
        return "PushDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", date='" + date + '\'' +
                ", google_key_id='" + google_key_id + '\'' +
                '}';
    }
}
