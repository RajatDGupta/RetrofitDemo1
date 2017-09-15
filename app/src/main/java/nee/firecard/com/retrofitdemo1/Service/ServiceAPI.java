package nee.firecard.com.retrofitdemo1.Service;

import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import nee.firecard.com.retrofitdemo1.Image_Uploade;
import nee.firecard.com.retrofitdemo1.Model.FatchImageResponse;
import nee.firecard.com.retrofitdemo1.Model.ImageResponse;
import nee.firecard.com.retrofitdemo1.Model.ImageUpdate;
import nee.firecard.com.retrofitdemo1.Model.Medical_Registration;
import nee.firecard.com.retrofitdemo1.Model.Student;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


/**
 * Created by hp on 12-May-17.
 */

public interface ServiceAPI {


    @GET("neeraj/select.php")
    Call<List<Student>> getStudentData();

    @FormUrlEncoded
    @POST("neeraj/insert.php")
    Call<Student> setStudentDetails(@Field("name") String name,
                                    @Field("email") String email,
                                    @Field("username") String username,
                                    @Field("password") String password,
                                    @Field("gender") String gender,
                                    @Field("country") String country
                                    );

    @Multipart
    @POST("neeraj/image_uploade.php")
    Call<ImageResponse> uploadFile(@Part MultipartBody.Part file, @Part("title") RequestBody title);

//, @Part("image") RequestBody image

    @GET("neeraj/get_image.php")
    Call<ArrayList<FatchImageResponse>> getImageData();

    @FormUrlEncoded
    @POST("neeraj/delete.php")
    Call<FatchImageResponse> delete_card(@Field("id") String id);

    @Multipart
    @POST("neeraj/image_uploade_update.php")
    Call<ImageUpdate> update_uploade_file(@Part MultipartBody.Part file, @Part("title") RequestBody title, @Part("id") RequestBody id,@Part("image") RequestBody image);

    @FormUrlEncoded
    @POST("Medical/medical_login.php")
    Call<List<Medical_Registration>> getLogin(@Field("user_email") String user_email,
                                              @Field("user_pwd") String user_pwd);


}

