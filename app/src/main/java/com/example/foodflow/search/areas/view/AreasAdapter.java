package com.example.foodflow.search.areas.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodflow.R;
import com.example.foodflow.core.view.OnThumbnailClickListener;
import com.example.foodflow.models.Area;

import java.util.ArrayList;
import java.util.List;


public class AreasAdapter extends RecyclerView.Adapter<AreasAdapter.ViewHolder> {
    private final Context context;
    private List<Area> areas;

    private static final String TAG = "AreaRecyclerView";
    private OnThumbnailClickListener onThumbClickListener;


    public AreasAdapter(Context context, List<Area> areaList, OnThumbnailClickListener onThumbnailClickListener) {
        this.onThumbClickListener = onThumbnailClickListener;
        areas = new ArrayList<>();
        this.context = context;
        this.areas = areaList;

    }

    void setAreas(List<Area> areaList) {
        this.areas = areaList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.area_layout, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder");


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            Area area = areas.get(position);
            holder.areaThumbnail.setImageResource(area.getAreaThumbnail());
            holder.areaThumbnail.setOnClickListener(v -> {
                onThumbClickListener.onImageClick(v, area.getName());
            });

        Log.i(TAG, "onBindViewHolder");
    }


    @Override
    public int getItemCount() {
        return areas != null ? areas.size() : 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView areaThumbnail;
        public ConstraintLayout areaLayout;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            areaThumbnail = v.findViewById(R.id.area_image_view);
            areaLayout = v.findViewById(R.id.area_layout);

        }
    }
}

