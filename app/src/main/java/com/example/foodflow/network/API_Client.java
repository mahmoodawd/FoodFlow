package com.example.foodflow.network;


import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodflow.models.AreasResponse;
import com.example.foodflow.models.CategoriesResponse;
import com.example.foodflow.models.Ingredient;
import com.example.foodflow.models.IngredientsResponse;
import com.example.foodflow.models.MealsResponse;
import com.example.foodflow.models.PlannerMealResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class API_Client implements RemoteSource {
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static final String TAG = "API_Client";
    private static API_Client instance = null;
    API_Service apiService;

    private API_Client() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        apiService = retrofit.create(API_Service.class);
    }

    public static API_Client getInstance() {
        if (instance == null) {
            instance = new API_Client();
        }
        return instance;
    }


    @Override
    public void getAllMeals(NetworkDelegate networkDelegate) {
        Call<PlannerMealResponse> meals = apiService.getAllMeals("");
        meals.enqueue(new retrofit2.Callback<PlannerMealResponse>() {
            @Override
            public void onResponse(@NonNull Call<PlannerMealResponse> call, Response<PlannerMealResponse> response) {
                Log.i(TAG, "onResponse");
                assert response.body() != null;
                Log.i(TAG, String.valueOf(response.body().getPlannerMeals()));
                if (response.isSuccessful() && response.body() != null) {
                    networkDelegate.onSuccess(response.body().getPlannerMeals());
                }
            }

            @Override
            public void onFailure(Call<PlannerMealResponse> call, Throwable t) {
                Log.i(TAG, "onFailure");
                Log.e(TAG, t.getMessage());
                t.printStackTrace();
                networkDelegate.onFailure(t);
            }
        });
    }

    @Override
    public void getMealOfTheDay(NetworkDelegate callback) {

        Call<MealsResponse> meals = apiService.getRandomMeal();
        meals.enqueue(new retrofit2.Callback<MealsResponse>() {
            @Override
            public void onResponse(@NonNull Call<MealsResponse> call, Response<MealsResponse> response) {
                Log.i(TAG, "onResponse");
                assert response.body() != null;
                Log.i(TAG, String.valueOf(response.body().getMeals()));
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                Log.i(TAG, "onFailure");
                Log.e(TAG, t.getMessage());
                t.printStackTrace();
                callback.onFailure(t);
            }
        });
    }


    @Override
    public void getMealDetails(NetworkDelegate callback, String mealId) {

        Call<MealsResponse> mealDetails = apiService.getMealDetails(mealId);
        mealDetails.enqueue(new retrofit2.Callback<MealsResponse>() {
            @Override
            public void onResponse(@NonNull Call<MealsResponse> call, Response<MealsResponse> response) {
                Log.i(TAG, "onResponse");
                assert response.body() != null;
                Log.i(TAG, String.valueOf(response.body().getMeals()));
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                Log.i(TAG, "onFailure");
                Log.e(TAG, t.getMessage());
                t.printStackTrace();
                callback.onFailure(t);
            }
        });
    }

    @Override
    public void searchMeals(NetworkDelegate callback, String title) {
        Call<MealsResponse> meals = apiService.getSearchedMeals(title);
        meals.enqueue(new retrofit2.Callback<MealsResponse>() {
            @Override
            public void onResponse(@NonNull Call<MealsResponse> call, Response<MealsResponse> response) {
                Log.i(TAG, "onResponse");
                assert response.body() != null;
                Log.i(TAG, String.valueOf(response.body().getMeals()));
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                Log.i(TAG, "onFailure");
                Log.e(TAG, t.getMessage());
                t.printStackTrace();
                callback.onFailure(t);
            }
        });


    }

    @Override
    public void getMealsByCategories(NetworkDelegate callback, String category) {

        Call<MealsResponse> meals = apiService.getMealsByCategory(category);
        meals.enqueue(new retrofit2.Callback<MealsResponse>() {
            @Override
            public void onResponse(@NonNull Call<MealsResponse> call, Response<MealsResponse> response) {
                Log.i(TAG, "onResponse");
                assert response.body() != null;
                Log.i(TAG, String.valueOf(response.body().getMeals()));
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                Log.i(TAG, "onFailure");
                Log.e(TAG, t.getMessage());
                t.printStackTrace();
                callback.onFailure(t);
            }
        });
    }

    @Override
    public void getMealsByArea(NetworkDelegate callback, String area) {
        Call<MealsResponse> meals = apiService.getMealsByArea(area);
        meals.enqueue(new retrofit2.Callback<MealsResponse>() {
            @Override
            public void onResponse(@NonNull Call<MealsResponse> call, Response<MealsResponse> response) {
                Log.i(TAG, "onResponse");
                assert response.body() != null;
                Log.i(TAG, String.valueOf(response.body().getMeals()));
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                Log.i(TAG, "onFailure");
                Log.e(TAG, t.getMessage());
                t.printStackTrace();
                callback.onFailure(t);
            }
        });
    }

    @Override
    public void getMealsByIngredient(NetworkDelegate callback, String ingredient) {
        Call<MealsResponse> meals = apiService.getMealsByIngredient(ingredient);
        meals.enqueue(new retrofit2.Callback<MealsResponse>() {
            @Override
            public void onResponse(@NonNull Call<MealsResponse> call, Response<MealsResponse> response) {
                Log.i(TAG, "onResponse");
                assert response.body() != null;
                Log.i(TAG, String.valueOf(response.body().getMeals()));
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                Log.i(TAG, "onFailure");
                Log.e(TAG, t.getMessage());
                t.printStackTrace();
                callback.onFailure(t);
            }
        });
    }


    @Override
    public void getCategories(NetworkDelegate callback) {

        Call<CategoriesResponse> categories = apiService.getCategories();
        categories.enqueue(new retrofit2.Callback<CategoriesResponse>() {
            @Override
            public void onResponse(@NonNull Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                Log.i(TAG, "onResponse");
                assert response.body() != null;
                Log.i(TAG, String.valueOf(response.body().getCategories()));
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getCategories());
                }
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {
                Log.i(TAG, "onFailure");
                Log.e(TAG, t.getMessage());
                t.printStackTrace();
                callback.onFailure(t);
            }
        });
    }

    @Override
    public void getAreas(NetworkDelegate networkDelegate) {

        Call<AreasResponse> areas = apiService.getMealsAreas("list");
        areas.enqueue(new retrofit2.Callback<AreasResponse>() {
            @Override
            public void onResponse(@NonNull Call<AreasResponse> call, Response<AreasResponse> response) {
                Log.i(TAG, "onResponse");
                assert response.body() != null;
                Log.i(TAG, String.valueOf(response.body().getAreas().toString()));
                if (response.isSuccessful() && response.body() != null) {
                    networkDelegate.onSuccess(response.body().getAreas());
                }
            }

            @Override
            public void onFailure(Call<AreasResponse> call, Throwable t) {
                Log.i(TAG, "onFailure");
                Log.e(TAG, t.getMessage());
                t.printStackTrace();
                networkDelegate.onFailure(t);
            }
        });
    }

    @Override
    public void getIngredients(NetworkDelegate networkDelegate) {
        Call<IngredientsResponse> ingredients = apiService.getMealsIngredients("list");
        ingredients.enqueue(new retrofit2.Callback<IngredientsResponse>() {
            @Override
            public void onResponse(@NonNull Call<IngredientsResponse> call, Response<IngredientsResponse> response) {
                Log.i(TAG, "onResponse");
                if (response.isSuccessful() && response.body() != null) {
                    Log.i(TAG, String.valueOf(response.body().getIngredients()));
                    List<Ingredient> ingredients = response.body().getIngredients();
                    if (ingredients != null && !ingredients.isEmpty()) { // Add a null check here
                        networkDelegate.onSuccess(ingredients);
                        Log.i(TAG, String.valueOf(ingredients.get(0).getName()));
                    }
                }
            }

            @Override
            public void onFailure(Call<IngredientsResponse> call, Throwable t) {
                Log.i(TAG, "onFailure");
                Log.e(TAG, t.getMessage());
                t.printStackTrace();
                networkDelegate.onFailure(t);
            }
        });
    }


}