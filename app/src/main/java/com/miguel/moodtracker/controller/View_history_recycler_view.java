package com.miguel.moodtracker.controller;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;

import com.miguel.moodtracker.R;
import com.miguel.moodtracker.model.Mood_history;

import java.util.ArrayList;
import java.util.List;

public class View_history_recycler_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history_recycler_view);

        RecyclerView mRecyclerView;
        List<Mood_history> mmood_list;
        MoodHistoryAdapter monadapteur;
        long timestamp = System.currentTimeMillis();
        SharedPreferences mSharedPreferences = this.getSharedPreferences("display size", MODE_PRIVATE);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        Log.e("Width", "" + width);
        Log.e("height", "" + height);
        
        mSharedPreferences.edit()
                .putInt("width", width)
                .putInt("height", height)
                .apply();


        mRecyclerView = findViewById(R.id.recyclerView);

        mmood_list = new ArrayList<>();
        mmood_list.add(new Mood_history("Happy",getResources().getColor(R.color.background_happy), timestamp));
        mmood_list.add(new Mood_history("Sad",getResources().getColor(R.color.background_sad), timestamp));
        mmood_list.add(new Mood_history("Super Happy",getResources().getColor(R.color.background_superHappy), timestamp));
        mmood_list.add(new Mood_history("Ehhh",getResources().getColor(R.color.background_ehhh), timestamp));
        mmood_list.add(new Mood_history("Ok",getResources().getColor(R.color.background_ok), timestamp));
        mmood_list.add(new Mood_history("Sad",getResources().getColor(R.color.background_sad), timestamp));
        mmood_list.add(new Mood_history("Super Happy",getResources().getColor(R.color.background_superHappy), timestamp));
        mmood_list.add(new Mood_history("Happy",getResources().getColor(R.color.background_happy), timestamp));
        mmood_list.add(new Mood_history("Sad",getResources().getColor(R.color.background_sad), timestamp));
        mmood_list.add(new Mood_history("Super Happy",getResources().getColor(R.color.background_superHappy), timestamp));
        mmood_list.add(new Mood_history("Ehhh",getResources().getColor(R.color.background_ehhh), timestamp));
        mmood_list.add(new Mood_history("Ok",getResources().getColor(R.color.background_ok), timestamp));
        mmood_list.add(new Mood_history("Sad",getResources().getColor(R.color.background_sad), timestamp));
        mmood_list.add(new Mood_history("Super Happy",getResources().getColor(R.color.background_superHappy), timestamp));
        mmood_list.add(new Mood_history("Happy",getResources().getColor(R.color.background_happy), timestamp));
        mmood_list.add(new Mood_history("Sad",getResources().getColor(R.color.background_sad), timestamp));
        mmood_list.add(new Mood_history("Super Happy",getResources().getColor(R.color.background_superHappy), timestamp));
        mmood_list.add(new Mood_history("Ehhh",getResources().getColor(R.color.background_ehhh), timestamp));
        mmood_list.add(new Mood_history("Ok",getResources().getColor(R.color.background_ok), timestamp));
        mmood_list.add(new Mood_history("Sad",getResources().getColor(R.color.background_sad), timestamp));
        mmood_list.add(new Mood_history("Super Happy",getResources().getColor(R.color.background_superHappy), timestamp));

        monadapteur = new MoodHistoryAdapter(mmood_list, this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(monadapteur);








    }

}
