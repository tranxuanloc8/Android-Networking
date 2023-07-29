package com.polyit.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Bai4 extends AppCompatActivity implements View.OnClickListener {
  TextView tvResult;
  EditText edtA,edtB,edtC;
  String link;
  Button btnLoad;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bai4);


    tvResult = findViewById(R.id.tvResult);
    edtA = findViewById(R.id.edtA);;
    edtB = findViewById(R.id.edtB);;
    edtC = findViewById(R.id.edtC);;
    btnLoad = findViewById(R.id.btnLoad);
    btnLoad.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    link = "http://172.16.50.31:8686/android/giaiPTB2_POST.php";
    PTB2AsyncTask asyncTask = new PTB2AsyncTask(this,
            link,edtA.getText().toString(),edtB.getText().toString(),edtC.getText().toString(),tvResult);
    asyncTask.execute();
  }
}