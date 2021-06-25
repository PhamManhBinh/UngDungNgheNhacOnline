package com.example.musicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

@SerializedName("status")
@Expose
private String status;
@SerializedName("fullname")
@Expose
private String fullname;
@SerializedName("email")
@Expose
private String email;
@SerializedName("password")
@Expose
private String password;

/**
* No args constructor for use in serialization
*
*/
public User() {
}

/**
*
* @param password
* @param fullname
* @param email
* @param status
*/
public User(String status, String fullname, String email, String password) {
super();
this.status = status;
this.fullname = fullname;
this.email = email;
this.password = password;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public String getFullname() {
return fullname;
}

public void setFullname(String fullname) {
this.fullname = fullname;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}

}