package com.example.themeal.network;

import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Harri Pratomo on 11/05/2020.
 * <p>
 * harrypratomo135@gmail.com
 */
public class RetrofitApi {
    public static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    public static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
