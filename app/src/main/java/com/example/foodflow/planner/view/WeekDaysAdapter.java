package com.example.foodflow.planner.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodflow.R;
import com.example.foodflow.core.view.OnThumbnailClickListener;
import com.example.foodflow.models.WeekDay;

import java.util.ArrayList;
import java.util.List;


public class WeekDaysAdapter extends RecyclerView.Adapter<WeekDaysAdapter.ViewHolder> {
    private final Context context;
    private List<WeekDay> weekDays;
    private final OnThumbnailClickListener onMealThumbClickListener;
    private final OnDelIconClickListener onDelIconClickListener;
    private final OnAddBtnClickListener onAddBtnClickListener;
    private static String TAG = "WeekDaysRecyclerView";

    public WeekDaysAdapter(Context context, List<WeekDay> values, OnThumbnailClickListener onMealThumbClickListener, OnDelIconClickListener onDelIconClickListener, OnAddBtnClickListener onAddBtnClickListener) {
        this.onMealThumbClickListener = onMealThumbClickListener;
        this.onDelIconClickListener = onDelIconClickListener;
        this.onAddBtnClickListener = onAddBtnClickListener;
        weekDays = new ArrayList<>();
        this.context = context;
        this.weekDays = values;
    }


    public void setDayList(List<WeekDay> weekDays) {
        this.weekDays = weekDays;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.day_layout, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        WeekDay day = weekDays.get(position);
        holder.dayTitle.setText(day.getTitle());

        PlannerMealsAdapter plannerMealsAdapter;
        plannerMealsAdapter = new PlannerMealsAdapter(context, weekDays.get(position).getMeals(),
                onMealThumbClickListener, onDelIconClickListener);
        holder.dayMealsRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        holder.dayMealsRecyclerView.setAdapter(plannerMealsAdapter);
        plannerMealsAdapter.notifyDataSetChanged();
        holder.addBtn.setOnClickListener(v -> onAddBtnClickListener.onAddBtnClick(v, day.getTitle()));
        Log.i(TAG, "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return weekDays == null ? 0 : weekDays.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dayTitle;
        public Button addBtn;
        public RecyclerView dayMealsRecyclerView;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            dayTitle = v.findViewById(R.id.dayTitle);
            addBtn = v.findViewById(R.id.addBtn);
            dayMealsRecyclerView = v.findViewById(R.id.plannerMealsRecyclerView);

        }
    }
}

