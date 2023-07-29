package com.polyit.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity  {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }
  public void bai1(View view){
    startActivity(new Intent(this,Bai1.class));
  }
  public void bai2(View view){
    startActivity(new Intent(this,Bai2.class));
  }
  public void bai3(View view){
    startActivity(new Intent(this,Bai3.class));
  }
  public void bai4(View view){
    startActivity(new Intent(this,Bai4.class));
  }
}