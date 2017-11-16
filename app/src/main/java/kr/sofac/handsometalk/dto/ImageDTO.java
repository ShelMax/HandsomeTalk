package kr.sofac.handsometalk.dto;

import java.util.Locale;

/**
 * Created by Maxim on 09.11.2017.
 */

public class ImageDTO {
    String id;
    String item_id;
    String type;
    String filename;
    String position;

    public ImageDTO(String id, String item_id, String type, String filename, String position) {
        this.id = id;
        this.item_id = item_id;
        this.type = type;
        this.filename = filename;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilename() {
        return filename.toLowerCase(Locale.ENGLISH);
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "ImageDTO{" +
                "id='" + id + '\'' +
                ", item_id='" + item_id + '\'' +
                ", type='" + type + '\'' +
                ", filename='" + filename + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
