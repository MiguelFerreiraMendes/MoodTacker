package com.miguel.moodtracker.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.miguel.moodtracker.model.Mood_Display;
import java.util.List;


public class CustomGestureDetector implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private List<Mood_Display> mmoodList;
    private List<MediaPlayer> mMediaPlayerList;
    private MediaPlayer mcurrentNote;
    private ConstraintLayout mlayout;
    private ImageView msmileyOfTheMood;
    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private int index;


    protected CustomGestureDetector(List<Mood_Display> moodDisplayList, ConstraintLayout layout, ImageView smileyOfTheMood, Context context, List<MediaPlayer> mediaPlayerlist) {
        mmoodList = moodDisplayList;
        mMediaPlayerList = mediaPlayerlist;
        mlayout = layout;
        msmileyOfTheMood = smileyOfTheMood;
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences("mood_of_the_day", Context.MODE_PRIVATE);
        Log.i("swipe", "nouvelle index dans le gesture a la création" + mSharedPreferences.getInt("moodindex", 0));
    }

    //We create a method to find the indexcolor but with "getRessources"
    private int findResourceColor(int indexcolor) {
        int color = mContext.getResources().getColor(mmoodList.get(indexcolor).getbackgroundColor());
        return color;
    }


    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        try {

            //On swipe up
            //We got back the "moodindex" of the sharedPref
            //We set the backgroundColor and the Smiley with the index + 1
            //With the index, we also get our Mediaplayer from our MediaplayerList and then play it
            //The index + 1 is now the new index and
            //we make sure we register the new index in the sharedPref and the new backgoundcolor

            if (e1.getY() < e2.getY()) {
                index = mSharedPreferences.getInt("moodindex", 3);
                mlayout.setBackgroundColor(findResourceColor(index + 1));
                msmileyOfTheMood.setImageResource(mmoodList.get(index + 1).getsmiley());
                mcurrentNote = mMediaPlayerList.get(index + 1);
                mcurrentNote.start();
                index = index + 1;
                mSharedPreferences.edit()
                        .putInt("moodindex", index)
                        .putInt("backgroundcolor", mmoodList.get(index).getbackgroundColor())
                        .apply();
                Log.i("swipe", "nouvelle index dans le gesture apres swipe haut " + mSharedPreferences.getInt("moodindex", 0));


                return true;
            }

            if (e1.getY() > e2.getY()) {
                index = mSharedPreferences.getInt("moodindex", 3);
                mlayout.setBackgroundColor(findResourceColor(index - 1));
                msmileyOfTheMood.setImageResource(mmoodList.get(index - 1).getsmiley());
                mcurrentNote = mMediaPlayerList.get(index - 1);
                mcurrentNote.start();
                index = index - 1;
                mSharedPreferences.edit()
                        .putInt("moodindex", index)
                        .putInt("backgroundcolor", mmoodList.get(index).getbackgroundColor())
                        .apply();
                Log.i("swipe", "nouvelle index dans le gesture apres swipe bas " + mSharedPreferences.getInt("moodindex", 0));


                return true;
            }
        //Exception to catch if we swipe out of the index
        }catch (IndexOutOfBoundsException index_out_of_bounds){
            if (index >= 4)
            {
                index = 4;
            }
            else
            {
                index = 0;
            }
        }
        return true;
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
}
