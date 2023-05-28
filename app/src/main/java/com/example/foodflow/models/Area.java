package com.example.foodflow.models;

import androidx.room.Ignore;

import com.example.foodflow.R;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Area {
    @SerializedName("strArea")
    private String name;
    @Ignore
    HashMap<String, Integer> flagMap;

    public String getName() {
        return name;
    }

    private void fillFlagMap() {
        List<String> areas = new ArrayList<>(Arrays.asList("American", "British", "Canadian",
                "Chinese", "Croatian", "Dutch", "Egyptian", "Filipino", "French", "Greek",
                "Indian", "Irish", "Italian", "Jamaican", "Japanese", "Kenyan", "Malaysian",
                "Mexican", "Moroccan", "Polish", "Portuguese", "Russian", "Spanish", "Thai",
                "Tunisian", "Turkish", "Unknown", "Vietnamese"));
        List<Integer> flags = new ArrayList<>((Arrays.asList(R.drawable.us, R.drawable.gb, R.drawable.ca,
                R.drawable.cn, R.drawable.hr, R.drawable.du, R.drawable.eg,
                R.drawable.ph, R.drawable.fr, R.drawable.gr, R.drawable.in,
                R.drawable.ie, R.drawable.it, R.drawable.jm, R.drawable.jp,
                R.drawable.kn, R.drawable.my, R.drawable.mx, R.drawable.ma,
                R.drawable.pl, R.drawable.pt, R.drawable.ru, R.drawable.es,
                R.drawable.th, R.drawable.tn, R.drawable.tr,
                R.drawable.ic_search, R.drawable.vn)));

        for (int i = 0; i < areas.size(); i++) {
            flagMap.put(areas.get(i), flags.get(i));
        }
    }

    public int getAreaThumbnail() {
        flagMap = new HashMap<>();
        fillFlagMap();
        int drawableId = flagMap.get(name);
        return drawableId;
    }
}
