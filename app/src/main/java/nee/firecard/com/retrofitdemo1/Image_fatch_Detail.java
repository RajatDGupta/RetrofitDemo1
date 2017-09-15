package nee.firecard.com.retrofitdemo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;

import nee.firecard.com.retrofitdemo1.Model.FatchImageResponse;
import nee.firecard.com.retrofitdemo1.Model.ImageResponse;
import nee.firecard.com.retrofitdemo1.Service.ServiceAPI;
import nee.firecard.com.retrofitdemo1.network.ApiClient;
import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Image_fatch_Detail extends AppCompatActivity
{
    GifImageView imageView;
    ImageView imageView1,imageView_edit;
    TextView  textView;
    String id1;
    String thumbnail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_fatch__detail);


        imageView=(GifImageView)findViewById(R.id.imageView);
        imageView1=(ImageView)findViewById(R.id.delete) ;
        textView=(TextView)findViewById(R.id.textView);
        imageView_edit=(ImageView)findViewById(R.id.upadte) ;


        textView.setText(getIntent().getStringExtra("title"));

        thumbnail=getIntent().getExtras().getString("image");

        id1=getIntent().getExtras().getString("id");

        Glide.with(this).load(thumbnail).placeholder(R.drawable.bye).into(imageView);

        imageView1.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View v)
           {
               delete_card();

               Intent intent=new Intent(getApplicationContext(),Image_Fatch.class);
               startActivity(intent);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
           }

       });

        imageView_edit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), UpdateCard.class);
                intent.putExtra("id",id1);
                intent.putExtra("image",thumbnail);
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }



    private void delete_card()
    {
        ServiceAPI service = ApiClient.getRetrofit().create(ServiceAPI.class);
        FatchImageResponse fatch_image = new FatchImageResponse();

        fatch_image.setId(id1);

        Call<FatchImageResponse> call=service.delete_card(fatch_image.getId());
        call.enqueue(new Callback<FatchImageResponse>()
        {
            @Override
            public void onResponse(Call<FatchImageResponse> call, Response<FatchImageResponse> response) {

                Toast.makeText(Image_fatch_Detail.this, "success", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<FatchImageResponse> call, Throwable t) {
                Toast.makeText(Image_fatch_Detail.this, "fail", Toast.LENGTH_SHORT).show();
            }

        });

    }
}
