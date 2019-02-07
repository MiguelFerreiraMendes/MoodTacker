package com.miguel.moodtracker.controller;

import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.miguel.moodtracker.model.Mood_Display;

import static android.support.constraint.Constraints.TAG;

public class CustomGestureDetector implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private ConstraintLayout mlayout;
    private ImageView msmileyOfTheMood;
    private Mood_Display[] mmoodTable;

    public CustomGestureDetector(Mood_Display[] moodTable) {
        mlayout = layout;
        msmileyOfTheMood = smileyOfTheMood;
        mmoodTable = moodTable;
    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        if (e1.getY() < e2.getY()) {
            Log.d(TAG, "Up to Down swipe performed");
        }

        if (e1.getY() > e2.getY()) {
            //
            return true;
        }

        return true;
    }
}
