package com.example.foodflow.search.ingredients.view;

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
import com.example.foodflow.models.Ingredient;

import java.util.ArrayList;
import java.util.List;


public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {
    private final Context context;
    private List<Ingredient> ingredients;

    private static final String TAG = "IngredientsRecyclerView";
    private OnThumbnailClickListener onMealThumbClickListener;


    public IngredientsAdapter(Context context, List<Ingredient> ingredientList, OnThumbnailClickListener onMealThumbClickListener) {
        this.onMealThumbClickListener = onMealThumbClickListener;
        ingredients = new ArrayList<>();
        this.context = context;
        this.ingredients = ingredientList;

    }

    void setIngredients(List<Ingredient> ingredientList) {
        this.ingredients = ingredientList;
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
        Ingredient ingredient = ingredients.get(position);
        holder.ingredientTitle.setText(ingredient.getName());
        Glide.with(context).load(ingredient.getThumbUrl()).into(holder.ingredientThumbnail);
        holder.ingredientThumbnail.setOnClickListener(v -> {
            onMealThumbClickListener.onImageClick(v, ingredient.getName());
        });

        Log.i(TAG, "onBindViewHolder");
    }


    @Override
    public int getItemCount() {
        return ingredients != null ? ingredients.size() : 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ingredientThumbnail;
        public TextView ingredientTitle;
        public CardView ingredientLayout;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            ingredientTitle = v.findViewById(R.id.ingredient_title_text_view);
            ingredientThumbnail = v.findViewById(R.id.ingredient_image_view);
            ingredientLayout = v.findViewById(R.id.ingredient_layout);

        }
    }
}

