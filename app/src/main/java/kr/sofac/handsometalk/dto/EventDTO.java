package kr.sofac.handsometalk.dto;

import java.util.ArrayList;

/**
 * Created by Maxim on 09.11.2017.
 */

public class EventDTO {

    String id;
    String name;
    String body;
    String visible;
    String date;
    ArrayList<ImageDTO> images;
    ImageDTO image;

    public EventDTO(String id, String name, String body, String visible, String date, ArrayList<ImageDTO> images, ImageDTO image) {
        this.id = id;
        this.name = name;
        this.body = body;
        this.visible = visible;
        this.date = date;
        this.images = images;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<ImageDTO> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageDTO> images) {
        this.images = images;
    }

    public ImageDTO getImage() {
        return image;
    }

    public void setImage(ImageDTO image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "EventDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", body='" + body + '\'' +
                ", visible='" + visible + '\'' +
                ", date='" + date + '\'' +
                ", images=" + images +
                ", image=" + image +
                '}';
    }


}

//{
//        "responseStatus": "SERVER_RESPONSE_SUCCESS",
//        "dataTransferObject": [
//        {
//        "id": "2",
//        "name": "에그라인 얼굴주사 8만원 / 18만원(VAT별도)",
//        "body": "<p>에그라인 얼굴주사 8만원 / 18만원(VAT별도)</p>\r\n",
//        "visible": "1",
//        "date": "2017-11-01 17:03:53",
//        "images": [
//        {
//        "id": "6",
//        "item_id": "2",
//        "type": "event",
//        "filename": "image_1509204716847_500.jpg",
//        "position": "6"
//        },
//        {
//        "id": "7",
//        "item_id": "2",
//        "type": "event",
//        "filename": "image_1509287752180.jpg",
//        "position": "7"
//        }
//        ],
//        "image": {
//        "id": "6",
//        "item_id": "2",
//        "type": "event",
//        "filename": "image_1509204716847_500.jpg",
//        "position": "6"
//        }
//        },
//        {
//        "id": "1",
//        "name": "LBL 탄력프로그램 42만원/45만원/49만원 (VAT별도)",
//        "body": "<p>LBL 탄력프로그램 42만원/45만원/49만원 (VAT별도)</p>\r\n",
//        "visible": "1",
//        "date": "2017-11-01 16:00:00",
//        "images": [
//        {
//        "id": "4",
//        "item_id": "1",
//        "type": "event",
//        "filename": "image_1509205147150.jpg",
//        "position": "0"
//        },
//        {
//        "id": "5",
//        "item_id": "1",
//        "type": "event",
//        "filename": "image_1509287726827.jpg",
//        "position": "1"
//        }
//        ],
//        "image": {
//        "id": "4",
//        "item_id": "1",
//        "type": "event",
//        "filename": "image_1509205147150.jpg",
//        "position": "0"
//        }
//        }
//        ]
//        }