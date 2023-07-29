package com.polyit.lab3.bai1;

public class Contact {
  String id, name, email, address, gender, phone, mobile, home, office;

  public Contact() {
  }

  public Contact(String id, String name, String email, String address, String gender, String phone, String mobile, String home, String office) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.address = address;
    this.gender = gender;
    this.phone = phone;
    this.mobile = mobile;
    this.home = home;
    this.office = office;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getHome() {
    return home;
  }

  public void setHome(String home) {
    this.home = home;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }
}
