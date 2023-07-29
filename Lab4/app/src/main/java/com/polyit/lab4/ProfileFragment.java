package com.polyit.lab4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment implements
        View.OnClickListener {
  private TextView tv_name, tv_email, tv_message;
  private SharedPreferences pref;
  private AppCompatButton btn_change_password, btn_logout;
  private EditText edt_old_password, edt_new_password;
  private AlertDialog dialog;
  private ProgressBar progressBar;
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_profile, container, false);
    initViews(view);
    return view;
  }
  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    pref = getActivity().getPreferences(0);
    tv_name.setText("Welcome : " + pref.getString(Constants.NAME, ""));
    tv_email.setText(pref.getString(Constants.EMAIL, ""));
  }
  private void initViews(View view) {
    tv_name = (TextView) view.findViewById(R.id.tv_name);
    tv_email = (TextView) view.findViewById(R.id.tv_email);
    btn_change_password = (AppCompatButton) view.findViewById(R.id.btn_chg_password);
    btn_logout = (AppCompatButton) view.findViewById(R.id.btn_logout);
    btn_change_password.setOnClickListener(this);
    btn_logout.setOnClickListener(this);
  }
  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_chg_password:
        showDialog();
        break;
      case R.id.btn_logout:
        logout();
        break;
    }
  }
  private void showDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    View view = inflater.inflate(R.layout.dialog_change_password, null);
    edt_old_password = (EditText) view.findViewById(R.id.et_old_password);
    edt_new_password = (EditText) view.findViewById(R.id.et_new_password);
    tv_message = (TextView) view.findViewById(R.id.tv_message);
    progressBar = (ProgressBar) view.findViewById(R.id.progress);
    builder.setView(view);
    builder.setTitle("Change Password");
    builder.setPositiveButton("Change Password", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
      }
    });
    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
      }
    });
    dialog = builder.create();
    dialog.show();
    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
       String old_password = edt_old_password.getText().toString();
       String new_password = edt_new_password.getText().toString();
       if (!old_password.isEmpty() && !new_password.isEmpty()) {
         progressBar.setVisibility(View.VISIBLE);
         changePasswordProcess(tv_email.getText().toString(),
                 old_password, new_password);
       } else {
         tv_message.setVisibility(View.VISIBLE);
         tv_message.setText("Fields are empty");
       }
     }
    });
  }
  private void logout() {
    SharedPreferences.Editor editor = pref.edit();
    editor.putBoolean(Constants.IS_LOGGED_IN, false);
    editor.putString(Constants.NAME, "");
    editor.putString(Constants.EMAIL, "");
    editor.putString(Constants.UNIQUE_ID, "");
    editor.apply();
    goToLogin();
  }
  private void goToLogin() {
    Fragment login = new LoginFragment();
    FragmentTransaction ft = getFragmentManager().beginTransaction();
    ft.replace(R.id.fragment_frame, login);
    ft.commit();
  }
  private void changePasswordProcess(String email, String old_password, String new_password) {
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    RequestInterface requestInterface = retrofit.create(RequestInterface.class);
    User user = new User();
    user.setEmail(email);
    user.setOld_password(old_password);
    user.setNew_password(new_password);
    ServerRequest request = new ServerRequest();
    request.setOperation(Constants.CHANGE_PASSWORD_OPERATION);
    request.setUser(user);
    Call<ServerResponse> response = requestInterface.operation(request);
    response.enqueue(new Callback<ServerResponse>() {
      @Override
      public void onResponse(Call<ServerResponse> call,
                             retrofit2.Response<ServerResponse> response) {
        ServerResponse resp = response.body();
        if (resp.getResult().equals(Constants.SUCCESS)) {
          progressBar.setVisibility(View.GONE);
          tv_message.setVisibility(View.GONE);
          dialog.dismiss();
          Snackbar.make(getView(), resp.getMessage(),
                  Snackbar.LENGTH_LONG).show();
        } else {
          progressBar.setVisibility(View.GONE);
          tv_message.setVisibility(View.VISIBLE);
          tv_message.setText(resp.getMessage());
        }
      }
      @Override
      public void onFailure(Call<ServerResponse> call, Throwable t) {
        Log.d(Constants.TAG, "failed "+t.getMessage());
        progressBar.setVisibility(View.GONE);
        tv_message.setVisibility(View.VISIBLE);
        tv_message.setText(t.getMessage());
      }
    });
  }
}