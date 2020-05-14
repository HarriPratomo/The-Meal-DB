package com.example.themeal.fragment.sub_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.themeal.R;
import com.example.themeal.adapter.AdapterMenu;
import com.example.themeal.model.meal.Meal;
import com.example.themeal.model.meal.ResponseMeal_Item;
import com.example.themeal.network.ApiInterface;
import com.example.themeal.network.RetrofitApi;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSeafood extends Fragment {

    private ApiInterface apiInterface;
    private List<Meal> mealList;
    private AdapterMenu adapterMenu;
    private RecyclerView.LayoutManager llm;

    @BindView(R.id.rv_seafood)
    RecyclerView r_seafood;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout mShimmerViewContainer;


    public FragmentSeafood() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_seafood, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiInterface = RetrofitApi.getRetrofit().create(ApiInterface.class);
        initUI();
    }

    private void initUI() {
        llm = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        r_seafood.setLayoutManager(llm);
        apiInterface.getCategory("Seafood").enqueue(new Callback<ResponseMeal_Item>() {
            @Override
            public void onResponse(Call<ResponseMeal_Item> call, Response<ResponseMeal_Item> response) {
                if (response.isSuccessful()){
                    mShimmerViewContainer.stopShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    mealList = response.body().getMeals();
                    adapterMenu = new AdapterMenu(mealList,getContext());
                    r_seafood.setAdapter(adapterMenu);
                    adapterMenu.notifyDataSetChanged();
                }else {
                    Snackbar.make(getActivity().findViewById(R.id.mainLayout), "Load data failed !", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMeal_Item> call, Throwable t) {
                Snackbar.make(getActivity().findViewById(R.id.mainLayout), t.getMessage(), Snackbar.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }
}

