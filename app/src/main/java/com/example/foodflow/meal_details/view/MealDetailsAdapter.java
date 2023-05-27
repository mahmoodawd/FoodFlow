package com.example.foodflow.meal_details.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodflow.R;
import com.example.foodflow.models.Meal;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;


public class MealDetailsAdapter extends RecyclerView.Adapter<MealDetailsAdapter.ViewHolder> {
    private final Context context;
    private Lifecycle lifecycle;
    private final OnFABClickListener onFABClickListener;
    private List<Meal> meals;
    StringBuilder ingredientsSB = new StringBuilder();
    StringBuilder measuresSB = new StringBuilder();
    private static final String TAG = "MealsRecyclerView";


    public MealDetailsAdapter(Context context, Lifecycle lifecycle, List<Meal> values, OnFABClickListener clickListener) {
        this.lifecycle = lifecycle;
        meals = new ArrayList<>();
        this.context = context;
        this.meals = values;
        onFABClickListener = clickListener;
    }

    void setMealsList(List<Meal> mealList) {
        this.meals = mealList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.meal_details_layout, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder");


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Meal meal = meals.get(position);
        holder.mealInstruction.setText(meal.getStrInstructions());
        lifecycle.addObserver(holder.youTubePlayerView);
        holder.mealTitle.setText(meal.getStrMeal());
        holder.mealCategory.setText(meal.getStrCategory());
        holder.mealArea.setText(meal.getStrArea());
        addIngredientToIngredients(meal);
        addMeasures(meal);
        holder.ingredients.setText(ingredientsSB);
        holder.measures.setText(measuresSB);
        holder.addToFavorites.setOnCheckedChangeListener((buttonView, isChecked) -> onFABClickListener.onClick(isChecked, meal));
        holder.youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = meal.getYoutubeVideoId();
                youTubePlayer.cueVideo(videoId, 0);
            }
        });


        Log.i(TAG, "onBindViewHolder");
    }

    private void addIngredientToIngredients(@NonNull Meal meal) {

        List<String> ingredients = new ArrayList<>();
        ingredients.add(meal.getStrIngredient1());
        ingredients.add(meal.getStrIngredient2());
        ingredients.add(meal.getStrIngredient3());
        ingredients.add(meal.getStrIngredient4());
        ingredients.add(meal.getStrIngredient5());
        ingredients.add(meal.getStrIngredient6());
        ingredients.add(meal.getStrIngredient7());
        ingredients.add(meal.getStrIngredient8());
        ingredients.add(meal.getStrIngredient9());
        ingredients.add(meal.getStrIngredient10());
        ingredients.add(meal.getStrIngredient11());
        ingredients.add(meal.getStrIngredient12());
        ingredients.add(meal.getStrIngredient13());
        ingredients.add(meal.getStrIngredient14());
        ingredients.add(meal.getStrIngredient15());
        ingredients.add(meal.getStrIngredient16());
        ingredients.add(meal.getStrIngredient17());
        ingredients.add(meal.getStrIngredient18());
        ingredients.add(meal.getStrIngredient19());
        ingredients.add(meal.getStrIngredient20());

        appendIngredient(ingredients);
    }

    private void appendIngredient(List<String> ingredients) {
        for (String ingredient : ingredients) {
            if (ingredient != null && !ingredient.isEmpty() && !ingredient.equals(" ")) {
                ingredientsSB.append("\n" + ingredient);
            }
        }
    }

    private void addMeasures(@NonNull Meal meal) {
        List<String> measures = new ArrayList<>();
        measures.add(meal.getStrMeasure1());
        measures.add(meal.getStrMeasure2());
        measures.add(meal.getStrMeasure3());
        measures.add(meal.getStrMeasure4());
        measures.add(meal.getStrMeasure5());
        measures.add(meal.getStrMeasure6());
        measures.add(meal.getStrMeasure7());
        measures.add(meal.getStrMeasure8());
        measures.add(meal.getStrMeasure9());
        measures.add(meal.getStrMeasure10());
        measures.add(meal.getStrMeasure11());
        measures.add(meal.getStrMeasure12());
        measures.add(meal.getStrMeasure13());
        measures.add(meal.getStrMeasure14());
        measures.add(meal.getStrMeasure15());
        measures.add(meal.getStrMeasure16());
        measures.add(meal.getStrMeasure17());
        measures.add(meal.getStrMeasure18());
        measures.add(meal.getStrMeasure19());
        measures.add(meal.getStrMeasure20());
        appendMeasures(measures);

    }

    private void appendMeasures(List<String> measures) {
        for (String measure : measures) {
            if (measure != null && !measure.isEmpty() && !measure.equals(" ")) {
                measuresSB.append("\n" + measure);
            }
        }
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        YouTubePlayerView youTubePlayerView;
        public TextView mealTitle;
        public TextView mealCategory;
        public TextView mealArea;
        public TextView mealInstruction;
        public TextView ingredients;
        public TextView measures;
        public CheckBox addToFavorites;
        public LinearLayout mealDetailsLayout;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            youTubePlayerView = v.findViewById(R.id.youtube_player_view);
            mealTitle = v.findViewById(R.id.mealTitleTV);
            mealCategory = v.findViewById(R.id.mealCategoryTV);
            mealArea = v.findViewById(R.id.mealAreaTV);
            mealInstruction = v.findViewById(R.id.instructionsDetails);
            ingredients = v.findViewById(R.id.ingredientsDetailsTV);
            measures = v.findViewById(R.id.measuresDetailsTV);
            addToFavorites = v.findViewById(R.id.iconFav);
            mealDetailsLayout = v.findViewById(R.id.mealDetailsLayout);

        }
    }
}

