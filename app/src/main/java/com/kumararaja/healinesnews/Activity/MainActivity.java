package com.kumararaja.healinesnews.Activity;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.kumararaja.healinesnews.Adapter.NewsRecycler;
import com.kumararaja.healinesnews.Apis;
import com.kumararaja.healinesnews.Model.Article;
import com.kumararaja.healinesnews.Model.News;
import com.kumararaja.healinesnews.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Apis apiService;
    RecyclerView recyclerView;
    List<Article>articles=new ArrayList<>();
    Context context;

    SwipeRefreshLayout swipeRefreshLayout;
    private NewsRecycler adapter;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        apiService=RetrofitSingleton.createService(Apis.class);
        recyclerView=(RecyclerView)findViewById(R.id.recycle);
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipe);
        functions();
    }

    private void functions() {

        retrofit2.Call<News> call=apiService.getHeadLines();
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                Log.v("Arraylist", new Gson().toJson(response.body()));

                if (response.isSuccessful() && response.body().getArticles() != null) {
                    News resp = response.body();
                    if (resp != null) {
                        Log.v("new", "Inside");
                        articles = new ArrayList<>();

                        articles = resp.getArticles();
                        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new NewsRecycler(MainActivity.this, articles);
                        recyclerView.setAdapter(adapter);

                    }
                } else {

                }
            }


            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
          }
}
