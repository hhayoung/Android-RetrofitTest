package com.example.retrofittest3;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BoxOfficeApi {
    @GET("/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json?")
    Call<Result> getBoxOffice(@Query("key") String key,
                              @Query("targetDt") String targetDt);
}
