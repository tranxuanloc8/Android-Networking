package com.polyit.lab3.bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.polyit.lab3.R;

public class MainActivityBai1 extends AppCompatActivity {
  ListView lvContact;
  GetContactAsyncTask getContactAsyncTask;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_bai1);

    lvContact = (ListView)findViewById(R.id.lvContact);

    getContactAsyncTask = new GetContactAsyncTask(this,lvContact);
    getContactAsyncTask.execute();
  }
}