package com.example.musicapp.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIRetrofitClient {
    private static Retrofit retrofit = null;
    public static Retrofit getClient(String base_url){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                        .readTimeout(10, TimeUnit.MINUTES)
                                        .writeTimeout(10,TimeUnit.MINUTES)
                                        .connectTimeout(10,TimeUnit.MINUTES)
                                        .retryOnConnectionFailure(true)
                                        .protocols(Arrays.asList(Protocol.HTTP_1_1))
                                        .build();
        Gson gson=new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();
        return retrofit;
    }
}
