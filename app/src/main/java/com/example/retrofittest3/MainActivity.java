package com.example.retrofittest3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final String base_url = "http://www.kobis.or.kr";
//    private TextView rank, movie_name, count1, count2;
//    private TextView tv_result;

    RecyclerView boxoffice_recyclerview;
    BoxOfficeApi boxOfficeApi;
    BoxOfficeAdapter boxOfficeAdapter;

    ArrayList<WeeklyBoxOfficeList> arrayList = new ArrayList<>();;

    Retrofit retrofit;

    String API_KEY = "67b6dffa8dea1495f99def02a6c7b88e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boxoffice_recyclerview = (RecyclerView)findViewById(R.id.boxoffice_recyclerview);

//        rank = (TextView)findViewById(R.id.rank);
//        movie_name = (TextView)findViewById(R.id.movie_name);
//        count1 = (TextView)findViewById(R.id.count1);
//        count2 = (TextView)findViewById(R.id.count2);
//        tv_result = (TextView)findViewById(R.id.tv_result);


        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        boxOfficeApi = retrofit.create(BoxOfficeApi.class);

        boxOfficeApi.getBoxOffice(API_KEY,"20210306").enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()){
                    Log.d("retro", 1+"");
                    Result result = response.body();
                    BoxOfficeResult boxOfficeResult = result.getBoxOfficeResult();
                    List<WeeklyBoxOfficeList> weeklyBoxOfficeList = boxOfficeResult.getWeeklyBoxOfficeList();
                    for (WeeklyBoxOfficeList weeklyBoxOffice : weeklyBoxOfficeList){
                        arrayList.add(weeklyBoxOffice);
                    }

                    boxOfficeAdapter = new BoxOfficeAdapter(arrayList, MainActivity.this);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                    boxoffice_recyclerview.setLayoutManager(linearLayoutManager);
                    boxoffice_recyclerview.setAdapter(boxOfficeAdapter);
                }else{
                    Log.d("retro", 2+"Error");
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
//                tv_result.setText(t.getMessage());
            }
        });
    }

}