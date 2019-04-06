package com.miguel.moodtracker.controller;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.miguel.moodtracker.R;
import com.miguel.moodtracker.model.Mood_history;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class View_history_recycler_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_view_history_recycler_view);

        RecyclerView mRecyclerView;
        List<Mood_history> mmood_list;
        MoodHistoryAdapter monadapteur;
        SharedPreferences mPrefs = getSharedPreferences("mood_of_the_day", MODE_PRIVATE);
        SharedPreferences mSharedPreferencesDisplay = this.getSharedPreferences("display size", MODE_PRIVATE);

        Gson gson = new Gson();


        String json = mPrefs.getString("json", "");
        Log.i("json", "Json quand on le récup dans l'activité de l'historique"+ json);
        Mood_history[] moodTable = gson.fromJson(json, Mood_history[].class);
        Log.i("json", "Json quand on le remet au format de tableau" + Arrays.toString(moodTable));
        Mood_history mood1 = moodTable[0];
        Mood_history mood2 = moodTable[1];
        Mood_history mood3 = moodTable[2];
        Mood_history mood4 = moodTable[3];
        Mood_history mood5 = moodTable[4];
        Mood_history mood6 = moodTable[5];
        Mood_history mood7 = moodTable[6];


        Log.i("json", "premier élément quand on le récup" + mood1);





        int ressourceID = getResources().getIdentifier("status_bar_height", "dimen", "android");
        int barheight = getResources().getDimensionPixelSize(ressourceID);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        Log.e("Width", "" + width);
        Log.e("height", "" + height);




        mRecyclerView = findViewById(R.id.recyclerView);

       //int width = mLinearLayout.getWidth();
       //int height = mLinearLayout.getHeight();
        int screenHeight = height - barheight;

        mSharedPreferencesDisplay.edit()
                .putInt("width", width)
                .putInt("height", screenHeight)
                .apply();


        mmood_list = new ArrayList<>();
        mmood_list.add(new Mood_history("J'ai vu un oiseau",getResources().getColor(R.color.background_happy)));
        mmood_list.add(new Mood_history("J'ai manger du caca",getResources().getColor(R.color.background_sad)));
        mmood_list.add(new Mood_history("Super Happy",getResources().getColor(R.color.background_superHappy)));
        mmood_list.add(new Mood_history("J'ai mal au ventre",getResources().getColor(R.color.background_ehhh)));
        mmood_list.add(new Mood_history("",getResources().getColor(R.color.background_ok)));
        mmood_list.add(new Mood_history("J'ai trop d'erreurs dans mon code",getResources().getColor(R.color.background_sad)));
        mmood_list.add(new Mood_history("J'ai manger un kebab",getResources().getColor(R.color.background_superHappy)));


        monadapteur = new MoodHistoryAdapter(moodTable, this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(monadapteur);








    }

}
