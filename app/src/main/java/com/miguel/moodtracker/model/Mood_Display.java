package com.miguel.moodtracker.model;



public class Mood_Display {

    private int smiley;
    private int backgroundColor;

    public Mood_Display(int msmiley, int mbackgroundColor) {
        this.smiley = msmiley;
        this.backgroundColor = mbackgroundColor;
    }


    public int getsmiley() {
        return smiley;
    }

    public int getbackgroundColor() {
        return backgroundColor;
    }


}
