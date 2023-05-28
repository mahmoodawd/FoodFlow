package com.example.foodflow.search.categories.view;

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
import com.example.foodflow.models.Category;

import java.util.ArrayList;
import java.util.List;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private final Context context;
    private List<Category> categories;

    private static final String TAG = "CategoriesRecyclerView";
    private OnThumbnailClickListener onMealThumbClickListener;


    public CategoriesAdapter(Context context, List<Category> categoryList, OnThumbnailClickListener onMealThumbClickListener) {
        this.onMealThumbClickListener = onMealThumbClickListener;
        categories = new ArrayList<>();
        this.context = context;
        this.categories = categoryList;

    }

    void setCategories(List<Category> categoryList) {
        this.categories = categoryList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.category_layout, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder");


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Category category = categories.get(position);
        holder.categoryTitle.setText(category.getStrCategory());
        Glide.with(context).load(category.getStrCategoryThumb()).into(holder.categoryThumbnail);
        holder.categoryLayout.setOnClickListener(v -> {
            onMealThumbClickListener.onImageClick(v, category.getStrCategory());
        });

        Log.i(TAG, "onBindViewHolder");
    }


    @Override
    public int getItemCount() {
        return categories != null ? categories.size() : 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView categoryThumbnail;
        public TextView categoryTitle;
        public CardView categoryLayout;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            categoryTitle = v.findViewById(R.id.categoryTitle);
            categoryThumbnail = v.findViewById(R.id.categoryImage);
            categoryLayout = v.findViewById(R.id.categoryLayout);

        }
    }
}

