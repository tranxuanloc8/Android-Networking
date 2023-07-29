package com.polyit.lab4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    // Kiểm tra xem savedInstanceState có null không để không ghi đè lên Fragment đã lưu trữ
    if (savedInstanceState == null) {
      loadFragment(new LoginFragment()); // Thay YourInitialFragment bằng Fragment ban đầu của bạn
    }
  }

  // Phương thức này dùng để thay thế Fragment trong fragment_frame
  private void loadFragment(Fragment fragment) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.fragment_frame, fragment);
    fragmentTransaction.commit();
  }
}
