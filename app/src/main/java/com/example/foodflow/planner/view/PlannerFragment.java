package com.example.foodflow.planner.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodflow.R;
import com.example.foodflow.core.view.OnThumbnailClickListener;
import com.example.foodflow.db.ConcreteLocalSource;
import com.example.foodflow.models.PlannerMeal;
import com.example.foodflow.network.API_Client;
import com.example.foodflow.models.WeekDay;
import com.example.foodflow.planner.presenter.PlannerPresenter;
import com.example.foodflow.repositories.Repository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;


public class PlannerFragment extends Fragment implements PlannerViewInterface, OnThumbnailClickListener, OnDelIconClickListener, OnAddBtnClickListener {

    PlannerPresenter plannerPresenter;
    RecyclerView weekDaysRecyclerView;
    TextView guestPrompt;
    WeekDaysAdapter weekDaysAdapter;
    List<WeekDay> weekDays;
    List<PlannerMeal> saturdayMeals;
    List<PlannerMeal> sundayMeals;
    List<PlannerMeal> mondayMeals;
    List<PlannerMeal> tuesdayMeals;
    List<PlannerMeal> wednesdayMeals;
    List<PlannerMeal> thursdayMeals;
    List<PlannerMeal> fridayMeals;

    public PlannerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_planner, container, false);
        weekDaysRecyclerView = view.findViewById(R.id.weekDaysRecyclerView);
        guestPrompt = view.findViewById(R.id.guestPrompt);
        initDays();
        initWeekDays();

        plannerPresenter = new PlannerPresenter(Repository.getInstance(this.getContext(), API_Client.getInstance(), ConcreteLocalSource.getInstance(this.getContext())), this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        weekDaysAdapter = new WeekDaysAdapter(this.getContext(), weekDays, this, this, this);
        weekDaysRecyclerView.setHasFixedSize(true);
        weekDaysRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL);
        weekDaysRecyclerView.addItemDecoration(dividerItemDecoration);
        weekDaysRecyclerView.setAdapter(weekDaysAdapter);
        plannerPresenter.getMealsOfTheWeek(this.getViewLifecycleOwner());
        return view;
    }

    private void initDays() {
        saturdayMeals = new ArrayList<>();
        sundayMeals = new ArrayList<>();
        mondayMeals = new ArrayList<>();
        tuesdayMeals = new ArrayList<>();
        wednesdayMeals = new ArrayList<>();
        thursdayMeals = new ArrayList<>();
        fridayMeals = new ArrayList<>();
    }

    private void initWeekDays() {
        weekDays = new ArrayList<>();
        weekDays.add(new WeekDay("Saturday", saturdayMeals));
        weekDays.add(new WeekDay("Sunday", sundayMeals));
        weekDays.add(new WeekDay("Monday", mondayMeals));
        weekDays.add(new WeekDay("Tuesday", tuesdayMeals));
        weekDays.add(new WeekDay("Wednesday", wednesdayMeals));
        weekDays.add(new WeekDay("Thursday", thursdayMeals));
        weekDays.add(new WeekDay("Friday", fridayMeals));
    }

    @Override
    public void displayMeals(List<PlannerMeal> plannerMeals) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            guestPrompt.setVisibility(View.VISIBLE);
        } else {

            clearLists();
            for (PlannerMeal meal : plannerMeals) {
                String weekDay = meal.getWeekDay();

                if (weekDay.equalsIgnoreCase(DayOfWeek.SATURDAY.name())) {
                    saturdayMeals.add(meal);

                }
                if (weekDay.equalsIgnoreCase(DayOfWeek.SUNDAY.name())) {
                    sundayMeals.add(meal);

                }
                if (weekDay.equalsIgnoreCase(DayOfWeek.MONDAY.name())) {
                    mondayMeals.add(meal);

                }
                if (weekDay.equalsIgnoreCase(DayOfWeek.TUESDAY.name())) {
                    tuesdayMeals.add(meal);

                }
                if (weekDay.equalsIgnoreCase(DayOfWeek.WEDNESDAY.name())) {
                    wednesdayMeals.add(meal);

                }
                if (weekDay.equalsIgnoreCase(DayOfWeek.THURSDAY.name())) {
                    thursdayMeals.add(meal);

                }
                if (weekDay.equalsIgnoreCase(DayOfWeek.FRIDAY.name())) {
                    fridayMeals.add(meal);

                }
            }
            weekDaysAdapter.setDayList(weekDays);
        }
    }

    private void clearLists() {
        saturdayMeals.clear();
        sundayMeals.clear();
        mondayMeals.clear();
        tuesdayMeals.clear();
        wednesdayMeals.clear();
        thursdayMeals.clear();
        fridayMeals.clear();
    }

    @Override
    public void onImageClick(View view, String mealId) {
        showMealDetails(view, mealId);
    }

    private void showMealDetails(View view, String mealId) {
        PlannerFragmentDirections.ActionPlannerFragmentToMealDetailsFragment action = PlannerFragmentDirections.actionPlannerFragmentToMealDetailsFragment(mealId);
        Navigation.findNavController(view).navigate(action);
    }

    @Override
    public void onDelIconClick(PlannerMeal meal) {
        removeFromPlan(meal);
        weekDaysAdapter.notifyDataSetChanged();
    }

    @Override
    public void removeFromPlan(PlannerMeal meal) {
        plannerPresenter.deleteMealFromPlan(meal);
    }

    @Override
    public void onAddBtnClick(View v, String weekDay) {
        Navigation.findNavController(v).navigate(PlannerFragmentDirections.actionPlannerFragmentToAllMealsFragment(weekDay));
    }
}