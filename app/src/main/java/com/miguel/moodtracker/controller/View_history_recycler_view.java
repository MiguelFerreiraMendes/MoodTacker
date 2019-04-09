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
import com.google.gson.reflect.TypeToken;
import com.miguel.moodtracker.R;
import com.miguel.moodtracker.model.Mood_history;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
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
       // List<Mood_history> mmood_list;
        MoodHistoryAdapter monadapteur;
        SharedPreferences mPrefs = getSharedPreferences("mood_of_the_day", MODE_PRIVATE);
        SharedPreferences mSharedPreferencesDisplay = this.getSharedPreferences("display size", MODE_PRIVATE);

        Gson gson = new Gson();


        String json = mPrefs.getString("json", "");
        Log.i("json", "Json quand on le récup dans l'activité de l'historique"+ json);
        Type mood_HistoryType = new TypeToken<ArrayList<Mood_history>>(){}.getType();
        //On crée le Type pour pouvoir l'utiliser dans le json et récupérer notre arraylist de Mood_History.
        ArrayList<Mood_history> moodList = gson.fromJson(json, mood_HistoryType);
        Log.i("json", "moodList après récup   " + moodList);





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


     //   mmood_list = new ArrayList<>();
     //   mmood_list.add(new Mood_history("J'ai vu un oiseau",getResources().getColor(R.color.background_happy)));
     //   mmood_list.add(new Mood_history("J'ai manger du caca",getResources().getColor(R.color.background_sad)));
     //   mmood_list.add(new Mood_history("Super Happy",getResources().getColor(R.color.background_superHappy)));
     //   mmood_list.add(new Mood_history("J'ai mal au ventre",getResources().getColor(R.color.background_ehhh)));
     //   mmood_list.add(new Mood_history("",getResources().getColor(R.color.background_ok)));
     //   mmood_list.add(new Mood_history("J'ai trop d'erreurs dans mon code",getResources().getColor(R.color.background_sad)));
     //   mmood_list.add(new Mood_history("J'ai manger un kebab",getResources().getColor(R.color.background_superHappy)));


        monadapteur = new MoodHistoryAdapter(moodList, this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(monadapteur);








    }

}
