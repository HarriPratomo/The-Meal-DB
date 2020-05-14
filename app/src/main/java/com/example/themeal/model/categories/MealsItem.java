package com.example.themeal.model.categories;

import com.google.gson.annotations.SerializedName;

public class MealsItem{

	@SerializedName("strCategory")
	private String strCategory;

	private Integer photoPic;

	public MealsItem(String strCategory, Integer photoPic) {
		this.strCategory = strCategory;
		this.photoPic = photoPic;
	}

	public Integer getPhotoPic() {
		return photoPic;
	}

	public void setPhotoPic(Integer photoPic) {
		this.photoPic = photoPic;
	}

	public String getStrCategory(){
		return strCategory;
	}
}