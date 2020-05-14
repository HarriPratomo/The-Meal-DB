package com.example.themeal.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.themeal.R;
import com.example.themeal.activity.DetailActivity;
import com.example.themeal.adapter.AdapterListFavoritesMeals;
import com.example.themeal.database.MealHelper;
import com.example.themeal.model.meal.Meal;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFavorites extends Fragment implements AdapterListFavoritesMeals.OnItemClickCallback{
    private RecyclerView rvMovie;
    private MealHelper mealHelper;
    private RecyclerView.LayoutManager llm;
    private AdapterListFavoritesMeals adapter;
    private ArrayList<Meal> listItem;
    @BindView(R.id.nodatafav)
    ImageView nodata;


    public FragmentFavorites() {

    }

    public static com.example.themeal.fragment.FragmentFavorites newInstance() {
        com.example.themeal.fragment.FragmentFavorites fragment = new com.example.themeal.fragment.FragmentFavorites();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_favorites, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.rv_fav);
        mealHelper = MealHelper.getInstance(getContext());
        listItem = new ArrayList<>();
        adapter = new AdapterListFavoritesMeals(getContext());
        llm = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rvMovie.setLayoutManager(llm);
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        mealHelper.open();
        listItem.clear();
        listItem.addAll(mealHelper.getAllMeal());
        adapter.setData(listItem);
        if (adapter.getItemCount() == 0) {
            rvMovie.setVisibility(View.GONE);
            nodata.setVisibility(View.VISIBLE);
            adapter.notifyDataSetChanged();
        } else {
            nodata.setVisibility(View.GONE);
            rvMovie.setVisibility(View.VISIBLE);
            adapter.setOnClickCallback(this);
            adapter.notifyDataSetChanged();
            rvMovie.setAdapter(adapter);
            mealHelper.close();
        }
    }

    @Override
    public void onClicked(View v, Meal item, int position) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra("id", item.getIdMeal());
        intent.putExtra(DetailActivity.EXTRA_ITEM, item);
        startActivity(intent);
    }
}
