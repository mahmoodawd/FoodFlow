package com.example.foodflow.repositories;

import android.content.Context;

import com.example.foodflow.db.ConcreteLocalSource;
import com.example.foodflow.models.AreasResponse;
import com.example.foodflow.models.CategoriesResponse;
import com.example.foodflow.models.IngredientsResponse;
import com.example.foodflow.models.Meal;
import com.example.foodflow.models.MealsResponse;
import com.example.foodflow.models.PlannerMeal;
import com.example.foodflow.models.PlannerMealResponse;
import com.example.foodflow.network.RemoteSource;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;


public class Repository implements RepositoryInterface {

    private Context context;
    RemoteSource remoteSource;
    ConcreteLocalSource concreteLocalSource;
    private static Repository instance = null;
    private String TAG = "Repo";


    private Repository(Context context, RemoteSource remoteSource, ConcreteLocalSource concreteLocalSource) {
        this.context = context;
        this.remoteSource = remoteSource;
        this.concreteLocalSource = concreteLocalSource;
    }

    public static Repository getInstance(Context context, RemoteSource remoteSource, ConcreteLocalSource concreteLocalSource) {
        if (instance == null) {
            instance = new Repository(context, remoteSource, concreteLocalSource);
        }
        return instance;
    }

    @Override
    public Single<List<Meal>> getMealDetails(String mealId) {
        return concreteLocalSource.getMealDetails(mealId)
                .flatMapObservable(Observable::fromIterable)
                .toList()
                .flatMap(localMeals -> {
                    if (!localMeals.isEmpty()) {
                        return Single.just(localMeals);
                    } else {
                        return Single.defer(() -> remoteSource.getMealDetails(mealId)
                                .map(MealsResponse::getMeals)
                                .flatMapObservable(Observable::fromIterable)
                                .toList()
                        );
                    }
                })
                .onErrorResumeNext(throwable -> Single.error(throwable));
    }
    public Single<MealsResponse> getMealOfTheDay() {
        return remoteSource.getMealOfTheDay();
    }

    @Override
    public Single<PlannerMealResponse> getAllMeals() {
        return remoteSource.getAllMeals();
    }

    @Override
    public Single<MealsResponse> searchMeal(String meal) {
        return remoteSource.searchMeals(meal);
    }

    @Override
    public Single<CategoriesResponse> getCategories() {
        return remoteSource.getCategories();
    }

    @Override
    public Single<MealsResponse> getMealsByCategory(String category) {
        return remoteSource.getMealsByCategory(category);
    }

    @Override
    public Single<MealsResponse> getMealsByArea(String area) {
        return remoteSource.getMealsByArea(area);
    }

    @Override
    public Single<MealsResponse> getMealsByIngredient(String ingredient) {
        return remoteSource.getMealsByIngredient(ingredient);
    }

    @Override
    public Single<AreasResponse> getAreas() {
        return remoteSource.getAreas();
    }

    @Override
    public Single<IngredientsResponse> getIngredients() {
        return remoteSource.getIngredients();
    }


    @Override
    public Observable<List<Meal>> getFavoritesMeals() {
        return concreteLocalSource.getStoredMeals();
    }

    @Override
    public void insertIntoFavorites(Meal meal) {
        concreteLocalSource.insert(meal);
    }

    @Override
    public void deleteFromFavorites(Meal meal) {
        concreteLocalSource.delete(meal);
    }

    @Override
    public Observable<List<PlannerMeal>> getDayMeals(String weekDay) {
        return concreteLocalSource.getMealsByDay(weekDay);
    }

    @Override
    public Observable<List<PlannerMeal>> getCurrentWeekMeals() {
        return concreteLocalSource.getWeekMeals();
    }

    @Override
    public void plan(PlannerMeal meal) {
        concreteLocalSource.plan(meal);
    }

    @Override
    public void unPlan(PlannerMeal meal) {
        concreteLocalSource.unPlan(meal);
    }

}
