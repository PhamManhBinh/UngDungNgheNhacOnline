package com.example.musicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Banner implements Serializable {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("HinhAnh")
    @Expose
    private String hinhAnh;
    @SerializedName("NoiDung")
    @Expose
    private String noiDung;
    @SerializedName("IdBaiHat")
    @Expose
    private String idBaiHat;
    @SerializedName("TenBaiHat")
    @Expose
    private String tenBaiHat;
    @SerializedName("HinhBaiHat")
    @Expose
    private String hinhBaiHat;

    /**
     * No args constructor for use in serialization
     *
     */
    public Banner() {
    }

    /**
     *
     * @param tenBaiHat
     * @param hinhAnh
     * @param hinhBaiHat
     * @param id
     * @param noiDung
     * @param idBaiHat
     */
    public Banner(String id, String hinhAnh, String noiDung, String idBaiHat, String tenBaiHat, String hinhBaiHat) {
        super();
        this.id = id;
        this.hinhAnh = hinhAnh;
        this.noiDung = noiDung;
        this.idBaiHat = idBaiHat;
        this.tenBaiHat = tenBaiHat;
        this.hinhBaiHat = hinhBaiHat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getIdBaiHat() {
        return idBaiHat;
    }

    public void setIdBaiHat(String idBaiHat) {
        this.idBaiHat = idBaiHat;
    }

    public String getTenBaiHat() {
        return tenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        this.tenBaiHat = tenBaiHat;
    }

    public String getHinhBaiHat() {
        return hinhBaiHat;
    }

    public void setHinhBaiHat(String hinhBaiHat) {
        this.hinhBaiHat = hinhBaiHat;
    }

}