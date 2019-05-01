package com.kumararaja.healinesnews.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.kumararaja.healinesnews.Adapter.AdapterA;
import com.kumararaja.healinesnews.Apis;
import com.kumararaja.healinesnews.Model.Modela;
import com.kumararaja.healinesnews.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewActivity extends AppCompatActivity {
    Apis apiService;
    RecyclerView recyclerView;
    List<Modela.Rover> listview=new ArrayList<>();
    Context context;
    private AdapterA adapter;

    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newmain);

        apiService = RetrofitSingleton.createService(Apis.class);
        recyclerView = (RecyclerView) findViewById(R.id.recyclenew);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipee);
        apiMethod();


    }

    private void apiMethod() {

            retrofit2.Call<Modela> call=apiService.getData();
            call.enqueue(new Callback<Modela>() {
                @Override
                public void onResponse(Call<Modela> call, Response<Modela> response) {
                    Log.v("arrayList",new Gson().toJson(response.body()));
                    if (response.isSuccessful()&& response.body().getRovers()!=null) {
                        Modela resp = response.body();
                        if (resp != null) {
                            Log.v("new","inside"+resp);
                            listview = new ArrayList<>();
                            listview = resp.getRovers();
                            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            adapter = new AdapterA(NewActivity.this, listview);
                            recyclerView.setAdapter(adapter);
                        }
                    }else {

                        }
                    }

                @Override
                public void onFailure(Call<Modela> call, Throwable t) {
                    Log.e("Failed",t.getMessage());

                }
            });
    }

    }