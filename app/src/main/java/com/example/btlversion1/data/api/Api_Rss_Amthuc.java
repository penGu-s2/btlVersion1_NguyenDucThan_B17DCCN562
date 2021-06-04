package com.example.btlversion1.data.api;

import com.example.btlversion1.data.rss.amthuc.Rss;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api_Rss_Amthuc {
    String Base_URL ="https://cdn.24h.com.vn/";
    @GET("upload/rss/amthuc.rss")
    Call<Rss> getRss();
}
