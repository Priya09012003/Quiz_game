package com.example.quiz_game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forget_Activity extends AppCompatActivity {
    Toolbar toolbar;
    EditText Email;
    AppCompatButton btn;

    ProgressBar progressBar;

    FirebaseAuth auth =FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        toolbar = findViewById(R.id.toolbarf);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Forgot Password");

        Email=findViewById(R.id.editTextTextEmailAddress);
        btn=findViewById(R.id.btnContinue);
        progressBar=findViewById(R.id.progressBarzf);


       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                String userEmail = Email.getText().toString();
                resetPassword(userEmail);
           }
       });

    }

    public  void  resetPassword(String userEmail)
    {
        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(userEmail).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Forget_Activity.this,
                            "we sent you an email to reset your password",Toast.LENGTH_LONG).show();
                    btn.setClickable(false);
                    progressBar.setVisibility(View.INVISIBLE);
                    finish();
                }
                else
                {
                    Toast.makeText(Forget_Activity.this,"Sorry,there is a problem,please try again later...",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}