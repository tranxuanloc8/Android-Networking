package com.polyit.lab2;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HinhLPAsyncTask extends AsyncTask<String,Void,String> {
  private Context context;
  private String link, canh;
  private String kq = "";
  private TextView tvResult;

  public HinhLPAsyncTask(Context context, String link, String canh, TextView tvResult) {
    this.context = context;
    this.link = link;
    this.canh = canh;
    this.tvResult = tvResult;
  }

  @Override
  protected String doInBackground(String... strings) {

    try {
      URL url = new URL(link);
      String ts ="canh="+URLEncoder.encode(canh,"utf-8");
      HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
      urlConnection.setDoOutput(true);
      urlConnection.setRequestMethod("POST");
      urlConnection.setFixedLengthStreamingMode(ts.getBytes().length);
      urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
      PrintWriter p = new PrintWriter(urlConnection.getOutputStream());
      p.print(ts);
      p.close();

      String line = "";
      BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
      StringBuffer sb = new StringBuffer();
      while((line = br.readLine()) != null){
        sb.append(line);
      }
      kq = sb.toString();
      urlConnection.disconnect();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  protected void onPostExecute(String s) {
    super.onPostExecute(s);
    tvResult.setText(kq);
  }

}
