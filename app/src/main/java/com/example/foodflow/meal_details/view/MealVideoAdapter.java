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

public class MealVideoAdapter extends RecyclerView.Adapter<MealVideoAdapter.ViewHolder> {
private final Context context;
private List<Meal> meals;
private static final String TAG = "MealsVideoRecyclerView";

public MealVideoAdapter(Context context, List<Meal> values) {
        meals = new ArrayList<>();
        this.context = context;
        this.meals = values;
        }

        void setMealsList(List<Meal> mealList) {
        this.meals = mealList;
        }


@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.meal_layout_video, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder");
        return viewHolder;
        }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Meal meal = meals.get(position);
        holder.mealVideoUrl.setText(meal.getStrYoutube());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.videoThumbnail);

        Log.i(TAG, "onBindViewHolder");
        }

@Override
public int getItemCount() {
        return meals.size();
        }


public static class ViewHolder extends RecyclerView.ViewHolder {
    public ImageView videoThumbnail;
    public TextView mealVideoUrl;
    public LinearLayout mealVideoLayout;
    public View layout;

    public ViewHolder(View v) {
        super(v);
        layout = v;
        videoThumbnail = v.findViewById(R.id.mealVideoThumbnail);
        mealVideoUrl = v.findViewById(R.id.mealVideoUrl);
        mealVideoLayout = v.findViewById(R.id.mealVideoLayout);
    }
}
}

