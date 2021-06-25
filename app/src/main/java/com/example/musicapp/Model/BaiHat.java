package com.example.musicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaiHat implements Serializable {

    @SerializedName("IdBaiHat")
    @Expose
    private String idBaiHat;
    @SerializedName("TenBaiHat")
    @Expose
    private String tenBaiHat;
    @SerializedName("CaSy")
    @Expose
    private String caSy;
    @SerializedName("HinhBaiHat")
    @Expose
    private String hinhBaiHat;
    @SerializedName("LinkBaiHat")
    @Expose
    private String linkBaiHat;
    @SerializedName("LoiBaiHat")
    @Expose
    private String loiBaiHat;
    @SerializedName("IdAlbum")
    @Expose
    private String idAlbum;
    @SerializedName("IdPlaylist")
    @Expose
    private String idPlaylist;
    @SerializedName("LuotThich")
    @Expose
    private String luotThich;

    /**
     * No args constructor for use in serialization
     *
     */
    public BaiHat() {
    }

    /**
     *
     * @param tenBaiHat
     * @param loiBaiHat
     * @param luotThich
     * @param hinhBaiHat
     * @param idPlaylist
     * @param caSy
     * @param linkBaiHat
     * @param idAlbum
     * @param idBaiHat
     */
    public BaiHat(String idBaiHat, String tenBaiHat, String caSy, String hinhBaiHat, String linkBaiHat, String loiBaiHat, String idAlbum, String idPlaylist, String luotThich) {
        super();
        this.idBaiHat = idBaiHat;
        this.tenBaiHat = tenBaiHat;
        this.caSy = caSy;
        this.hinhBaiHat = hinhBaiHat;
        this.linkBaiHat = linkBaiHat;
        this.loiBaiHat = loiBaiHat;
        this.idAlbum = idAlbum;
        this.idPlaylist = idPlaylist;
        this.luotThich = luotThich;
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

    public String getCaSy() {
        return caSy;
    }

    public void setCaSy(String caSy) {
        this.caSy = caSy;
    }

    public String getHinhBaiHat() {
        return hinhBaiHat;
    }

    public void setHinhBaiHat(String hinhBaiHat) {
        this.hinhBaiHat = hinhBaiHat;
    }

    public String getLinkBaiHat() {
        return linkBaiHat;
    }

    public void setLinkBaiHat(String linkBaiHat) {
        this.linkBaiHat = linkBaiHat;
    }

    public String getLoiBaiHat() {
        return loiBaiHat;
    }

    public void setLoiBaiHat(String loiBaiHat) {
        this.loiBaiHat = loiBaiHat;
    }

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(String idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public String getLuotThich() {
        return luotThich;
    }

    public void setLuotThich(String luotThich) {
        this.luotThich = luotThich;
    }

}