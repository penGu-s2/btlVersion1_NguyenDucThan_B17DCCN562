package com.example.btlversion1.data.api;

import com.example.btlversion1.data.rss.trangchu.Rss;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api_Rss {
    String Base_URL ="https://cdn.24h.com.vn/";
    @GET("upload/rss/trangchu24h.rss")
    Call<Rss> getRss();
}
