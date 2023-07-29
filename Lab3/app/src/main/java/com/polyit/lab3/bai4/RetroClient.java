package com.polyit.lab3.bai4;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetroClient {
  private static final String ROOT_URL = "http://172.16.53.70:8686/Lab3/";

  private static Retrofit getRetrofitInstance() {
    return new Retrofit.Builder()
            .baseUrl(ROOT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
  }

  public static ApiService getApiService() {
    return getRetrofitInstance().create(ApiService.class);
  }
}
