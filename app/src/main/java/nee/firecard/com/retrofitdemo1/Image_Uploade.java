package nee.firecard.com.retrofitdemo1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import nee.firecard.com.retrofitdemo1.Model.ImageResponse;
import nee.firecard.com.retrofitdemo1.Service.ServiceAPI;
import nee.firecard.com.retrofitdemo1.network.ApiClient;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Image_Uploade extends AppCompatActivity {
    Button btnUpload, btnPickImage, next_page;
    String mediaPath;
    GifImageView imgView;
    ProgressDialog progressDialog;
    EditText img_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_uploade);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");

        btnUpload = (Button) findViewById(R.id.upload);
        btnPickImage = (Button) findViewById(R.id.pick_img);
        next_page = (Button) findViewById(R.id.next_page);


        imgView = (GifImageView) findViewById(R.id.preview);

        img_title = (EditText) findViewById(R.id.img_name);


        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });

        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);
            }
        });

        next_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(Image_Uploade.this, Image_Fatch.class);
                startActivity(j);
                j.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {

                // Get the Image from data
                Uri selectedImage = data.getData();

                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);
                // Set the Image in ImageView for Previewing the Media

                imgView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                imgView.setVisibility(View.VISIBLE);
                img_title.setVisibility(View.VISIBLE);
                btnPickImage.setEnabled(false);
                btnUpload.setEnabled(true);

                cursor.close();

            } else {
                Toast.makeText(this, "You haven't picked Image/Video", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

    }


    // Uploading Image/Video
    private void uploadFile() {
        progressDialog.show();

        String Image_title = img_title.getText().toString().trim();

        // Map is used to multipart the file using okhttp3.RequestBody
        File file = new File(mediaPath);

        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
       // RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        RequestBody title = RequestBody.create(MediaType.parse("text/plain"), Image_title);



        ServiceAPI getResponse = ApiClient.getRetrofit().create(ServiceAPI.class);
        Call<ImageResponse> call = getResponse.uploadFile(fileToUpload,title);

        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {

                ImageResponse serverResponse = response.body();
                if (serverResponse != null) {
                    if (serverResponse.getSuccess()) {
                        Toast.makeText(getApplicationContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    assert serverResponse != null;
                    Log.v("Response", serverResponse.toString());
                }
                progressDialog.dismiss();

                imgView.setVisibility(View.GONE);
                img_title.setVisibility(View.GONE);
                btnPickImage.setEnabled(true);
                btnUpload.setEnabled(false);
            }

            @Override
              public void onFailure(Call<ImageResponse> call, Throwable t) {

            }
        });
    }

}
