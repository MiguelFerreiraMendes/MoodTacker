package com.miguel.moodtracker.controller;

import android.content.Context;
import android.content.SharedPreferences;
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
    Context mContext;
    SharedPreferences mSharedPreferences;
    int mwidth;
    int mheight;


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
        myViewHolder.displaymoodHeight(mmoodlist.get(position), mheight / 4);


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

    void displaymoodWidth(Mood_history mood_history, int x){
        mBackgroundColor.setMaxWidth(x);
    }

    void displaymoodHeight(Mood_history mood_history, int x){
        mBackgroundColor.setMaxHeight(x);
    }
}

