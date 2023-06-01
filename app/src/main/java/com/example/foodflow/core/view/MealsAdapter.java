package com.example.foodflow.core.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodflow.R;
import com.example.foodflow.models.Meal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;


public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.ViewHolder> {
    private final Context context;
    private List<Meal> meals;
    private final OnThumbnailClickListener onMealThumbClickListener;
    private static final String TAG = "MealsRecyclerView";
    private OnFavIconClickListener onFavIconClickListener;

    public MealsAdapter(Context context, List<Meal> values, OnThumbnailClickListener onMealThumbClickListener, OnFavIconClickListener onFavIconClickListener) {
        this.onMealThumbClickListener = onMealThumbClickListener;
        this.onFavIconClickListener = onFavIconClickListener;
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

        holder.addToFavorites.setOnCheckedChangeListener((buttonView, isChecked) -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            if (user == null) {
                Toast.makeText(context, "Please, Join to get Full Features",
                        Toast.LENGTH_LONG).show();
                holder.addToFavorites.setChecked(false);
            } else {
                onFavIconClickListener.onFavClick(isChecked, meal);
            }
        });

        Log.i(TAG, "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return meals == null ? 0 : meals.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTxt;
        public ImageView imageView;
        public TextView mealAreaTxt;
        public CardView mealLayout;
        public CheckBox addToFavorites;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            titleTxt = v.findViewById(R.id.mealTitle);
            imageView = v.findViewById(R.id.mealImage);
            mealAreaTxt = v.findViewById(R.id.mealArea);
            addToFavorites = v.findViewById(R.id.iconFav);
            mealLayout = v.findViewById(R.id.mealLayout);
        }
    }
}

