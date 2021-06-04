package com.example.btlversion1.data.api;

import com.example.btlversion1.data.rss.thethao.Rss;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api_Rss_Thethao {
    String Base_URL ="https://cdn.24h.com.vn/";
    @GET("upload/rss/thethao.rss")
    Call<Rss> getRss();
}
