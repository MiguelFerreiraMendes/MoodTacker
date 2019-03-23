package com.miguel.moodtracker.controller;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.miguel.moodtracker.R;
import com.miguel.moodtracker.model.Mood_history;

import java.util.List;

class MoodHistoryAdapter extends RecyclerView.Adapter<MyViewHolder> {

    List<Mood_history> mmoodlist;

    MoodHistoryAdapter(List<Mood_history> moodlist){
        mmoodlist = moodlist;
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
}

