package com.miguel.moodtracker.model;

public class Mood_history {

    private String mcomment;
    private int mbackgroundColor;


    public Mood_history(String comment, int backgroundColor) {

        mcomment = comment;
        mbackgroundColor = backgroundColor;

    }

    public String getmcomment() {
        return mcomment;
    }

    public int getmbackgroundColor() {
        return mbackgroundColor;
    }
}


