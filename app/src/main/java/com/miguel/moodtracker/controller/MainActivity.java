package com.miguel.moodtracker.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        setContentView(R.layout.activity_main);


        mWriteCommentaryButton = findViewById(R.id.mood_write_comment);
        mViewHistorybutton = findViewById(R.id.mood_history);
        mSmileyOfTheMood = findViewById(R.id.smiley_of_the_mood);
        mLayout = findViewById(R.id.gestureDetector);
        mSharedPreferences = this.getSharedPreferences("mood_of_the_day", MODE_PRIVATE);
        long timestamp = System.currentTimeMillis();
        Log.d("MainActivity", "Current Timestamp: " + timestamp);





        Mood_Display display_Super_Happy = new Mood_Display(R.drawable.smiley_super_happy, R.color.background_superHappy);
        Mood_Display display_Happy = new Mood_Display(R.drawable.smiley_happy, R.color.background_happy);
        Mood_Display display_Normal = new Mood_Display(R.drawable.smiley_normal, R.color.background_ok);
        Mood_Display display_Ehh = new Mood_Display(R.drawable.smiley_disappointed, R.color.background_ehhh);
        Mood_Display display_Sad = new Mood_Display(R.drawable.smiley_sad, R.color.background_sad);



        List <Mood_Display> MoodDisplayList = new ArrayList<>();
        MoodDisplayList.add(0, display_Sad);
        MoodDisplayList.add(1, display_Ehh);
        MoodDisplayList.add(2, display_Normal);
        MoodDisplayList.add(3,display_Happy);
        MoodDisplayList.add(4,display_Super_Happy);



        Log.i("swipe", "index dans le main, initialisation" + mSharedPreferences.getInt("moodindex", 0));

        Gson gson = new Gson();
       // Mood_history registerHistory = new Mood_history(m_commentary, MoodDisplayList.get(mSharedPreferences.getInt("moodindex", 3)).getbackgroundColor(), timestamp);
       // String json = gson.toJson(registerHistory);
        String json = "{\"moodLevel\":" + MoodDisplayList.get(mSharedPreferences.getInt("moodindex", 3)).getbackgroundColor() +
                ",\"commentary\":" + m_commentary + ",\"date\":" + timestamp +"}";
        Mood_history mood_history = gson.fromJson(json, Mood_history.class);

        mSharedPreferences.edit()
                .putString("comment", m_commentary)
                .putInt("backgroundcolor", MoodDisplayList.get(mSharedPreferences.getInt("moodindex", 3)).getbackgroundColor())
                .putInt("moodindex", 3)
                .putLong("timestamp", timestamp)
                .apply();
        



        CustomGestureDetector customGestureDetector = new CustomGestureDetector(MoodDisplayList, mLayout, mSmileyOfTheMood, this);
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
                Intent historyIntent = new Intent(MainActivity.this, ViewHistoryActivity.class);
                startActivity(historyIntent);
           }});

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }













}
