package com.example.foodflow.search.view;

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


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchResultViewHolder> {
    private final Context context;
    private List<Object> itemList;
    private final OnThumbnailClickListener onMealThumbClickListener;
    private static final String TAG = "SearchAdapter";




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
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.meal_layout, parent, false);
        CardView cardView = (CardView) v;
        SearchResultViewHolder searchResultViewHolder = new SearchResultViewHolder(cardView);
        Log.i(TAG, "onCreateViewHolder : Meal");
        return searchResultViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultViewHolder holder, int position) {
        Object item = itemList.get(position);

        Meal meal = (Meal) item;
        SearchResultViewHolder searchResultViewHolder = (SearchResultViewHolder) holder;
        searchResultViewHolder.titleTxt.setText(meal.getStrMeal());
        searchResultViewHolder.mealAreaTxt.setText(meal.getStrArea());
        Glide.with(context).load(meal.getStrMealThumb()).into(searchResultViewHolder.imageView);
        searchResultViewHolder.imageView.setOnClickListener(v -> {
            onMealThumbClickListener.onImageClick(v, meal.getIdMeal());
        });
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    static class SearchResultViewHolder extends RecyclerView.ViewHolder {
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

}

