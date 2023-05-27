package com.example.foodflow.home.view;

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
import com.example.foodflow.models.Meal;

import java.util.ArrayList;
import java.util.List;


public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.ViewHolder> {
    private final Context context;
    private List<Meal> meals;
    private final OnMealThumbClickListener onMealThumbClickListener;
    private static final String TAG = "MealsRecyclerView";

    public MealsAdapter(Context context, List<Meal> values, OnMealThumbClickListener onMealThumbClickListener) {
        this.onMealThumbClickListener = onMealThumbClickListener;
        meals = new ArrayList<>();
        this.context = context;
        this.meals = values;
    }


    public void setMealsList(List<Meal> mealList) {
        this.meals = mealList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.meal_layout, recyclerView, false);
        CardView cardView = (CardView) v;
        ViewHolder viewHolder = new ViewHolder(cardView);
        Log.i(TAG, "onCreateViewHolder");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Meal meal = meals.get(position);
        holder.titleTxt.setText(meal.getStrMeal());
        holder.mealAreaTxt.setText(meal.getStrArea());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.imageView);

        holder.imageView.setOnClickListener(v -> {
            onMealThumbClickListener.onImageClick(v, meal.getIdMeal());
        });
        Log.i(TAG, "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTxt;
        public ImageView imageView;
        public TextView mealAreaTxt;
        public CardView mealLayout;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            titleTxt = v.findViewById(R.id.mealTitle);
            imageView = v.findViewById(R.id.mealImage);
            mealAreaTxt = v.findViewById(R.id.mealArea);
            mealLayout = v.findViewById(R.id.mealLayout);
        }
    }
}

