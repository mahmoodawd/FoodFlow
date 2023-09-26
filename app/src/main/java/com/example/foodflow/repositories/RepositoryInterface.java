package com.example.foodflow.repositories;


import com.example.foodflow.models.AreasResponse;
import com.example.foodflow.models.CategoriesResponse;
import com.example.foodflow.models.IngredientsResponse;
import com.example.foodflow.models.Meal;
import com.example.foodflow.models.MealsResponse;
import com.example.foodflow.models.PlannerMeal;
import com.example.foodflow.models.PlannerMealResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;


public interface RepositoryInterface {

    Single<MealsResponse> getMealOfTheDay();

    Single<PlannerMealResponse> getAllMeals();

    Single<MealsResponse> searchMeal(String meal);

    Single<CategoriesResponse> getCategories();

    Single<MealsResponse> getMealsByCategory(String category);

    Single<MealsResponse> getMealsByArea(String area);

    Single<MealsResponse> getMealsByIngredient(String ingredient);

    Single<AreasResponse> getAreas();

    Single<IngredientsResponse> getIngredients();

    Single<List<Meal>> getMealDetails(String mealId);


    //Local
    Observable<List<Meal>> getFavoritesMeals();

    void insertIntoFavorites(Meal meal);

    void deleteFromFavorites(Meal meal);

    Observable<List<PlannerMeal>> getDayMeals(String weekDay);

    Observable<List<PlannerMeal>> getCurrentWeekMeals();

    void plan(PlannerMeal meal);

    void unPlan(PlannerMeal meal);



}
