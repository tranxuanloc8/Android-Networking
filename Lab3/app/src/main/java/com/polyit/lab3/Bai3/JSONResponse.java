package com.polyit.lab3.Bai3;

import com.google.gson.annotations.SerializedName;


public class JSONResponse {
  private String msg;
  private AndroidVersion[] contacts;

  public AndroidVersion[] getContacts() {
    return contacts;
  }

  public String getMsg() {
    return msg;
  }
}
