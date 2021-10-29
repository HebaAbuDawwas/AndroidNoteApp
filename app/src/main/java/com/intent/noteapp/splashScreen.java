package com.intent.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splashScreen extends AppCompatActivity {
   Handler h = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    h.postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent in = new Intent(splashScreen.this,MainActivity.class);
            startActivity(in);
            finish();
        }
    },3000);
    }
}