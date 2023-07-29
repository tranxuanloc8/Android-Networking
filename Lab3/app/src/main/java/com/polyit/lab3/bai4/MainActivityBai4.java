package com.polyit.lab3.bai4;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.polyit.lab3.R;
import android.app.ProgressDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MainActivityBai4 extends AppCompatActivity {

  private ListView listView;
  private View parentView;
  private ArrayList<Contact> contactList;
  private MyContactAdapter adapter;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_bai4);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);
    contactList = new ArrayList<>();
    parentView = findViewById(R.id.parentLayout);
    listView = (ListView) findViewById(R.id.listview);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Snackbar.make(parentView, contactList.get(position).getName() + " => " + contactList.get(position).getPhone().getHome(), Snackbar.LENGTH_LONG).show();
      }
    });
    Toast toast = Toast.makeText(getApplicationContext(), R.string.string_click_to_load, Toast.LENGTH_LONG);
    toast.setGravity(Gravity.CENTER, 0, 0);
    toast.show();
    FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
    assert fab != null;
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if
        (InternetConnection.checkConnection(getApplicationContext())) {
          final ProgressDialog dialog;
          dialog = new ProgressDialog(MainActivityBai4.this);

          dialog.setTitle(getString(R.string.string_getting_json_title));

          dialog.setMessage(getString(R.string.string_getting_json_message));
          dialog.show();
          ApiService api = RetroClient.getApiService();
          Call<ContactList> call = api.getMyJSON();
          call.enqueue(new Callback<ContactList>() {
            @Override
            public void onResponse(Call<ContactList> call, Response<ContactList> response) {
              dialog.dismiss();
              if(response.isSuccessful()) {
                contactList = response.body().getContacts();
                adapter = new MyContactAdapter(MainActivityBai4.this, contactList);
                listView.setAdapter(adapter);
              } else {
                Snackbar.make(parentView, R.string.string_some_thing_wrong, Snackbar.LENGTH_LONG).show();
              }
            }
            @Override
            public void onFailure(Call<ContactList> call, Throwable t) {
              dialog.dismiss();
            }
          });
        } else {
          Snackbar.make(parentView, R.string.string_internet_connection_not_available,Snackbar.LENGTH_LONG).show();
        }
      }
    });
  }
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}

