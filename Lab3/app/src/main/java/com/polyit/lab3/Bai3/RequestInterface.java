package com.polyit.lab3.Bai3;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {
  @GET("json_data.json")
  Call<JSONResponse> getJSON();
}
