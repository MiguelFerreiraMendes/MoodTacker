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
import android.widget.Toast;

import com.miguel.moodtracker.R;
import com.miguel.moodtracker.model.Mood_history;

import java.util.List;

class MoodHistoryAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Mood_history> mmoodlist;
    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private int mwidth;
    private int mheight;
    private int index;



    MoodHistoryAdapter(List<Mood_history> moodlist, Context context){
        mmoodlist = moodlist;
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences("display size", Context.MODE_PRIVATE);
        mwidth = mSharedPreferences.getInt("width", 0);
        mheight = mSharedPreferences.getInt("height", 0);
        index = moodlist.size();

    }
    //we create a layoutinflater to get our view
    //we then create the view on our historycell_recycleview
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.historycell_recycleview, parent, false);
        return new MyViewHolder(view);
    }
    //Here is the method that is called every time we create a cell
    //we need the instance of MyViewHolder and the current position in the list
    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {

        myViewHolder.displayColor(mmoodlist.get(position), mContext);
        myViewHolder.displaymoodWidth(mmoodlist.get(position), mwidth, mContext);
        myViewHolder.displaymoodHeight(mheight /7);
        myViewHolder.displayText(index);
        myViewHolder.displayCommentButton(mmoodlist.get(position), mContext);
        index--;



    }

    //Method to get back the size of our list
    @Override
     public int getItemCount() {
         return mmoodlist.size();
     }
}

//MyViewHolder is the model of our cell
class MyViewHolder extends RecyclerView.ViewHolder {

    private TextView mXjours;
    private ImageButton mCommentButton;
    private ConstraintLayout mBackgroundColor;

    MyViewHolder(View itemView){
        super(itemView);

        mBackgroundColor = itemView.findViewById(R.id.backgroundcolor);
        mCommentButton = itemView.findViewById(R.id.comment_button);
        mXjours = itemView.findViewById(R.id.Il_y_a_x_Temps);

    }

    //Method to display the color
    void displayColor(Mood_history mood_history, Context context){
        mBackgroundColor.setBackgroundColor(context.getResources().getColor(mood_history.getmbackgroundColor()));

    }
    //Method to display the comment.
    //if there is a comment, we put a clickListener on the mCommentbutton and display it if pressed
    //if there is no comment, we just put the visibility of mCommentButton to invisible
    void displayCommentButton(Mood_history mood_history, final Context context){
        final String comment = mood_history.getmcomment();
        if (!comment.equals("")){
            mCommentButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, comment, Toast.LENGTH_LONG).show();
                }

            });
        }else{
            mCommentButton.setVisibility(View.INVISIBLE);
        }

    }
    //Method to display the width of each mood
    //We just set the max width depending of the color of the mood
    //For example for Sad (-2212784), we set the MaxWidth at 35%
    void displaymoodWidth(Mood_history mood_history, int width, Context context){
        int color = (context.getResources().getColor(mood_history.getmbackgroundColor()));
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
    //Method to display the Height of the moods
    void displaymoodHeight(int x){
        mBackgroundColor.setMinHeight(x);
    }

    //Method to display the correct text of our history with the help of an index
    void displayText(int index){
        switch (index)
        {
            case 7:
                mXjours.setText("Il y a une semaine");
                break;
            case 6:
                mXjours.setText("Il y a six jours");
                break;
            case 5:
                mXjours.setText("Il y a cinq jours");
                break;
            case 4:
                mXjours.setText("Il y a quatre jours");
                break;
            case 3:
                mXjours.setText("Il y a trois jours");
                break;
            case 2:
                mXjours.setText("Avant-hier");
                break;
            case 1:
                mXjours.setText("Hier");
                break;
            default:
                mXjours.setText("Fail");
        }

    }

}

