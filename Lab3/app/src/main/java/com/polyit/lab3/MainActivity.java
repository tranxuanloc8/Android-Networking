package com.polyit.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.polyit.lab3.Bai3.MainActivityBai3;
import com.polyit.lab3.bai1.MainActivityBai1;
import com.polyit.lab3.bai2.MainActivityBai2;
import com.polyit.lab3.bai4.MainActivityBai4;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void bai1(View view) {
    startActivity(new Intent(this, MainActivityBai1.class));
  }
  public void bai2(View view) {
    startActivity(new Intent(this, MainActivityBai2.class));
  }
  public void bai3(View view) {
    startActivity(new Intent(this, MainActivityBai3.class));
  }
  public void bai4(View view) {
    startActivity(new Intent(this, MainActivityBai4.class));
  }
}