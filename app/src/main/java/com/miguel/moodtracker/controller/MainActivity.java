package com.miguel.moodtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.miguel.moodtracker.R;
import com.miguel.moodtracker.model.Mood_Display;


public class MainActivity extends AppCompatActivity {

    protected ImageButton mWriteCommentaryButton;
    protected ImageButton mViewHistorybutton;
    protected ImageView mSmileyOfTheMood;
    public ConstraintLayout mLayout;
    protected GestureDetector mGestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mWriteCommentaryButton = findViewById(R.id.mood_write_comment);
        mViewHistorybutton = findViewById(R.id.mood_history);
        mSmileyOfTheMood = findViewById(R.id.smiley_of_the_mood);
        mLayout = findViewById(R.id.gestureDetector);

        Mood_Display display_Super_Happy = new Mood_Display(R.drawable.smiley_super_happy, R.color.background_superHappy);
        Mood_Display display_Happy = new Mood_Display(R.drawable.smiley_happy, R.color.background_happy);
        Mood_Display display_Normal = new Mood_Display(R.drawable.smiley_normal, R.color.background_ok);
        Mood_Display display_Ehh = new Mood_Display(R.drawable.smiley_disappointed, R.color.background_ehhh);
        Mood_Display display_Sad = new Mood_Display(R.drawable.smiley_sad, R.color.background_sad);

        Mood_Display[] moodTable  = {display_Sad, display_Ehh, display_Normal, display_Happy, display_Super_Happy};

        CustomGestureDetector customGestureDetector = new CustomGestureDetector(moodTable);
        mGestureDetector = new GestureDetector(this, customGestureDetector);
        mGestureDetector.setOnDoubleTapListener(customGestureDetector);


        mWriteCommentaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
