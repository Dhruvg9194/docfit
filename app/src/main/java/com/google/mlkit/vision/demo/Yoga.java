package com.google.mlkit.vision.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Yoga extends AppCompatActivity {
    TextView TO;
    int counter = 0;
    int endTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga);
        TO = findViewById(R.id.timer_view);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
//        if(id==R.id.menu_contact){
//            Toast.makeText(this, "Opening Contact Us.", Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(this,MainActivity6.class);
//            startActivity(i);
//        }
//        if(id==R.id.menu_about){
//            Toast.makeText(this, "Opening About Us.", Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(this,MainActivity8.class);
//            startActivity(i);
//        }
        if(id==R.id.menu_home){
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
    public void startTimer(View view){
        SharedPreferences sp = getSharedPreferences("vals",MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        endTime = sp.getInt("timer",5);
        int endTimeFinal = endTime*1000*60;
        if(counter !=0){
            Toast.makeText(this, "Timer Already Running", Toast.LENGTH_SHORT).show();
        }
        else {
            new CountDownTimer(endTimeFinal, 1000) {
                public void onTick(long millisUntilFinished) {
                    int timef = endTime * 60 - counter;
                    int timem = (timef / 60);
                    int times = (timef - (timem * 60));
                    String stimem;
                    String stimes;
                    if (timem < 10) {
                        stimem = "0" + timem;
                    } else {
                        stimem = "" + timem;
                    }
                    if (times < 10) {
                        stimes = "0" + times;
                    } else {
                        stimes = "" + times;
                    }
                    TO.setText("" + stimem + " : " + stimes);
                    counter++;
                }

                public void onFinish() {
                    TO.setText("FINISH!!");
                    MediaPlayer mediaPlayer = MediaPlayer.create(Yoga.this, R.raw.chyme);
                    mediaPlayer.start();
                    counter = 0;
                    if (endTime < 30) {
                        endTime++;
                        ed.putInt("timer", endTime);
                        ed.apply();
                    }
                }

            }.start();
        }
    }
    public void reset(View view){
        recreate();
    }
}