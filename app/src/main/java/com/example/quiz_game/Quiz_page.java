package com.example.quiz_game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Quiz_page extends AppCompatActivity {

    TextView time,correct,wrong;
    TextView question ,a,b,c,d;
    AppCompatButton next ,finish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);
        time=findViewById(R.id.time);
        correct=findViewById(R.id.correctans);
        wrong=findViewById(R.id.wrongans);
        question=findViewById(R.id.question);
        a=findViewById(R.id.a);
        b=findViewById(R.id.b);
        c=findViewById(R.id.c);
        d=findViewById(R.id.d);
        next=findViewById(R.id.nextQuestion);
        finish=findViewById(R.id.FinishGame);


        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}