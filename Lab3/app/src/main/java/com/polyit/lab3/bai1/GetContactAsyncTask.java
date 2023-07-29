package com.polyit.lab3.bai1;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetContactAsyncTask extends AsyncTask<Void,Void,Void> {
  private String TAG = MainActivityBai1.class.getSimpleName();

  public static String url = "http://172.16.53.70:8686/Lab3/index.php";

  ArrayList<Contact> list;

  private ProgressDialog pDialog;
  private ListView listView;
  Context context;
  ContactAdapter adapter;

  public GetContactAsyncTask(Context context, ListView listView) {
    this.listView = listView;
    this.context = context;
    list = new ArrayList<>();
  }
  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    pDialog = new ProgressDialog(context);
    pDialog.setMessage("Please wait...");
    pDialog.setCancelable(false);
    pDialog.show();
  }

  @Override
  protected Void doInBackground(Void... voids) {
    HttpHandler handler = new HttpHandler();
    // making request to url and getting response
    String jsonStr = handler.makeServiceCall(url);
    Log.e(TAG, "Response from url: " + jsonStr);
    if (jsonStr != null) {
      try {
        JSONObject jsonObject = new JSONObject(jsonStr);
        // Getting JSON Array node
        JSONArray contacts = jsonObject.getJSONArray("contacts");
        // looping through all Contacts
        for (int i = 0; i < contacts.length(); i++) {
          JSONObject c = contacts.getJSONObject(i);
          String id = c.getString("id");
          String name = c.getString("name");
          String email = c.getString("email");
          String address = c.getString("address");
          String gender = c.getString("gender");
          // Phone node is JSON Object
          JSONObject phone = c.getJSONObject("phone");
          String mobile = phone.getString("mobile");
          String home = phone.getString("home");
          String office = phone.getString("office");
          Contact contact = new Contact();
          contact.setId(id);
          contact.setName(name);
          contact.setEmail(email);
          contact.setMobile(mobile);
          list.add(contact);
        }
      } catch (Exception e) {
        e.printStackTrace();
        Log.e(TAG, "Json parsing error: " + e.getMessage());
      }
    } else {
      Log.e(TAG, "Couldn't get json from server.");
    }
    return null;
  }
  @Override
  protected void onPostExecute(Void aVoid) {
    super.onPostExecute(aVoid);
    if (pDialog.isShowing()) {
      pDialog.dismiss();
    }
    adapter = new ContactAdapter(context, list);
    listView.setAdapter(adapter);
  }
}
