package nee.firecard.com.retrofitdemo1.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 24-Jun-17.
 */

public class ImageUpdate {

    boolean success;

    @SerializedName("message")
    String message;


    public boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    @SerializedName("title")
    String title;

    @SerializedName("id")
    String id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
