package com.example.lamlv.jsonexample.service.api.APIInterface;

import com.google.gson.JsonObject;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface IWeatherAPI {

    @GET("public/yql?")
    Observable<Response<JsonObject>> getWeatherData(@Query("q") String q, @Query("format") String format, @Query("env") String env);

    @GET("public/yql?")
    Observable<Response<JsonObject>> getWeatherDataByCityName(@Query("q") String q, @Query("format") String format, @Query("env") String env);
}
