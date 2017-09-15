package nee.firecard.com.retrofitdemo1.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 19-Jun-17.
 */

public class FatchImageResponse {
    @SerializedName("image")
    String image;

    @SerializedName("title")
    String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("id")
    String id;

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}
