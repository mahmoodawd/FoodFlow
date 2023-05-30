package com.example.foodflow.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity (primaryKeys = {"ID", "WEEK_DAY"})
public class PlannerMeal extends Meal {
    @NonNull
    @ColumnInfo(name = "WEEK_DAY")
    private String weekDay;

    @NonNull
    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(@NonNull String weekDay) {
        this.weekDay = weekDay;
    }
}
