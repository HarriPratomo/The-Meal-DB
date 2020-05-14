package com.example.themeal.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.themeal.R;
import com.example.themeal.activity.DetailActivity;
import com.example.themeal.model.meal.Meal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Harri Pratomo on 09/05/2020.
 * <p>
 * harrypratomo135@gmail.com
 */
public class AdapterListFavoritesMeals extends RecyclerView.Adapter<AdapterListFavoritesMeals.ViewHolder> {
    private Context context;
    private ArrayList<Meal> listItem;
    private OnItemClickCallback onClickCallback;

    public AdapterListFavoritesMeals(Context context) {
        this.context = context;
        listItem = new ArrayList<>();
    }

    public AdapterListFavoritesMeals(Context context, ArrayList<Meal> listItem) {
        this.context = context;
        this.listItem = listItem;
    }


    public void setData(ArrayList<Meal> listItem) {
        this.listItem.clear();
        this.listItem.addAll(listItem);
        notifyDataSetChanged();
    }

    public void setOnClickCallback(OnItemClickCallback onClickCallback) {
        this.onClickCallback = onClickCallback;
    }

    public void removeItem(int position) {
        this.listItem.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listItem.size());
    }

    @NonNull
    @Override
    public AdapterListFavoritesMeals.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_favorites, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListFavoritesMeals.ViewHolder holder, int position) {
        Meal meal = listItem.get(position);
        holder.title.setText(meal.getStrMeal());
        Picasso.get().load(meal.getStrMealThumb()).placeholder(R.drawable.no_image).error(R.drawable.no_image).fit().into(holder.image);
        holder.card.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation));
        holder.card_images.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("idMeal", meal.getIdMeal());
                intent.putExtra(DetailActivity.EXTRA_ITEM, meal);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageFav)
        ImageView image;
        @BindView(R.id.nameFavorites)
        TextView title;
        @BindView(R.id.card_row_fav)
        CardView card;
        @BindView(R.id.cardLines)
        CardView card_images;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickCallback {
        void onClicked(View v, Meal item, int position);
    }
}

