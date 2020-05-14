package com.example.themeal.adapter;

import com.example.themeal.fragment.sub_fragment.FragmentBeef;
import com.example.themeal.fragment.sub_fragment.FragmentBreakfast;
import com.example.themeal.fragment.sub_fragment.FragmentChicken;
import com.example.themeal.fragment.sub_fragment.FragmentDessert;
import com.example.themeal.fragment.sub_fragment.FragmentFork;
import com.example.themeal.fragment.sub_fragment.FragmentGoat;
import com.example.themeal.fragment.sub_fragment.FragmentLamb;
import com.example.themeal.fragment.sub_fragment.FragmentMiscelanous;
import com.example.themeal.fragment.sub_fragment.FragmentPasta;
import com.example.themeal.fragment.sub_fragment.FragmentSeafood;
import com.example.themeal.fragment.sub_fragment.FragmentSide;
import com.example.themeal.fragment.sub_fragment.FragmentVegan;
import com.example.themeal.fragment.sub_fragment.FragmentVegetarian;
import com.example.themeal.fragment.sub_fragment.Fragment_Starter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by Harri Pratomo
 * harrypratomo135@gmail.com
 */
public class TabAdapterAllCategories extends FragmentStatePagerAdapter {
    int numbOfTabs;


    public TabAdapterAllCategories(@NonNull FragmentManager fm, int numbOfTabs) {
        super(fm);
        this.numbOfTabs = numbOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentBeef beef =  new FragmentBeef();
                return beef;
            case 1:
                FragmentBreakfast breakfast = new FragmentBreakfast();
                return breakfast;
            case 2:
                FragmentChicken chicken = new FragmentChicken();
                return chicken;
            case 3:
                FragmentDessert dessert  = new FragmentDessert();
                return dessert;
            case 4:
                FragmentGoat goat =  new FragmentGoat();
                return goat;
            case 5:
                FragmentLamb lamb = new FragmentLamb();
                return lamb;
            case 6:
                FragmentMiscelanous miscelanous = new FragmentMiscelanous();
                return miscelanous;
            case 7:
                FragmentPasta pasta  = new FragmentPasta();
                return pasta;
            case 8:
                FragmentFork fork =  new FragmentFork();
                return fork;
            case 9:
                FragmentSeafood seafood = new FragmentSeafood();
                return seafood;
            case 10:
                FragmentSide side = new FragmentSide();
                return side;
            case 11:
                Fragment_Starter starter  = new Fragment_Starter();
                return starter;
            case 12:
                FragmentVegan vegan  = new FragmentVegan();
                return vegan;
            case 13:
                FragmentVegetarian vegetarian  = new FragmentVegetarian();
                return vegetarian;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numbOfTabs;
    }
}

