package com.polyit.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class AllProductsActivity extends AppCompatActivity {
  private ListView lvProducts;
  private Button btnCancel;
  LoadAllProductsTask task;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_all_products);
    lvProducts = (ListView)findViewById(R.id.listProducts);
    btnCancel = (Button) findViewById(R.id.btnCancel);
    task = new LoadAllProductsTask(AllProductsActivity.this,lvProducts);
    task.execute();
    btnCancel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if(resultCode == 100){
      // if result code 100 is received
      // means user edited/deleted product
      // reload this screen again
      Intent intent = getIntent();
      finish();
      startActivity(intent);
    }
  }
}