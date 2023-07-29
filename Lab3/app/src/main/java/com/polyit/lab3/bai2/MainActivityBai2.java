package com.polyit.lab3.bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.polyit.lab3.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivityBai2 extends AppCompatActivity {
  private String urlJsonObj = "http://172.16.53.70:8686/Lab3/person_object.json";
  private String urlJsonArry = "http://172.16.53.70:8686/Lab3/person_array.json";

  private static String TAG = MainActivityBai2.class.getSimpleName();
  private Button btnMakeObjectRequest, btnMakeArrayRequest;

  private ProgressDialog pDialog;
  private TextView tvResponse;
  private String jsonResponse;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_bai2);
    btnMakeObjectRequest = (Button) findViewById(R.id.btnObjRequest);
    btnMakeArrayRequest = (Button) findViewById(R.id.btnArrRequest);
    tvResponse = (TextView) findViewById(R.id.tvResponse);

    pDialog = new ProgressDialog(this);
    pDialog.setMessage("Please wait...");
    pDialog.setCancelable(false);

    btnMakeObjectRequest.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        makeJsonObjectRequest();
      }
    });
    btnMakeArrayRequest.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        makeJsonArrayRequest();
      }
    });
  }
  private void makeJsonArrayRequest() {
    showDialog();

    JsonArrayRequest arrayRequest = new JsonArrayRequest(urlJsonArry, new Response.Listener<JSONArray>() {
      @Override
      public void onResponse(JSONArray jsonArray) {
        Log.d(TAG, jsonArray.toString());
        try {
          jsonResponse = "";
          for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject person = (JSONObject) jsonArray.get(i);
            String name = person.getString("name");
            String email = person.getString("email");

            JSONObject phone = person.getJSONObject("phone");
            String home = phone.getString("home");
            String mobile = phone.getString("mobile");

            jsonResponse += "Name: " + name + "\n\n";
            jsonResponse += "Email: " + email + "\n\n";
            jsonResponse += "Home: " + home + "\n\n";
            jsonResponse += "Mobile: " + mobile + "\n\n";
          }
          tvResponse.setText(jsonResponse);
        } catch (Exception e) {
          e.printStackTrace();
          Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        hideDialog();
      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError volleyError) {
        VolleyLog.d(TAG,"Error: "+ volleyError.getMessage());
        Toast.makeText(getApplicationContext(),volleyError.getMessage(), Toast.LENGTH_SHORT).show();
        hideDialog();
      }
    });
    AppController.getInstance().addToRequestQueue(arrayRequest);
  }


  private void makeJsonObjectRequest() {
    showDialog();
    JsonObjectRequest jsonObjReq = new
            JsonObjectRequest(Request.Method.GET, urlJsonObj,
            null, new Response.Listener<JSONObject>() {
      @Override
      public void onResponse(JSONObject jsonObject) {
        Log.d(TAG, jsonObject.toString());
        try {
          // Parsing json object response
// response will be a json object
          String name = jsonObject.getString("name");
          String email = jsonObject.getString("email");
          JSONObject phone = jsonObject.getJSONObject("phone");
          String home = phone.getString("home");
          String mobile = phone.getString("mobile");
          jsonResponse = "";
          jsonResponse += "Name: " + name + "\n\n";
          jsonResponse += "Email: " + email + "\n\n";
          jsonResponse += "Home: " + home + "\n\n";
          jsonResponse += "Phone: " + mobile + "\n\n";
          tvResponse.setText(jsonResponse);
        } catch (Exception e) {
          e.printStackTrace();
          Toast.makeText(getApplicationContext(), "Error: " +
                  e.getMessage(), Toast.LENGTH_LONG).show();
        }
        hideDialog();
      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError volleyError) {
        VolleyLog.d(TAG, "Error: " + volleyError.getMessage());
        Toast.makeText(getApplicationContext(), "Error: " +
                volleyError.getMessage(), Toast.LENGTH_LONG).show();
        hideDialog();
      }
    });
    AppController.getInstance().addToRequestQueue(jsonObjReq);

  }

  private void showDialog() {
    if (!pDialog.isShowing()) {
      pDialog.show();
    }
  }

    private void hideDialog() {
    if (pDialog.isShowing()) {
      pDialog.dismiss();
    }
  }


}