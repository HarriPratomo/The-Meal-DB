package com.example.themeal.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.themeal.R;
import com.example.themeal.adapter.TabAdapterAllCategories;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMenu extends Fragment {

    private TabAdapterAllCategories tabsAdapter;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;


    public FragmentMenu() {
        // Required empty public constructor
    }
    public static FragmentMenu newInstance() {
        FragmentMenu fragment = new FragmentMenu();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTab();
    }

    private void initTab() {
        tabLayout.addTab(tabLayout.newTab().setText("Beef"));
        tabLayout.addTab(tabLayout.newTab().setText("Breakfast"));
        tabLayout.addTab(tabLayout.newTab().setText("Chicken"));
        tabLayout.addTab(tabLayout.newTab().setText("Dessert"));
        tabLayout.addTab(tabLayout.newTab().setText("Goat"));
        tabLayout.addTab(tabLayout.newTab().setText("Lamb"));
        tabLayout.addTab(tabLayout.newTab().setText("Miscellaneous"));
        tabLayout.addTab(tabLayout.newTab().setText("Pasta"));
        tabLayout.addTab(tabLayout.newTab().setText("Pork"));
        tabLayout.addTab(tabLayout.newTab().setText("Seafood"));
        tabLayout.addTab(tabLayout.newTab().setText("Side"));
        tabLayout.addTab(tabLayout.newTab().setText("Starter"));
        tabLayout.addTab(tabLayout.newTab().setText("Vegan"));
        tabLayout.addTab(tabLayout.newTab().setText("Vegetarian"));
        tabsAdapter = new TabAdapterAllCategories(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabsAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
