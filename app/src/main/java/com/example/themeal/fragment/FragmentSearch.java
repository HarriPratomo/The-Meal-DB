package com.example.themeal.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.themeal.R;
import com.example.themeal.adapter.AdapterMenu;
import com.example.themeal.model.meal.Meal;
import com.example.themeal.model.meal.ResponseMeal_Item;
import com.example.themeal.network.ApiInterface;
import com.example.themeal.network.RetrofitApi;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSearch extends Fragment  {

    @BindView(R.id.search)
    SearchView searchView;

    @BindView(R.id.rv_search)
    RecyclerView r_search;

    @BindView(R.id.emptydata)
    RelativeLayout empty;

    @BindView(R.id.noresult)
    TextView noresultfound;

    private ApiInterface apiInterface;
    private AdapterMenu adapter;
    private List<Meal> listMovies = new ArrayList<>();
    private RecyclerView.LayoutManager llm;
    private Meal meal;

    public FragmentSearch() {

    }

    public static FragmentSearch newInstance() {
        FragmentSearch fragment = new FragmentSearch();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        meal = new Meal();
        empty.setVisibility(View.VISIBLE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loadMovie();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
       searchView.setQueryHint("Search menu here ....");
    }



    private void loadMovie() {
        apiInterface = RetrofitApi.getRetrofit().create(ApiInterface.class);
        llm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        r_search.setLayoutManager(llm);
        String input_movie = searchView.getQuery().toString();

        apiInterface.getSearchMenu(input_movie).enqueue(new Callback<ResponseMeal_Item>() {
            @Override
            public void onResponse(Call<ResponseMeal_Item> call, Response<ResponseMeal_Item> response) {
                if (response.isSuccessful()) {
                    listMovies = response.body().getMeals();
                        empty.setVisibility(View.GONE);
                        r_search.setVisibility(View.VISIBLE);
                        adapter = new AdapterMenu(listMovies, getContext());
                        r_search.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        if (adapter.getItemCount()==0){
                            empty.setVisibility(View.VISIBLE);
                            noresultfound.setVisibility(View.VISIBLE);
                        }else {
                            noresultfound.setVisibility(View.GONE);
                            r_search.setVisibility(View.VISIBLE);
                        }

                }else {
                    Toast.makeText(getContext(), "Load data failed !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMeal_Item> call, Throwable t) {
                t.getMessage();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
