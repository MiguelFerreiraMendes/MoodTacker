package com.miguel.moodtracker.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.miguel.moodtracker.R;

public class ViewHistoryActivity extends AppCompatActivity {

   private TextView j7;
   private TextView j6;
   private TextView j5;
   private TextView j4;
   private TextView j3;
   private TextView j2;
   private TextView j1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);

        j1 = findViewById(R.id.il_y_a_une_semaine);
        j2 = findViewById(R.id.il_y_a_six_jours);
        j3 = findViewById(R.id.il_y_a_cinq_jours);
        j4 = findViewById(R.id.il_y_a_quatre_jours) ;
        j5 = findViewById(R.id.il_y_a_trois_jours);
        j6 = findViewById(R.id.avant_hier);
        j7 = findViewById(R.id.hier);

        j1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        j2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        j3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        j4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        j5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
       j6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
       j7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
}}
