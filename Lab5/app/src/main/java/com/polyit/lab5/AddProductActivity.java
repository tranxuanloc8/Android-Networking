package com.polyit.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddProductActivity extends AppCompatActivity {
  private EditText edtName, edtPrice, edtDescription;
  private Button btnAdd, btnCancel;
  String strName,strPrice,strDes;
  CreateNewProductTask newProductTask;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_product);
    edtName = (EditText)findViewById(R.id.edtProductName);
    edtPrice = (EditText)findViewById(R.id.edtProductPrice);
    edtDescription = (EditText)findViewById(R.id.edtProductDes);
    btnAdd = (Button)findViewById(R.id.btnAdd);
    btnCancel = (Button)findViewById(R.id.btnCancel);
    newProductTask = new CreateNewProductTask(this);

    btnAdd.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        strName = edtName.getText().toString();
        strPrice = edtPrice.getText().toString();
        strDes = edtDescription.getText().toString();
        newProductTask.execute(strName,strPrice,strDes);
      }
    });

    btnCancel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }
}