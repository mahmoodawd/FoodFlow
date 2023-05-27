package com.example.foodflow.meal_details.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodflow.R;
import com.example.foodflow.models.Meal;

import java.util.ArrayList;
import java.util.List;

public class MealIngredientsAdapter extends RecyclerView.Adapter<MealIngredientsAdapter.ViewHolder> {
private final Context context;
private List<Meal> meals;
private static final String TAG = "MealsIngredientsRV";

public MealIngredientsAdapter(Context context, List<Meal> values) {
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
        View v = inflater.inflate(R.layout.ingredient_layout, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder");
        return viewHolder;
        }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Meal meal = meals.get(position);
        holder.ingredientTitle.setText(meal.getStrIngredient1());
        holder.ingredientMeasure.setText(meal.getStrMeasure1());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.ingredientThumbnail);

        Log.i(TAG, "onBindViewHolder");
        }

@Override
public int getItemCount() {
        return meals.size();
        }

public void setProductsList(List<Meal> mealList) {
        this.meals = mealList;
        }

public static class ViewHolder extends RecyclerView.ViewHolder {
    public ImageView ingredientThumbnail;
    public TextView ingredientTitle;
    public TextView ingredientMeasure;
    public LinearLayout ingredientLayout;
    public View layout;

    public ViewHolder(View v) {
        super(v);
        layout = v;
        ingredientThumbnail = v.findViewById(R.id.ingredient_image_view);
        ingredientTitle = v.findViewById(R.id.ingredient_title_text_view);
        ingredientMeasure = v.findViewById(R.id.ingredient_measure_text_view);
        ingredientLayout = v.findViewById(R.id.ingredient_layout);
    }
}
}

