package nee.firecard.com.retrofitdemo1.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 03-Jun-17.
 */

public class ImageResponse {

    boolean success;
    @SerializedName("message")
    String message;

    @SerializedName("title")
    String title;

    @SerializedName("id")
    String id;

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




    public boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
