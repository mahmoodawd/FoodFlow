package com.example.foodflow.planner.all_meals.view;

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
import com.example.foodflow.models.PlannerMeal;

import java.util.ArrayList;
import java.util.List;


public class AllMealsAdapter extends RecyclerView.Adapter<AllMealsAdapter.ViewHolder> {
    private final Context context;
    private List<PlannerMeal> plannerMeals;
    private final OnItemClickListener onMealThumbClickListener;
    private static  String TAG = "plannerMealsRecyclerView";

    public AllMealsAdapter(Context context, List<PlannerMeal> values, OnItemClickListener onMealThumbClickListener) {
        this.onMealThumbClickListener = onMealThumbClickListener;
        plannerMeals = new ArrayList<>();
        this.context = context;
        this.plannerMeals = values;
    }


    public void setMealsList(List<PlannerMeal> mealList) {
        this.plannerMeals = mealList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.all_meals_meal_layout, recyclerView, false);
        CardView cardView = (CardView) v;
        ViewHolder viewHolder = new ViewHolder(cardView);
        Log.i(TAG, "onCreateViewHolder");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        PlannerMeal meal = plannerMeals.get(position);
        holder.mealTitle.setText(meal.getStrMeal());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.mealThumb);

        holder.mealThumb.setOnClickListener(v -> {
            onMealThumbClickListener.onImageClick  (v, meal);
        });
        Log.i(TAG, "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return plannerMeals == null ? 0 : plannerMeals.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mealTitle;
        public ImageView mealThumb;
        public CardView mealLayout;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            mealTitle = v.findViewById(R.id.allMealsMealTitle);
            mealThumb = v.findViewById(R.id.allMealsMealImage);
            mealLayout = v.findViewById(R.id.allMealsMealLayout);
        }
    }
}

