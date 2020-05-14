package com.example.themeal.model.meal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class Meal implements Parcelable {

	@SerializedName("strMealThumb")
	private String strMealThumb;

	@SerializedName("idMeal")
	private String idMeal;

	@SerializedName("strMeal")
	private String strMeal;

	public void setStrMealThumb(String strMealThumb) {
		this.strMealThumb = strMealThumb;
	}

	public void setIdMeal(String idMeal) {
		this.idMeal = idMeal;
	}

	public void setStrMeal(String strMeal) {
		this.strMeal = strMeal;
	}

	protected Meal(Parcel in) {
		strMealThumb = in.readString();
		idMeal = in.readString();
		strMeal = in.readString();
	}

	public Meal() {

	}
	public Meal(JSONObject jObject, String[] keys) {
		try {
			idMeal = jObject.getString(keys[0]);
			strMeal = jObject.getString(keys[1]);
			strMealThumb = jObject.getString(keys[2]);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static final Creator<Meal> CREATOR = new Creator<Meal>() {
		@Override
		public Meal createFromParcel(Parcel in) {
			return new Meal(in);
		}

		@Override
		public Meal[] newArray(int size) {
			return new Meal[size];
		}
	};

	public String getStrMealThumb(){
		return strMealThumb;
	}

	public String getIdMeal(){
		return idMeal;
	}

	public String getStrMeal(){
		return strMeal;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(strMealThumb);
		dest.writeString(String.valueOf(idMeal));
		dest.writeString(strMeal);
	}
}