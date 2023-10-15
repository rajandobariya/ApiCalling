package com.example.apicalling.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.apicalling.R;
import com.example.apicalling.adapter.ResponseAdapter;
import com.example.apicalling.databinding.ActivityMainBinding;
import com.example.apicalling.modal.Image;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ResponseAdapter responseAdapter;
    ArrayList<Image> imageArrayList = new ArrayList<>();
    String url = "https://lexica.art/api/infinite-prompts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerview.setLayoutManager(new GridLayoutManager(this, 3));
        binding.recyclerview.setHasFixedSize(true);
        responseAdapter = new ResponseAdapter(this, imageArrayList);
        binding.recyclerview.setAdapter(responseAdapter);

        getApiCall();

    }

    public void getApiCall() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                com.example.apicalling.modal.Response data = gson.fromJson(String.valueOf(response), com.example.apicalling.modal.Response.class);
                imageArrayList.addAll(Arrays.asList(data.image));
                responseAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}