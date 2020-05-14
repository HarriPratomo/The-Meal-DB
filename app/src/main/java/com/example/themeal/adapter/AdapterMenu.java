package com.example.themeal.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.themeal.activity.DetailActivity;
import com.example.themeal.R;
import com.example.themeal.model.meal.Meal;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Harri Pratomo on 12/05/2020.
 * <p>
 * harrypratomo135@gmail.com
 */
public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.ViewHolder> {
    private List<Meal> list;
    private Context context;

    public AdapterMenu(List<Meal> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal meal = list.get(position);
        holder.name.setText(meal.getStrMeal());
        Picasso.get().load(meal.getStrMealThumb())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .fit().into(holder.image);

        holder.cliked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_ITEM, meal);
                intent.putExtra("idMeal",meal.getIdMeal());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nameMenu)
        TextView name;
        @BindView(R.id.imageMenu)
        ImageView image;
        @BindView(R.id.cardClick)
        CardView cliked;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
