package nee.firecard.com.retrofitdemo1;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nee.firecard.com.retrofitdemo1.Model.FatchImageResponse;
import nee.firecard.com.retrofitdemo1.Model.Student;
import nee.firecard.com.retrofitdemo1.Service.ServiceAPI;
import nee.firecard.com.retrofitdemo1.network.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Image_Fatch extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<FatchImageResponse> image_fatch_adapters;
    private Image_Fatch_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_fatch);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        getImageData();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(this, Image_Fatch.class);
        startActivity(intent);
        finish();

    }

    @Override
     public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, Insert.class);
        startActivity(intent);
    }


    public void getImageData() {
        ServiceAPI service = ApiClient.getRetrofit().create(ServiceAPI.class);
        Call<ArrayList<FatchImageResponse>> call = service.getImageData();
        call.enqueue(new Callback<ArrayList<FatchImageResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<FatchImageResponse>> call, Response<ArrayList<FatchImageResponse>> response) {

                image_fatch_adapters = response.body();

                adapter = new Image_Fatch_Adapter(image_fatch_adapters, Image_Fatch.this);
                recyclerView.setAdapter(adapter);
                Toast.makeText(Image_Fatch.this, "success", Toast.LENGTH_LONG).show();

            }


            @Override
            public void onFailure(Call<ArrayList<FatchImageResponse>> call, Throwable t) {
                Toast.makeText(Image_Fatch.this, "fail", Toast.LENGTH_LONG).show();
            }
        });


    }


}
