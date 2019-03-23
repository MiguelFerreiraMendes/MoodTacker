package com.miguel.moodtracker.model;

public class Mood_history {

    private String mcomment;
    private int mbackgroundColor;
    private Long mdate;


    public Mood_history(String comment, int backgroundColor, Long date) {

        mcomment = comment;
        mbackgroundColor = backgroundColor;
        mdate = date;
    }

    public Long getMdate() {
        return mdate;
    }

    public String getmcomment() {
        return mcomment;
    }

    public int getmbackgroundColor() {
        return mbackgroundColor;
    }
}


