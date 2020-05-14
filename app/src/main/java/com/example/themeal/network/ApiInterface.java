package com.example.themeal.network;

import com.example.themeal.model.categories.ResponseMeals;
import com.example.themeal.model.detail.ResponseDetail;
import com.example.themeal.model.meal.ResponseMeal_Item;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Harri Pratomo on 11/05/2020.
 * <p>
 * harrypratomo135@gmail.com
 */
public interface ApiInterface {

    @GET("filter.php?")
    Call<ResponseMeal_Item> getCategory(@Query("c") String category);

    @GET("lookup.php?")
    Call<ResponseDetail> getDetail(@Query("i") String idMeal);

    @GET("search.php?")
    Call<ResponseMeal_Item> getSearchMenu(@Query("s") String nameMenu);


}
