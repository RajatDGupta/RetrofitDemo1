package nee.firecard.com.retrofitdemo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import nee.firecard.com.retrofitdemo1.Model.Medical_Registration;
import nee.firecard.com.retrofitdemo1.Service.ServiceAPI;
import nee.firecard.com.retrofitdemo1.network.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText email,pass;
    Button login;
    Medical_Registration mr;
    Intent intent;
    String userid, userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=(EditText)findViewById(R.id.email);
        pass=(EditText) findViewById(R.id.pass);
        login=(Button)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        ServiceAPI service = ApiClient.getRetrofit().create(ServiceAPI.class);

        String getEmail = email.getText().toString();
        String getPass = pass.getText().toString();

        mr.setUser_email(getEmail);
        mr.setUser_pwd(getPass);

        Call<List<Medical_Registration>> call = service.getLogin(mr.getUser_email(), mr.getUser_pwd());
        call.enqueue(new Callback<List<Medical_Registration>>() {
            @Override
            public void onResponse(Call<List<Medical_Registration>> call, Response<List<Medical_Registration>> response) {
                List<Medical_Registration> res = response.body();

                if (response.isSuccessful()) {

                    for (int i = 0; i < res.size(); i++) {
                        userid = res.get(i).getUser_id();
                        userEmail = res.get(i).getUser_email();
                    }

                    intent = new Intent(Login.this, Insert.class);
                    startActivity(intent);
                }
                Toast.makeText(Login.this, "succ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Medical_Registration>> call, Throwable t) {
                Toast.makeText(Login.this, "fail", Toast.LENGTH_LONG).show();
            }
        });

    }
}
