package com.kumararaja.healinesnews;

import com.kumararaja.healinesnews.Model.Modela;
import com.kumararaja.healinesnews.Model.News;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Apis {

    public static final String BASE_URL="https://newsapi.org/";

    public static final String URL="https://api.nasa.gov/";

@GET("/v2/top-headlines?country=us&category=business&apiKey=99ea9a3b6a354953b7a26a52bbaba73c")
Call<News> getHeadLines();

@GET("/mars-photos/api/v1/rovers?api_key=DEMO_KEY")
    Call<Modela> getData();
}
