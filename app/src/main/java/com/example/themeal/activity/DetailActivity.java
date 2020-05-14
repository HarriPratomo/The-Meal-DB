package com.example.themeal.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.themeal.R;
import com.example.themeal.database.MealHelper;
import com.example.themeal.model.detail.Detail;
import com.example.themeal.model.detail.ResponseDetail;
import com.example.themeal.model.meal.Meal;
import com.example.themeal.network.ApiInterface;
import com.example.themeal.network.RetrofitApi;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    String idMeal;
    int linecount;
    public static final String EXTRA_ITEM = "extraItem";
    private Meal item;
    private MealHelper mealHelper;
    public static final String TRAILER_THUMBNAIL_IMAGE_URL = "https://img.youtube.com/vi/";
    private ApiInterface apiInterface;
    @BindView(R.id.nameMenudetail) TextView title;
    @BindView(R.id.relativeDetail) RelativeLayout detail;
    @BindView(R.id.shimmer_view_container_detail) ShimmerFrameLayout shimerDetail;
    @BindView(R.id.imageDetailMenu) ImageView image;
    @BindView(R.id.imageVideos) ImageView imageVideo;
    @BindView(R.id.detailTagMenu) TextView tag;
    @BindView(R.id.locationDetail) TextView location;
    @BindView(R.id.categoriesDetail) TextView categories;
    @BindView(R.id.play) ImageButton playYoutube;
    @BindView(R.id.str10) TextView str_10;
    @BindView(R.id.str1) TextView str_1;
    @BindView(R.id.str2) TextView str_2;
    @BindView(R.id.str3) TextView str_3;
    @BindView(R.id.str4) TextView str_4;
    @BindView(R.id.str5) TextView str_5;
    @BindView(R.id.str6) TextView str_6;
    @BindView(R.id.str7) TextView str_7;
    @BindView(R.id.str8) TextView str_8;
    @BindView(R.id.str9) TextView str_9;

    @BindView(R.id.msr1) TextView msr_1;
    @BindView(R.id.msr2) TextView msr_2;
    @BindView(R.id.msr3) TextView msr_3;
    @BindView(R.id.msr4) TextView msr_4;
    @BindView(R.id.msr5) TextView msr_5;
    @BindView(R.id.msr6) TextView msr_6;
    @BindView(R.id.msr7) TextView msr_7;
    @BindView(R.id.msr8) TextView msr_8;
    @BindView(R.id.msr9) TextView msr_9;
    @BindView(R.id.msr10) TextView msr_10;
    @BindView(R.id.txtMore) TextView moreText;
    @BindView(R.id.favorites)
    FloatingActionButton favs;
    @BindView(R.id.descMenudetail) TextView description;
    @OnClick(R.id.backArrow)
    void onClick(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        idMeal = getIntent().getStringExtra("idMeal");
        item = getIntent().getParcelableExtra(EXTRA_ITEM);
        mealHelper = MealHelper.getInstance(this);
        mealHelper.open();
        initUI();
    }

    private void initUI() {
        loadDetail();
    }

    private void loadDetail() {
        apiInterface = RetrofitApi.getRetrofit().create(ApiInterface.class);
        apiInterface.getDetail(idMeal).enqueue(new Callback<ResponseDetail>() {
            @Override
            public void onResponse(Call<ResponseDetail> call, Response<ResponseDetail> response) {
                if (response.isSuccessful()){
                    detail.setVisibility(View.VISIBLE);
                    shimerDetail.stopShimmerAnimation();
                    shimerDetail.setVisibility(View.GONE);
                    ArrayList<Detail> meal = (ArrayList<Detail>) response.body().getMeals();
                    getDetail(meal);
                }
                else {
                    Snackbar.make(findViewById(R.id.detailMenu), "Load data failed !", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetail> call, Throwable t) {

            }
        });
    }

    private void getDetail(ArrayList<Detail> meal) {
        description.setText(meal.get(0).getStrInstructions());
        title.setText(meal.get(0).getStrMeal());
        tag.setText(meal.get(0).getStrTags());
        Picasso.get().load(meal.get(0).getStrMealThumb()).placeholder(R.drawable.no_image).error(R.drawable.no_image).fit().into(image);
        location.setText(meal.get(0).getStrArea());
        categories.setText(meal.get(0).getStrCategory());
        str_1.setText(meal.get(0).getStrIngredient1());
        str_2.setText(meal.get(0).getStrIngredient2());
        str_3.setText(meal.get(0).getStrIngredient3());
        str_4.setText(meal.get(0).getStrIngredient4());
        str_5.setText(meal.get(0).getStrIngredient5());
        str_6.setText(meal.get(0).getStrIngredient6());
        str_7.setText(meal.get(0).getStrIngredient7());
        str_8.setText(meal.get(0).getStrIngredient8());
        str_9.setText(meal.get(0).getStrIngredient9());
        str_10.setText(meal.get(0).getStrIngredient10());

        msr_1.setText(meal.get(0).getStrMeasure1());
        msr_2.setText(meal.get(0).getStrMeasure2());
        msr_3.setText(meal.get(0).getStrMeasure3());
        msr_4.setText(meal.get(0).getStrMeasure4());
        msr_5.setText(meal.get(0).getStrMeasure5());
        msr_6.setText(meal.get(0).getStrMeasure6());
        msr_7.setText(meal.get(0).getStrMeasure7());
        msr_8.setText(meal.get(0).getStrMeasure8());
        msr_9.setText(meal.get(0).getStrMeasure9());
        msr_10.setText(meal.get(0).getStrMeasure10());
        String youtube = meal.get(0).getStrYoutube();
        Uri uri = Uri.parse(youtube);
        String videoID = uri.getQueryParameter("v");
        String thumbnail = TRAILER_THUMBNAIL_IMAGE_URL+videoID+ "/hqdefault.jpg";
        Picasso.get().load(thumbnail).placeholder(R.drawable.no_image).error(R.drawable.no_image).resize(300,200).into(imageVideo);
        description.post(new Runnable() {
            @Override
            public void run() {
                linecount = description.getLineCount();
            }
        });
        playYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String action = Intent.ACTION_VIEW;
                Uri uri = Uri.parse("vnd.youtube:" + videoID);
                Intent videoIntent = new Intent(action, uri);
                startActivity(videoIntent);
            }
        });
    }
    @OnClick(R.id.txtMore)
    void more() {
        if (description.getMaxLines() <= 4) {
            moreText.setText("Less");
            description.setMaxLines(linecount);
        } else {
            moreText.setText("...More");
            description.setMaxLines(4);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        shimerDetail.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        shimerDetail.stopShimmerAnimation();
        super.onPause();
    }

    @OnClick(R.id.favorites)
    void clickFav() {
        if (!mealHelper.isExist(Integer.parseInt(this.item.getIdMeal()))) {
            favs.setImageResource(R.drawable.favorite_btn);
            addToFavorite();
        } else {
            favs.setImageResource(R.drawable.unfav);
            removeFromFavorite();
        }
    }
    private void addToFavorite() {
        long result = mealHelper.insert(this.item);
        if (result > 0)
            Snackbar.make(findViewById(R.id.detailMenu), R.string.add_favorite, Snackbar.LENGTH_LONG).show();

        else
            Snackbar.make(findViewById(R.id.detailMenu), R.string.fail_favorite, Snackbar.LENGTH_LONG).show();
    }

    private void removeFromFavorite() {
        int result = mealHelper.delete(Integer.parseInt(item.getIdMeal()));
        if (result > 0) {
            Snackbar.make(findViewById(R.id.detailMenu), R.string.remove_favorite, Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(findViewById(R.id.detailMenu), R.string.fail_remove, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mealHelper.isExist(Integer.parseInt(item.getIdMeal()))) {
            favs.setImageResource(R.drawable.favorite_btn);
        }
    }
}
