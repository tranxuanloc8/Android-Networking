package com.polyit.lab3.bai1;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHandler {
  private static final String TAG = HttpHandler.class.getSimpleName();

  public HttpHandler(){

  }
  public String makeServiceCall(String requrl){
    String response = null;
    try {
      URL url = new URL(requrl);
      HttpURLConnection urlConnection= (HttpURLConnection)url.openConnection();
      InputStream in = new BufferedInputStream(urlConnection.getInputStream());
      response = convertStreamToString(in);
    } catch (Exception e) {
      e.printStackTrace();
      Log.e(TAG,"Excaption: " + e.getMessage());
    }
    return response;
  }
  private String convertStreamToString(InputStream is){
    BufferedReader read = new BufferedReader(new InputStreamReader(is));
    StringBuilder sb = new StringBuilder();
    String line;
    try{
      while ((line = read.readLine()) != null){
        sb.append(line).append("\n");
      }
    }catch (Exception e){
      e.printStackTrace();
    }finally {
      try{
        is.close();
      }catch (Exception ex){
        ex.printStackTrace();
      }
    }
    return sb.toString();
  }
}
