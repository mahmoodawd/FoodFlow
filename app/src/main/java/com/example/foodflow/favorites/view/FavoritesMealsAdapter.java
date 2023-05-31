package com.example.foodflow.favorites.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodflow.R;
import com.example.foodflow.core.view.OnThumbnailClickListener;
import com.example.foodflow.models.Meal;

import java.util.ArrayList;
import java.util.List;


public class FavoritesMealsAdapter extends RecyclerView.Adapter<FavoritesMealsAdapter.ViewHolder> {
    private final Context context;
    private List<Meal> meals;
    private final OnThumbnailClickListener onMealThumbClickListener;
    private final OnDelImgClickListener onDelImgClickListener;
    private static String TAG = "plannerMealsRecyclerView";

    public FavoritesMealsAdapter(Context context, List<Meal> values, OnThumbnailClickListener onMealThumbClickListener, OnDelImgClickListener onDelIconClickListener) {
        this.onMealThumbClickListener = onMealThumbClickListener;
        this.onDelImgClickListener = onDelIconClickListener;
        meals = new ArrayList<>();
        this.context = context;
        this.meals = values;
    }


    public void setMealsList(List<Meal> mealList) {
        this.meals = mealList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.favorite_meal_layout, recyclerView, false);
        CardView cardView = (CardView) v;
        ViewHolder viewHolder = new ViewHolder(cardView);
        Log.i(TAG, "onCreateViewHolder");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Meal meal = meals.get(position);
        holder.mealTitle.setText(meal.getStrMeal());
        holder.mealArea.setText(meal.getStrArea());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.mealThumb);

        holder.mealThumb.setOnClickListener(v -> {
            onMealThumbClickListener.onImageClick(v, meal.getIdMeal());
        });
        holder.deleteImg.setOnClickListener(v -> {
            onDelImgClickListener.onDelIconClick(meal);
        });
        Log.i(TAG, "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return meals == null ? 0 : meals.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mealTitle;
        public TextView mealArea;
        public ImageView mealThumb;
        public ImageView deleteImg;
        public CardView mealLayout;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            mealTitle = v.findViewById(R.id.mealTitle);
            mealArea = v.findViewById(R.id.mealArea);
            mealThumb = v.findViewById(R.id.mealImage);
            deleteImg = v.findViewById(R.id.delImg);
            mealLayout = v.findViewById(R.id.favoriteMealLayout);
        }
    }
}

