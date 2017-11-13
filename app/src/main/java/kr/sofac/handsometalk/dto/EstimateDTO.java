package kr.sofac.handsometalk.dto;

import java.util.ArrayList;

/**
 * Created by Maxim on 13.11.2017.
 */

public class EstimateDTO {

    private String id;
    private String user_id;
    private String sku;
    private String date;
    private String name;
    private String kakao_id;
    private String message_date;
    private MessageDTO message;
    private ArrayList<MessageDTO> messages;

    public EstimateDTO(String id, String user_id, String sku, String date, String name, String kakao_id, String message_date, MessageDTO message, ArrayList<MessageDTO> messages) {
        this.id = id;
        this.user_id = user_id;
        this.sku = sku;
        this.date = date;
        this.name = name;
        this.kakao_id = kakao_id;
        this.message_date = message_date;
        this.message = message;
        this.messages = messages;
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getMessage_date() {
        return message_date;
    }

    public void setMessage_date(String message_date) {
        this.message_date = message_date;
    }

    public MessageDTO getMessage() {
        return message;
    }

    public void setMessage(MessageDTO message) {
        this.message = message;
    }

    public ArrayList<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<MessageDTO> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "EstimateDTO{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", sku='" + sku + '\'' +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", kakao_id='" + kakao_id + '\'' +
                ", message_date='" + message_date + '\'' +
                ", message=" + message +
                ", messages=" + messages +
                '}';
    }
}
