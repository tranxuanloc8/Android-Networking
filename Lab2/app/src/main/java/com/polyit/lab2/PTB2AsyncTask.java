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

public class PTB2AsyncTask extends AsyncTask<String,Void,String> {
  private Context context;
  private String link, a, b, c;
  private String kq = "";
  private TextView tvResult;

  public PTB2AsyncTask(Context context, String link, String a, String b, String c, TextView tvResult) {
    this.context = context;
    this.link = link;
    this.a = a;
    this.b = b;
    this.c = c;
    this.tvResult = tvResult;
  }

  @Override
  protected String doInBackground(String... strings) {

    try {
      URL url = new URL(link);
      String ts ="a="+URLEncoder.encode(a,"utf-8")+
              "&b="+URLEncoder.encode(b,"utf-8")+
              "&c="+URLEncoder.encode(c,"utf-8");
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
