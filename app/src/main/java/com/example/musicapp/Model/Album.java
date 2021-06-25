package com.example.musicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Album implements Serializable {

@SerializedName("IdAlbum")
@Expose
private String idAlbum;
@SerializedName("TenAlbum")
@Expose
private String tenAlbum;
@SerializedName("TenCasiAlbum")
@Expose
private String tenCasiAlbum;
@SerializedName("HinhAlbum")
@Expose
private String hinhAlbum;

/**
* No args constructor for use in serialization
*
*/
public Album() {
}

/**
*
* @param hinhAlbum
* @param tenAlbum
* @param idAlbum
* @param tenCasiAlbum
*/
public Album(String idAlbum, String tenAlbum, String tenCasiAlbum, String hinhAlbum) {
super();
this.idAlbum = idAlbum;
this.tenAlbum = tenAlbum;
this.tenCasiAlbum = tenCasiAlbum;
this.hinhAlbum = hinhAlbum;
}

public String getIdAlbum() {
return idAlbum;
}

public void setIdAlbum(String idAlbum) {
this.idAlbum = idAlbum;
}

public String getTenAlbum() {
return tenAlbum;
}

public void setTenAlbum(String tenAlbum) {
this.tenAlbum = tenAlbum;
}

public String getTenCasiAlbum() {
return tenCasiAlbum;
}

public void setTenCasiAlbum(String tenCasiAlbum) {
this.tenCasiAlbum = tenCasiAlbum;
}

public String getHinhAlbum() {
return hinhAlbum;
}

public void setHinhAlbum(String hinhAlbum) {
this.hinhAlbum = hinhAlbum;
}

}