package com.example.retrofittest3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

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

    private final String base_url = "http://www.kobis.or.kr/";
//    private TextView rank, movie_name, count1, count2;
    private TextView tv_result;

//    BoxOfficeApi boxOfficeApi;
    List<WeeklyBoxOfficeList> weeklyBoxOfficeLists = new ArrayList<>();

    String API_KEY = "67b6dffa8dea1495f99def02a6c7b88e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        rank = (TextView)findViewById(R.id.rank);
//        movie_name = (TextView)findViewById(R.id.movie_name);
//        count1 = (TextView)findViewById(R.id.count1);
//        count2 = (TextView)findViewById(R.id.count2);
        tv_result = (TextView)findViewById(R.id.tv_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BoxOfficeApi boxOfficeApi = retrofit.create(BoxOfficeApi.class);

        boxOfficeApi.getBoxOffice(API_KEY,"20210306").enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()){
                    Log.e("retro", 1+"");
                    Result result = response.body();
                    Log.e("result 값 ", result.toString());
                    BoxOfficeResult boxOfficeResult = result.getBoxOfficeResult();
                    Log.e("boxofficeResult값 ", String.valueOf(boxOfficeResult));
                    /////////////////////////여기 문제임 여기서부터 내일 하는걸로 //////////////////////////
                    List<WeeklyBoxOfficeList> weeklyBoxOfficeLists2 = boxOfficeResult.getWeeklyBoxOfficeList();
                    Log.e("weeklyBoxOfficeLists 값 ", String.valueOf(weeklyBoxOfficeLists2));
                    for(WeeklyBoxOfficeList weeklyBoxOffice : weeklyBoxOfficeLists2) {
                        weeklyBoxOfficeLists.add(weeklyBoxOffice);
                    }

                    for(WeeklyBoxOfficeList list : weeklyBoxOfficeLists) {
                        String content = "";
                        content += "Rank: " + list.getRank() + "\n";
                        content += "Movie name: " + list.getMovieNm() + "\n";
                        content += "Title: " + list.getAudiCnt() + "\n";
                        content += "Text: " + list.getAudiAcc() + "\n";

                        tv_result.setText(content);
                    }

                }else{
                    Log.e("retro", 2+"Error");
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                tv_result.setText(t.getMessage());
            }
        });
    }

}