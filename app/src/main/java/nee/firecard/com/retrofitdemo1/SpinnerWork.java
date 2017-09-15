package nee.firecard.com.retrofitdemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import nee.firecard.com.retrofitdemo1.Model.Student;
import nee.firecard.com.retrofitdemo1.Service.ServiceAPI;
import nee.firecard.com.retrofitdemo1.network.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpinnerWork extends AppCompatActivity {
    Spinner spinner_state, spinner_country, spinner_city;
    AutoCompleteTextView autoCompleteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_work);

        spinner_country = (Spinner) findViewById(R.id.sp_country);
        spinner_state = (Spinner) findViewById(R.id.sp_state);
        spinner_city = (Spinner) findViewById(R.id.sp_city);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        getStudentData();

    }

    public void getStudentData() {


        final ServiceAPI service = ApiClient.getRetrofit().create(ServiceAPI.class);
        Call<List<Student>> call = service.getStudentData();

        call.enqueue(new Callback<List<Student>>() {

            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                List<Student> studentdata = response.body();

                String[] nameList = new String[studentdata.size()];

                for (int i = 0; i < studentdata.size(); i++) {
                    nameList[i] = studentdata.get(i).getName(); //create array of name
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(SpinnerWork.this, android.R.layout.simple_spinner_dropdown_item, nameList);
                //drop down layout style - list view with radio button
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //attaching data adapter to spinner
                spinner_country.setAdapter(dataAdapter);

                ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(SpinnerWork.this, android.R.layout.simple_list_item_1, nameList);
                autoCompleteTextView.setAdapter(dataAdapter1);

                String p = "pooja";


//                if (!p.equals(null)) {
//                    int spinnerPosition = dataAdapter.getPosition(p);
//                    spinner_country.setSelection(spinnerPosition);
//                }

                // String[] tokens = str.split(",", -1);

            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                // Toast.makeText(Insert.this, "fail", Toast.LENGTH_LONG).show();
            }
        });

    }
}
