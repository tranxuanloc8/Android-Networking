package com.polyit.lab3.bai4;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
public class ContactList {
  @SerializedName("contacts")
  @Expose
  private ArrayList<Contact> contacts = new ArrayList<>();

  public ArrayList<Contact> getContacts() {
    return contacts;
  }

  public void setContacts(ArrayList<Contact> contacts) {
    this.contacts = contacts;
  }
}
