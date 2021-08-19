package com.example.myinstagramclone.webservice;

import com.example.myinstagramclone.model.PixabyPosts;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.http.GET;
import retrofit2.http.Query;
//Service
public interface Service {
    //Get MainPosts From Pixaby API
    @GET("?key=22845978-044da8f1b1b5c5bf3467d2b41&q=yellow+flowers&image_type=photo&pretty=true")
    Call<PixabyPosts> getAllPosts();
    //Get Posts by out keyword From Pixaby API
    @GET("?key=22845978-044da8f1b1b5c5bf3467d2b41&=photo&pretty=true")
    Call<PixabyPosts> getPostsByKeyboard(@Query("q") String keyWord);
}
