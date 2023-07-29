package com.polyit.lab3.Bai3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.polyit.lab3.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityBai3 extends AppCompatActivity implements View.OnClickListener {
  Button btnGetAll;
  TextView tvGetAll;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_bai3);

    btnGetAll = findViewById(R.id.btnGetAll);
    tvGetAll = findViewById(R.id.tvGetAll);
    btnGetAll.setOnClickListener(this);

  }
  @Override
  public void onClick(View v) {
    loadAll();
  }
  String str = "";
  List<AndroidVersion> data;

  public void loadAll(){
    //tao doi tuong retrofit
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://172.16.53.70:8686/Lab3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    //lay request
    RequestInterface interface1 = retrofit.create(RequestInterface.class);
    Call<JSONResponse> call = interface1.getJSON();
    //thuc thi
    call.enqueue(new Callback<JSONResponse>() {
      @Override
      public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
        JSONResponse jsonResponse = response.body();
        data = new ArrayList<>(Arrays.asList(jsonResponse.getContacts()));
        for(AndroidVersion adr: data){
          str += "Id: " + adr.getId() + "\n\n";
          str += "Name: " + adr.getName() + "\n\n";
          str += "Email: " + adr.getEmail() + "\n\n";
          str += "-------------\n\n\"";
        }
        tvGetAll.setText(str);
      }

      @Override
      public void onFailure(Call<JSONResponse> call, Throwable t) {
        Log.d("Error",t.getMessage());
      }
    });
  }
}