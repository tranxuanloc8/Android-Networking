package com.polyit.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Bai2 extends AppCompatActivity implements View.OnClickListener {
  TextView tvResult;
  EditText edtDai, edtRong;
  String link;
  Button btnLoad;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bai2);

    tvResult = findViewById(R.id.tvResult);
    edtDai = findViewById(R.id.edtDai);
    edtRong = findViewById(R.id.edtRong);
    btnLoad = findViewById(R.id.btnLoad);
    btnLoad.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    link = "http://172.16.50.31:8686/android/hinhCN_POST.php";
    HinhcnAsyncTask asyncTask = new HinhcnAsyncTask(this,
            link,edtDai.getText().toString(),edtRong.getText().toString(),tvResult);
    asyncTask.execute();
  }
}