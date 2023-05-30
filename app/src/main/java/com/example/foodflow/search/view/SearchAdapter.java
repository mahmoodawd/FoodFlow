package com.example.foodflow.search.view;

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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodflow.R;
import com.example.foodflow.core.view.OnThumbnailClickListener;
import com.example.foodflow.models.Area;
import com.example.foodflow.models.Category;
import com.example.foodflow.models.Ingredient;
import com.example.foodflow.models.Meal;
import com.example.foodflow.search.areas.view.AreasAdapter;
import com.example.foodflow.search.categories.view.CategoriesAdapter;
import com.example.foodflow.search.ingredients.view.IngredientsAdapter;

import java.util.ArrayList;
import java.util.List;


public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private List<Object> itemList;
    private final OnThumbnailClickListener onMealThumbClickListener;
    private static final String TAG = "SearchAdapter";

    private final int MEAL_VIEW = 0;
    private final int INGREDIENT_VIEW = 1;
    private final int AREA_VIEW = 2;
    private final int CATEGORY_VIEW = 3;


    public SearchAdapter(Context context, List<Object> values, OnThumbnailClickListener onMealThumbClickListener) {
        this.onMealThumbClickListener = onMealThumbClickListener;
        itemList = new ArrayList<>();
        this.context = context;
        this.itemList = values;
    }


    public void setItemList(List<Object> itemList) {
        this.itemList = itemList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v;
        switch (viewType) {
            case MEAL_VIEW:
                v = inflater.inflate(R.layout.meal_layout, recyclerView, false);
                CardView cardView = (CardView) v;
                SearchResultViewHolder searchResultViewHolder = new SearchResultViewHolder(cardView);
                Log.i(TAG, "onCreateViewHolder : Meal");
                return searchResultViewHolder;
            case AREA_VIEW:
                v = inflater.inflate(R.layout.area_layout, recyclerView, false);
                Log.i(TAG, "onCreateViewHolder : Area");
                AreasViewHolder areasViewHolder = new AreasViewHolder(v);
                return areasViewHolder;
            case INGREDIENT_VIEW:
                v = inflater.inflate(R.layout.ingredient_layout, recyclerView, false);
                IngredientsViewHolder ingredientsViewHolder = new IngredientsViewHolder(v);
                Log.i(TAG, "onCreateViewHolder : Ingredient");
                return ingredientsViewHolder;
            case CATEGORY_VIEW:
                v = inflater.inflate(R.layout.category_layout, recyclerView, false);
                CategoriesViewHolder categoriesViewHolder = new CategoriesViewHolder(v);
                Log.i(TAG, "onCreateViewHolder : Category");
                return categoriesViewHolder;

        }

        return null;

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Object item = itemList.get(position);
        if (item instanceof Meal) {
            Meal meal = (Meal) item;
            SearchResultViewHolder searchResultViewHolder = (SearchResultViewHolder) holder;
            searchResultViewHolder.titleTxt.setText(meal.getStrMeal());
            searchResultViewHolder.mealAreaTxt.setText(meal.getStrArea());
            Glide.with(context).load(meal.getStrMealThumb()).into(searchResultViewHolder.imageView);
            searchResultViewHolder.imageView.setOnClickListener(v -> {
                onMealThumbClickListener.onImageClick(v, meal.getIdMeal());
            });
            Log.i(TAG, "onBindViewHolder: Meal");
        } else if (item instanceof Ingredient) {
            Ingredient ingredient = (Ingredient) item;
            IngredientsViewHolder ingredientsViewHolder = (IngredientsViewHolder) holder;
            ingredientsViewHolder.ingredientTitle.setText(ingredient.getName());
            ingredientsViewHolder.ingredientThumbnail.setOnClickListener(v -> {
                onMealThumbClickListener.onImageClick(v, ingredient.getName());
            });

            Log.i(TAG, "onBindViewHolder: Ingredient");
        } else if (item instanceof Area) {
            Area area = (Area) item;
            AreasViewHolder areasViewHolder = (AreasViewHolder) holder;
            areasViewHolder.areaThumbnail.setImageResource(area.getAreaThumbnail());
            areasViewHolder.areaThumbnail.setOnClickListener(v -> {
                onMealThumbClickListener.onImageClick(v, area.getName());
            });

            Log.i(TAG, "onBindViewHolder: Area");
        } else if (item instanceof Category) {
            Category category = (Category) item;
            CategoriesViewHolder categoriesViewHolder = (CategoriesViewHolder) holder;
            categoriesViewHolder.categoryTitle.setText(category.getStrCategory());
            Glide.with(context).load(category.getStrCategoryThumb()).into(categoriesViewHolder.categoryThumbnail);
            categoriesViewHolder.categoryLayout.setOnClickListener(v -> {
                onMealThumbClickListener.onImageClick(v, category.getStrCategory());
            });
            Log.i(TAG, "onBindViewHolder: Category");
        }

    }

    @Override
    public int getItemViewType(int position) {
        int output = 0;
        Object item = itemList.get(position);
        if (item instanceof Meal) output = MEAL_VIEW;
        else if (item instanceof Ingredient) output = INGREDIENT_VIEW;
        else if (item instanceof Area) output = AREA_VIEW;
        else if (item instanceof Category) output = CATEGORY_VIEW;

        return output;
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }


}

class SearchResultViewHolder extends RecyclerView.ViewHolder {
    public TextView titleTxt;
    public ImageView imageView;
    public TextView mealAreaTxt;
    public CardView mealLayout;
    public View layout;

    public SearchResultViewHolder(View v) {
        super(v);
        layout = v;
        titleTxt = v.findViewById(R.id.mealTitle);
        imageView = v.findViewById(R.id.mealImage);
        mealAreaTxt = v.findViewById(R.id.mealArea);
        mealLayout = v.findViewById(R.id.mealLayout);
    }
}

class AreasViewHolder extends RecyclerView.ViewHolder {
    public ImageView areaThumbnail;
    public ConstraintLayout areaLayout;
    public View layout;

    public AreasViewHolder(View v) {
        super(v);
        layout = v;
        areaThumbnail = v.findViewById(R.id.area_image_view);
        areaLayout = v.findViewById(R.id.area_layout);

    }

}

class CategoriesViewHolder extends RecyclerView.ViewHolder {
    public ImageView categoryThumbnail;
    public TextView categoryTitle;
    public CardView categoryLayout;
    public View layout;

    public CategoriesViewHolder(View v) {
        super(v);
        layout = v;
        categoryTitle = v.findViewById(R.id.categoryTitle);
        categoryThumbnail = v.findViewById(R.id.categoryImage);
        categoryLayout = v.findViewById(R.id.categoryLayout);

    }

}

class IngredientsViewHolder extends RecyclerView.ViewHolder {
    public ImageView ingredientThumbnail;
    public TextView ingredientTitle;
    public CardView ingredientLayout;
    public View layout;

    public IngredientsViewHolder(View v) {
        super(v);
        layout = v;
        ingredientTitle = v.findViewById(R.id.ingredient_title_text_view);
        ingredientThumbnail = v.findViewById(R.id.ingredient_image_view);
        ingredientLayout = v.findViewById(R.id.ingredient_layout);

    }
}

