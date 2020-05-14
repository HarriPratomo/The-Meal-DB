package com.example.themeal.model.meal;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseMeal_Item {

	@SerializedName("meals")
	private List<Meal> meals;

	public List<Meal> getMeals(){
		return meals;
	}
}