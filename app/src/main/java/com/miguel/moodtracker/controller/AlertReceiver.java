package com.miguel.moodtracker.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

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

        String jsonInShared = mSharedPreferences.getString("json", "");
        Type mood_HistoryType = new TypeToken<ArrayList<Mood_history>>(){}.getType();
        ArrayList<Mood_history> historyList = gson.fromJson(jsonInShared, mood_HistoryType);


        if (historyList.size() > 6){
            historyList.remove(0);
            Mood_history newMood = addMood();
            historyList.add(newMood);
            resetMood(gson , historyList);

        }else{
            Mood_history newMood = addMood();
            historyList.add(newMood);
            resetMood(gson, historyList);

        }
    }

    private Mood_history addMood(){
        int backgroundcolor = mSharedPreferences.getInt("backgroundcolor", 0);
        String comment = mSharedPreferences.getString("comment", null);
        Mood_history newMood = new Mood_history(comment, backgroundcolor);
        return newMood;
    }

    private void resetMood(Gson gson, ArrayList<Mood_history> historyList){
        String json = gson.toJson(historyList);
        Log.i("index", "index au lancement du reset de l'alarm" + mSharedPreferences.getInt("moodindex", 3));
        mSharedPreferences.edit()
                .putString("json", json)
                .putString("comment", "")
                .putInt("moodindex", 3)
                .apply();
        Log.i("index", "index a la fin du reset de l'alarm" + mSharedPreferences.getInt("moodindex", 3));
}}

