package com.example.musicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Playlist implements Serializable {

    @SerializedName("IdPlaylist")
    @Expose
    private String idPlaylist;
    @SerializedName("IdChuDe")
    @Expose
    private String idChuDe;
    @SerializedName("Ten")
    @Expose
    private String ten;
    @SerializedName("HinhNen")
    @Expose
    private String hinhNen;

    /**
     * No args constructor for use in serialization
     *
     */
    public Playlist() {
    }

    /**
     *
     * @param idChuDe
     * @param idPlaylist
     * @param ten
     * @param hinhNen
     */
    public Playlist(String idPlaylist, String idChuDe, String ten, String hinhNen) {
        super();
        this.idPlaylist = idPlaylist;
        this.idChuDe = idChuDe;
        this.ten = ten;
        this.hinhNen = hinhNen;
    }

    public String getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(String idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public String getIdChuDe() {
        return idChuDe;
    }

    public void setIdChuDe(String idChuDe) {
        this.idChuDe = idChuDe;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinhNen() {
        return hinhNen;
    }

    public void setHinhNen(String hinhNen) {
        this.hinhNen = hinhNen;
    }

}