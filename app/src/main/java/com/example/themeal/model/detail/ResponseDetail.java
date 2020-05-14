package com.example.themeal.model.detail;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDetail{

	@SerializedName("meals")
	private List<Detail> meals;

	public List<Detail> getMeals(){
		return meals;
	}
}