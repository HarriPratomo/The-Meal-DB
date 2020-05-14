package com.example.themeal.model.categories;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseMeals{

	@SerializedName("meals")
	private List<MealsItem> meals;

	public List<MealsItem> getMeals(){
		return meals;
	}
}