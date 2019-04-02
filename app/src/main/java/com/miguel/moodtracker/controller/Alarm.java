package com.miguel.moodtracker.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PowerManager;


import static android.content.Context.MODE_PRIVATE;

public class Alarm extends BroadcastReceiver
{
    SharedPreferences mSharedPreferences;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        mSharedPreferences = context.getSharedPreferences("mood_of_the_day", MODE_PRIVATE);
        String mComment = mSharedPreferences.getString("comment", "");
        int mBackgroundColor = mSharedPreferences.getInt("backgroundcolor", 0);
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "myapp:mywakelocktag");
        wl.acquire();

        mSharedPreferences.edit()
                .putString("comment_of_the_day", mComment)
                .putInt("background_of_the_day", mBackgroundColor)
                .apply();

        wl.release();
    }


    public void setAlarm(Context context)
    {
        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60 * 60 * 24, pi); // Millisec * Second * Minute * hours
    }
}
