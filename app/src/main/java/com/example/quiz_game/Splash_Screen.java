package com.example.quiz_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_Screen extends AppCompatActivity {

    ImageView img;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        img=findViewById(R.id.isplash_img);
        txt=findViewById(R.id.splash_txt);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.splash_anim);
        txt.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i =new Intent(Splash_Screen.this,login_activity.class);
                startActivity(i);
                finish();
            }
        },5000);
    }
}