package nee.firecard.com.retrofitdemo1.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hp on 02-Jun-17.
 */

public class ApiClient {

    public static final String BASE_URL = "http://neerajgupta.netne.net/";
   // public static final String BASE_URL ="http://sanket21.host22.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }}
