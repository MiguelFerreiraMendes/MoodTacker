package com.miguel.moodtracker.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miguel.moodtracker.model.Mood_history;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class AlertReceiver extends BroadcastReceiver {

    SharedPreferences mSharedPreferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "CA MARCCHHHEEEEEEEEE !!!!!!!!!!!!", Toast.LENGTH_LONG).show();

        mSharedPreferences = context.getSharedPreferences("mood_of_the_day", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        int backgroundcolor = mSharedPreferences.getInt("backgroundcolor", 0);
        String comment = mSharedPreferences.getString("comment", null);
        Mood_history newMood = new Mood_history(comment, backgroundcolor);

        String jsonInShared = mSharedPreferences.getString("json", "");
        Type mood_HistoryType = new TypeToken<ArrayList<Mood_history>>(){}.getType();
        ArrayList<Mood_history> historyList = gson.fromJson(jsonInShared, mood_HistoryType);


        if (historyList.size() > 6){
            historyList.remove(0);
            historyList.add(newMood);
            resetMood(gson , historyList);

        }else{

            historyList.add(newMood);
            resetMood(gson, historyList);

        }
    }
    private void resetMood(Gson gson, ArrayList<Mood_history> historyList){
        String json = gson.toJson(historyList);
        mSharedPreferences.edit()
                .putString("json", json)
                .putString("comment", "")
                .putInt("moodindex", 3)
                .apply();
}}

