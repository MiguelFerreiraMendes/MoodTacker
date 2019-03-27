package com.miguel.moodtracker.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.miguel.moodtracker.R;
import com.miguel.moodtracker.model.Mood_history;

import java.util.List;

class MoodHistoryAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Mood_history> mmoodlist;
    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private int mwidth;
    private int mheight;
    private int index = 0;


    MoodHistoryAdapter(List<Mood_history> moodlist, Context context){
        mmoodlist = moodlist;
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences("display size", Context.MODE_PRIVATE);
        mwidth = mSharedPreferences.getInt("width", 0);
        mheight = mSharedPreferences.getInt("height", 0);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.historycell_recycleview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        myViewHolder.displayColor(mmoodlist.get(position));
        myViewHolder.displaymoodWidth(mmoodlist.get(position), mwidth);
        myViewHolder.displaymoodHeight(mheight /7);
        myViewHolder.displayText(index);
        index++;


    }


    @Override
     public int getItemCount() {
         return mmoodlist.size();
     }
}

class MyViewHolder extends RecyclerView.ViewHolder {

    //view holder = Modeles pour nos cellules

    private TextView mXjours;
    private ImageButton mCommentButton;
    private ConstraintLayout mBackgroundColor;

    MyViewHolder(View itemView){
        super(itemView);

        mBackgroundColor = itemView.findViewById(R.id.backgroundcolor);
        mCommentButton = itemView.findViewById(R.id.comment_button);
        mXjours = itemView.findViewById(R.id.Il_y_a_x_Temps);

    }


    void displayColor(Mood_history mood_history){
        mBackgroundColor.setBackgroundColor(mood_history.getmbackgroundColor());
    }

    void displaymoodWidth(Mood_history mood_history, int width){
        int color = mood_history.getmbackgroundColor();
        Log.i("color", "couleur dans la fonction" + color);
        switch (color)
        {
            case -398257:
                mBackgroundColor.setMaxWidth(width);
                break;
            case -4658810:
                mBackgroundColor.setMaxWidth((width / 100)* 90);
                break;
            case -1522103591:
                mBackgroundColor.setMaxWidth((width / 100)* 75);
                break;
            case -6579301:
                mBackgroundColor.setMaxWidth((width / 100)* 50);
                break;
            case -2212784:
                mBackgroundColor.setMaxWidth((width / 100)* 35);
                break;
            default:
                mBackgroundColor.setMaxWidth(width);
        }
    }

    void displaymoodHeight(int x){
        mBackgroundColor.setMinHeight(x);
    }

    void displayText(int index){
        switch (index)
        {
            case 0:
                mXjours.setText("Il y a une semaine");
                break;
            case 1:
                mXjours.setText("Il y a six jours");
                break;
            case 2:
                mXjours.setText("Il y a cinq jours");
                break;
            case 3:
                mXjours.setText("Il y a quatre jours");
                break;
            case 4:
                mXjours.setText("Il y a trois jours");
                break;
            case 5:
                mXjours.setText("Avant-hier");
                break;
            case 6:
                mXjours.setText("Hier");
                break;
            default:
                mXjours.setText("Fail");
        }

    }

}

