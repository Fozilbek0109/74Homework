package uz.coder.a74homework;

import static androidx.recyclerview.widget.RecyclerView.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import uz.coder.a74homework.adapter.ImgAdapter;
import uz.coder.a74homework.databinding.ActivityMainBinding;
import uz.coder.a74homework.model.ImgModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private RequestQueue requestQueue;
    private List<ImgModel> imgModelList;
    private ImgAdapter imgAdapter;
    private static final String TAG = "MainActivity";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadData();
        requestQueue = Volley.newRequestQueue(this);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://jsonplaceholder.typicode.com/photos", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<ImgModel>>() {
                    }.getType();
                     imgModelList = gson.fromJson(response.toString(), type);
                    Log.d(TAG, "onResponse: "+imgModelList);
                     imgAdapter = new ImgAdapter(imgModelList);
                     binding.rv.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));

                     binding.rv.setAdapter(imgAdapter);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+ error.getMessage());
            }

        });
        requestQueue.add(jsonArrayRequest);
    }

    private void loadData() {
        imgModelList = new ArrayList<>();
    }
}