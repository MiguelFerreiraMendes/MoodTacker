package com.miguel.moodtracker.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.miguel.moodtracker.R;
import com.miguel.moodtracker.model.Mood_history;

import java.util.ArrayList;

//class générique d'une cellule

public class MoodAdapter extends ArrayAdapter<Mood_history> {

    private Context mContext;
    private ArrayList mMoodList;

    public MoodAdapter(Context context, int resource, ArrayList<Mood_history> moodlist) {
        super(context, resource);
        mContext = context;
        mMoodList = moodlist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // on crée un objet de type view, il permet d'avoir la vue sur la cellule
        View v;


        //Layoutinflater permet d'aller chercher notre modèle de cellule
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = layoutInflater.inflate(R.layout.history_cell, null);

        Mood_history currentMood = getItem(position);

        LinearLayout backgroundcolor = v.findViewById(R.id.backgroundcolor);
        ImageButton imagebutton = v.findViewById(R.id.imageButton);
        TextView textView = v.findViewById(R.id.il_y_a_Xjours);

        backgroundcolor.setBackgroundColor(currentMood.getmbackgroundColor());
        textView.setText("Hier");
        imagebutton.setImageResource(R.drawable.ic_comment_black_48px);

        return v;



    }
}
