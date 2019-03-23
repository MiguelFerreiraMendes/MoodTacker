package com.miguel.moodtracker.controller;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.miguel.moodtracker.R;
import com.miguel.moodtracker.model.Mood_history;


import java.util.ArrayList;
import java.util.Map;


public class View_history_recycler extends AppCompatActivity {

    private ListView mListView;
    private MoodAdapter mMoodAdapter;
    private ArrayList<Mood_history> moodlist;
    private SharedPreferences mJson;
    private Map<String, ?> mjsontest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history_recycler);

        long timestamp = System.currentTimeMillis();

        mListView = findViewById(R.id.ListView);
        mJson = getSharedPreferences("json", MODE_PRIVATE);
        Log.i("json", "json dans la seconde activité quand on viens de le récup" + mJson);

        if (mJson != null) {
            Mood_history jsonMood = new Mood_history(mJson.getString("commentary", ""), mJson.getInt("moodLevel", 3), mJson.getLong("date", 0));
            Log.i("json", "json dans la seconde activité quand on le récup avec les clefs" + jsonMood);
            Log.i("json", "json getbackground" + jsonMood.getmbackgroundColor());
            Log.i("json", "json get comment" + jsonMood.getmcomment());
            Log.i("json", "json get time" + jsonMood.getMdate());

        }









       // SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
       // String mood = mPreferences.getString("json", "aaa");
       // Log.i("test", "mood quand on viens de le récup" + mood);


        mMoodAdapter = new MoodAdapter(getApplicationContext(), 0, moodlist);


        moodlist = new ArrayList<>();
        mMoodAdapter.add(new Mood_history("Happy",getResources().getColor(R.color.background_happy), timestamp));
        mMoodAdapter.add(new Mood_history("Sad",getResources().getColor(R.color.background_sad), timestamp));
        mMoodAdapter.add(new Mood_history("Super Happy",getResources().getColor(R.color.background_superHappy), timestamp));
        mMoodAdapter.add(new Mood_history("Ehhh",getResources().getColor(R.color.background_ehhh), timestamp));
        mMoodAdapter.add(new Mood_history("Ok",getResources().getColor(R.color.background_ok), timestamp));
        mMoodAdapter.add(new Mood_history("Sad",getResources().getColor(R.color.background_sad), timestamp));
        mMoodAdapter.add(new Mood_history("Super Happy",getResources().getColor(R.color.background_superHappy), timestamp));
        mMoodAdapter.add(new Mood_history("Happy",getResources().getColor(R.color.background_happy), timestamp));
        mMoodAdapter.add(new Mood_history("Sad",getResources().getColor(R.color.background_sad), timestamp));
        mMoodAdapter.add(new Mood_history("Super Happy",getResources().getColor(R.color.background_superHappy), timestamp));
        mMoodAdapter.add(new Mood_history("Ehhh",getResources().getColor(R.color.background_ehhh), timestamp));
        mMoodAdapter.add(new Mood_history("Ok",getResources().getColor(R.color.background_ok), timestamp));
        mMoodAdapter.add(new Mood_history("Sad",getResources().getColor(R.color.background_sad), timestamp));
        mMoodAdapter.add(new Mood_history("Super Happy",getResources().getColor(R.color.background_superHappy), timestamp));
        mMoodAdapter.add(new Mood_history("Happy",getResources().getColor(R.color.background_happy), timestamp));
        mMoodAdapter.add(new Mood_history("Sad",getResources().getColor(R.color.background_sad), timestamp));
        mMoodAdapter.add(new Mood_history("Super Happy",getResources().getColor(R.color.background_superHappy), timestamp));
        mMoodAdapter.add(new Mood_history("Ehhh",getResources().getColor(R.color.background_ehhh), timestamp));
        mMoodAdapter.add(new Mood_history("Ok",getResources().getColor(R.color.background_ok), timestamp));
        mMoodAdapter.add(new Mood_history("Sad",getResources().getColor(R.color.background_sad), timestamp));
        mMoodAdapter.add(new Mood_history("Super Happy",getResources().getColor(R.color.background_superHappy), timestamp));

        //on affecte notre adapteur a notre liste de mood

        mListView.setAdapter(mMoodAdapter);
        mMoodAdapter.addAll(moodlist);









    }
}
