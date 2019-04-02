package com.miguel.moodtracker.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.miguel.moodtracker.R;
import com.miguel.moodtracker.model.Mood_Display;
import com.miguel.moodtracker.model.Mood_history;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    protected ImageButton mWriteCommentaryButton;
    protected ImageButton mViewHistorybutton;
    protected ImageView mSmileyOfTheMood;
    public ConstraintLayout mLayout;
    protected GestureDetector mGestureDetector;
    private String m_commentary;
    SharedPreferences mSharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
        setContentView(R.layout.activity_main);

        mWriteCommentaryButton = findViewById(R.id.mood_write_comment);
        mViewHistorybutton = findViewById(R.id.mood_history);
        mSmileyOfTheMood = findViewById(R.id.smiley_of_the_mood);
        mLayout = findViewById(R.id.gestureDetector);
        mSharedPreferences = this.getSharedPreferences("mood_of_the_day", MODE_PRIVATE);
        long timestamp = System.currentTimeMillis();
        Log.d("MainActivity", "Current Timestamp: " + timestamp);

        final MediaPlayer superHappyNote = MediaPlayer.create(this, R.raw.superhappy);
        final MediaPlayer happyNote = MediaPlayer.create(this, R.raw.happy);
        final MediaPlayer okNote = MediaPlayer.create(this, R.raw.ok);
        final MediaPlayer ehhhNote = MediaPlayer.create(this, R.raw.ehhh);
        final MediaPlayer sadNote = MediaPlayer.create(this, R.raw.sad);

        final List <MediaPlayer> MediaplayerList = new ArrayList<>();
        MediaplayerList.add(0, sadNote);
        MediaplayerList.add(1, ehhhNote);
        MediaplayerList.add(2, okNote);
        MediaplayerList.add(3, happyNote);
        MediaplayerList.add(4, superHappyNote);


        Mood_Display display_Super_Happy = new Mood_Display(R.drawable.smiley_super_happy, R.color.background_superHappy);
        Mood_Display display_Happy = new Mood_Display(R.drawable.smiley_happy, R.color.background_happy);
        Mood_Display display_Normal = new Mood_Display(R.drawable.smiley_normal, R.color.background_ok);
        Mood_Display display_Ehh = new Mood_Display(R.drawable.smiley_disappointed, R.color.background_ehhh);
        Mood_Display display_Sad = new Mood_Display(R.drawable.smiley_sad, R.color.background_sad);



        final List <Mood_Display> MoodDisplayList = new ArrayList<>();
        MoodDisplayList.add(0, display_Sad);
        MoodDisplayList.add(1, display_Ehh);
        MoodDisplayList.add(2, display_Normal);
        MoodDisplayList.add(3,display_Happy);
        MoodDisplayList.add(4,display_Super_Happy);



        Log.i("swipe", "index dans le main, initialisation" + mSharedPreferences.getInt("moodindex", 0));


        Gson gson = new Gson();
        Mood_history[] historyList = new Mood_history[7];
        historyList[0]= (new Mood_history("blabla 1", R.color.background_happy));
        historyList[1]= (new Mood_history("blabla 2", R.color.background_sad));
        historyList[2]= (new Mood_history("blabla 3", R.color.background_ehhh));
        historyList[3]= (new Mood_history("blabla 4", R.color.background_superHappy));
        historyList[4]= (new Mood_history("blabla 5", R.color.background_ok));
        historyList[5]= (new Mood_history("blabla 6", R.color.background_sad));
        historyList[6]= (new Mood_history("blabla 7", R.color.background_superHappy));
        String json = gson.toJson(historyList);


        Log.i("json", "json apres création" + json);


        mSharedPreferences.edit()
                .putString("comment", m_commentary)
                .putInt("backgroundcolor", MoodDisplayList.get(mSharedPreferences.getInt("moodindex", 3)).getbackgroundColor())
                .putInt("moodindex", 3)
                .putLong("timestamp", timestamp)
                .putString("json", json)
                .apply();
        Log.i("index", ""+mSharedPreferences.getInt("moodindex", 0));
        Log.i("json", "json des shared pref" + mSharedPreferences.getString("json", ""));





        CustomGestureDetector customGestureDetector = new CustomGestureDetector(MoodDisplayList, mLayout, mSmileyOfTheMood, this, MediaplayerList);
        mGestureDetector = new GestureDetector(this, customGestureDetector);
        mGestureDetector.setOnDoubleTapListener(customGestureDetector);
        mWriteCommentaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Commentaire");
                builder.setCancelable(true);

                final EditText commentary = new EditText(MainActivity.this);
                commentary.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(commentary);

                builder.setPositiveButton(
                        "Valider",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                m_commentary = commentary.getText().toString();
                            }
                        });

                builder.setNegativeButton(
                        "Annuler",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog write_commentary = builder.create();
                write_commentary.show();



            }
        });
        mViewHistorybutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent historyIntent = new Intent(MainActivity.this, View_history_recycler_view.class);
                startActivity(historyIntent);
           }});


    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }













}
