package nee.firecard.com.retrofitdemo1.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 12-May-17.
 */

public class Student {

    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("gender")
    private String gender;
    @SerializedName("country")
    private String country;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
