package nee.firecard.com.retrofitdemo1;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nee.firecard.com.retrofitdemo1.Model.Student;
import nee.firecard.com.retrofitdemo1.Service.ServiceAPI;
import nee.firecard.com.retrofitdemo1.network.ApiClient;
import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecylerViewList extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private LinearLayoutManager layoutManager;
    List<Student> userList;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    Toolbar toolbar;
    GifImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler_view_list);

        imageView=(GifImageView)findViewById(R.id.toolbarImage);

        getUserList();

         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    private void getUserList() {
        try {


            ServiceAPI service = ApiClient.getRetrofit().create(ServiceAPI.class);
            Call<List<Student>> call = service.getStudentData();

            call.enqueue(new Callback<List<Student>>() {
                @Override
                public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {

                    userList = response.body();
                    layoutManager = new LinearLayoutManager(RecylerViewList.this);
                    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerViewAdapter = new RecyclerViewAdapter(userList);
                    recyclerView.setAdapter(recyclerViewAdapter);

                    Toast.makeText(RecylerViewList.this, "Succsses", Toast.LENGTH_SHORT).show();
                    toolbar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onFailure(Call<List<Student>> call, Throwable t) {

                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        List<Student> newList = new ArrayList<>();
        for (Student student : userList) {
            String name = student.getName().toLowerCase();
            String email = student.getEmail().toLowerCase();
            String username = student.getUsername().toLowerCase();
            String gender = student.getGender().toLowerCase();
            String contry = student.getCountry().toLowerCase();

            if (name.contains(newText) || email.contains(newText) || username.contains(newText) || gender.contains(newText) || contry.contains(newText))
                newList.add(student);

        }
        recyclerViewAdapter.setFilter(newList);
        return true;
    }
}
