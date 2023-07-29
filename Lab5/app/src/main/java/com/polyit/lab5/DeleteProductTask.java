package com.polyit.lab5;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class DeleteProductTask extends AsyncTask<String, String, String> {
  Context context;
  ProgressDialog pDialog;
  JSONParser jsonParser;
  boolean checkSuccess = false;
  public DeleteProductTask(Context context) {
    this.context = context;
    jsonParser = new JSONParser();
  }
  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    pDialog = new ProgressDialog(context);
    pDialog.setMessage("Deleting product...");
    pDialog.setCancelable(false);
    pDialog.show();
  }
  @Override
  protected String doInBackground(String... strings) {
    try {
      // Building Parameters
      List<HashMap<String, String>> params = new ArrayList<>();
      HashMap<String, String> hsPid = new HashMap<>();
      hsPid.put(Constants.TAG_PID, strings[0]);
      params.add(hsPid);
      // getting product detail by making HTTP request
      JSONObject object =
              jsonParser.makeHttpRequest(Constants.url_delete_product,"POST",params);
      // check your log for json response
      Log.d("Delete product",object.toString());
      // json success tag
      int success = object.getInt(Constants.TAG_SUCCESS);
      if(success == 1){
        // product successfully delete
        checkSuccess = true;
      }else{
        checkSuccess = false;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  @Override
  protected void onPostExecute(String s) {
    super.onPostExecute(s);
    if(pDialog.isShowing()){
      pDialog.dismiss();
    }
    if(checkSuccess){
      // notify previous activity by sending code 100
      Intent intent = ((Activity)context).getIntent();
      // send result code 100 to notify about product deletion
      ((Activity) context).setResult(100,intent);
      ((Activity) context).finish();
    }
  }
}