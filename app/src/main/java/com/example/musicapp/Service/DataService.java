package com.example.musicapp.Service;

import com.example.musicapp.Model.Album;
import com.example.musicapp.Model.BaiHat;
import com.example.musicapp.Model.Banner;
import com.example.musicapp.Model.ChuDe;
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.Model.User;

import java.util.List;

import retrofit2.*;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DataService {
    @GET("baihat.php")
    Call<List<BaiHat>> getListBaiHat();

    @GET("bangxephang.php")
    Call<List<BaiHat>> getBangXepHang();

    @GET("random.php")
    Call<List<BaiHat>> get5BaiHatRandom();

    @GET("banner.php")
    Call<List<Banner>> getListBanner();

    @GET("playlist.php")
    Call<List<Playlist>> getListPlaylist();

    @GET("album.php")
    Call<List<Album>> getListAlbum();

    @GET("chude.php")
    Call<List<ChuDe>> getListChuDe();

    @FormUrlEncoded
    @POST("baihat.php")
    Call<BaiHat> getBaiHat(@Field("id") String id);

    @FormUrlEncoded
    @POST("playlist.php")
    Call<List<Playlist>> getListPlaylistLimit(@Field("limit") int number);

    @FormUrlEncoded
    @POST("playlist.php")
    Call<List<Playlist>> getListPlaylistByChuDe(@Field("idChuDe") int number);

    @FormUrlEncoded
    @POST("album.php")
    Call<List<Album>> getListAlbumLimit(@Field("limit") int number);

    @FormUrlEncoded
    @POST("baihat.php")
    Call<List<BaiHat>> getListBaiHatByIDPlaylist(@Field("idPlaylist") int number);

    @FormUrlEncoded
    @POST("baihat.php")
    Call<List<BaiHat>> getListBaiHatByIDAlbum(@Field("idAlbum") int number);

    @FormUrlEncoded
    @POST("like.php")
    Call<String> Like(@Field("idBaiHat") String id);

    @FormUrlEncoded
    @POST("timkiem.php")
    Call<List<BaiHat>> TimKiem(@Field("keyword") String keyword);

    @FormUrlEncoded
    @POST("dangky.php")
    Call<String> DangKy(@Field("email") String email,@Field("password") String password,@Field("fullname") String fullname);

    @FormUrlEncoded
    @POST("dangnhap.php")
    Call<User> DangNhap(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("doimatkhau.php")
    Call<String> DoiMatKhau(@Field("email") String email,@Field("old_password") String old_password,@Field("new_password") String new_password);

}
