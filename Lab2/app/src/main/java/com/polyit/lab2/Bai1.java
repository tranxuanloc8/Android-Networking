package com.polyit.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Bai1 extends AppCompatActivity implements View.OnClickListener {
  TextView tvResult;
  EditText edtName, edtScore;
  String link;
  Button btnLoad;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bai1);
    link = "http://172.16.50.31:8686/android/student_GET.php";
    tvResult = findViewById(R.id.tvResult);
    edtName = findViewById(R.id.edtName);
    edtScore = findViewById(R.id.edtScore);
    btnLoad = findViewById(R.id.btnLoad);
    btnLoad.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    StudentAsyncTask asyncTask = new StudentAsyncTask(this,
            link,edtName.getText().toString(),edtScore.getText().toString(),tvResult);
    asyncTask.execute();
  }
}