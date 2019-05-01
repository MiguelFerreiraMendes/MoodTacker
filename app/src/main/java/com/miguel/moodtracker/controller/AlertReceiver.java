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

        mSharedPreferences = context.getSharedPreferences("mood_of_the_day", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        //We have to create a Type to get back our Array of Mood_history.

        String jsonInShared = mSharedPreferences.getString("json", "");
        Type mood_HistoryType = new TypeToken<ArrayList<Mood_history>>(){}.getType();
        ArrayList<Mood_history> historyList = gson.fromJson(jsonInShared, mood_HistoryType);

        //We checked the size of our ArrayList of mood_history
        //We need the history for 7 days only so
        //if its size is > 6, we have to delete the most ancient Mood_history.
        //If its size isn't > 6, we just register it.

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
    //Method that we call after checking the size of our ArrayList
    //We just get from the sharedpref the backgroundcolor and the comment if there is one.
    //Then we create a Mood_history with it
    private Mood_history addMood(){
        int backgroundcolor = mSharedPreferences.getInt("backgroundcolor", 0);
        String comment = mSharedPreferences.getString("comment", null);
        Mood_history newMood = new Mood_history(comment, backgroundcolor);
        return newMood;
    }
    //Method that we call after addMood.
    //We convert the new json String into a Json
    //We then put the defaults values of our elements in the sharedPref
    private void resetMood(Gson gson, ArrayList<Mood_history> historyList){
        String json = gson.toJson(historyList);
        mSharedPreferences.edit()
                .putString("json", json)
                .putString("comment", "")
                .putInt("moodindex", 3)
                .apply();

}}

