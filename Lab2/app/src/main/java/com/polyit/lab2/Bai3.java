package com.polyit.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Bai3 extends AppCompatActivity implements View.OnClickListener {
  TextView tvResult;
  EditText edtCanh;
  String link;
  Button btnLoad;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bai3);


    tvResult = findViewById(R.id.tvResult);
    edtCanh = findViewById(R.id.edtCanh);;
    btnLoad = findViewById(R.id.btnLoad);
    btnLoad.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    link = "http://172.16.50.31:8686/android/hinhLP_POST.php";
    HinhLPAsyncTask asyncTask = new HinhLPAsyncTask(this,
            link,edtCanh.getText().toString(),tvResult);
    asyncTask.execute();
  }
}