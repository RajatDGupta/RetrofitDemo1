package nee.firecard.com.retrofitdemo1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Base64OutputStream;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import nee.firecard.com.retrofitdemo1.Model.Student;
import nee.firecard.com.retrofitdemo1.Service.ServiceAPI;
import nee.firecard.com.retrofitdemo1.network.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Insert extends AppCompatActivity {


    EditText et_name, et_email, et_username, et_password, et_title;
    Button insert, fatch, next, image_page, web,choose,getImage,spinnerwork,login;
    TextView textView, textView1, textView2, textView3;
    RadioGroup radioGroup;
    RadioButton selectedGender;
    Spinner spinner;
    private ProgressDialog pDialog;
    private static  final int IMG_REQUEST=777;
    private Bitmap bitmap;
    ImageView imageView;

     /*  a*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);


        et_name = (EditText) findViewById(R.id.et_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        et_title=(EditText) findViewById(R.id.edit_title);


        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        textView = (TextView) findViewById(R.id.textView);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);

        insert = (Button) findViewById(R.id.button);
        fatch = (Button) findViewById(R.id.button2);
        next = (Button) findViewById(R.id.bt_next_page);
        image_page = (Button) findViewById(R.id.bt_next_image_upload);
        choose=(Button) findViewById(R.id.chooseBtn);
        web = (Button) findViewById(R.id.webView);
        getImage=(Button) findViewById(R.id.my_image);
        spinnerwork=(Button)findViewById(R.id.spinner_work);
        login=(Button)findViewById(R.id.login);


        imageView=(ImageView)findViewById(R.id.imageView);

        spinner = (Spinner) findViewById(R.id.sp_country);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setStudentDetails();

            }
        });

        fatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStudentData();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Insert.this, RecylerViewList.class);
                startActivity(i);
            }
        });

        image_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(Insert.this, Image_Uploade.class);
                startActivity(j);
            }
        });


        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(Insert.this, MyView.class);
                startActivity(j);
            }
        });

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

       getImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent j = new Intent(Insert.this, Image_Fatch.class);
               startActivity(j);
           }
       });

        spinnerwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(Insert.this, SpinnerWork.class);
                startActivity(j);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(Insert.this, Login.class);
                startActivity(j);
            }
        });

    }


    public void getStudentData() {


        ServiceAPI service = ApiClient.getRetrofit().create(ServiceAPI.class);
        Call<List<Student>> call = service.getStudentData();

        call.enqueue(new Callback<List<Student>>() {

            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                List<Student> studentdata = response.body();

                String details_name = "";
                String details_email = "";
                String details_gender = "";
                String details_country = "";

                for (int i = 0; i < studentdata.size(); i++) {

                    String name = studentdata.get(i).getName();
                    String email = studentdata.get(i).getEmail();
                    String gender = studentdata.get(i).getGender();
                    String country = studentdata.get(i).getCountry();

                    details_name += "Name: " + name + "\n";
                    details_email += "Email: " + email + "\n";
                    details_gender += "Gender: " + gender + "\n";
                    details_country += "country: " + country + "\n";
                }
                textView.setText(details_name);
                textView1.setText(details_email);
                textView2.setText(details_gender);
                textView3.setText(details_country);
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Toast.makeText(Insert.this, "fail", Toast.LENGTH_LONG).show();
            }
        });

    }


    public void setStudentDetails() {

        showpDialog();
        String email, name, username, password, country, gender;

        int selectedGenderId = radioGroup.getCheckedRadioButtonId();
        selectedGender = (RadioButton) findViewById(selectedGenderId);


        ServiceAPI service = ApiClient.getRetrofit().create(ServiceAPI.class);
        Student student = new Student();

        email = et_email.getText().toString();
        name = et_name.getText().toString();
        username = et_username.getText().toString();
        password = et_password.getText().toString();
        gender = selectedGender.getText().toString();
        country = spinner.getSelectedItem().toString();




        student.setEmail(email);
        student.setName(name);
        student.setUsername(username);
        student.setPassword(password);
        student.setGender(gender);
        student.setCountry(country);


        Call<Student> studentCalls = service.setStudentDetails(student.getName(), student.getEmail(),
                student.getUsername(), student.getPassword(), student.getGender(), student.getCountry());

        studentCalls.enqueue(new Callback<Student>() {

            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                Toast.makeText(Insert.this, "success", Toast.LENGTH_SHORT).show();
                hidepDialog();

            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {

                Log.d("onFailure", t.toString());
                Toast.makeText(Insert.this, "fail", Toast.LENGTH_LONG).show();
                hidepDialog();
            }

        });
    }


    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void selectImage()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQUEST);
    }




}
