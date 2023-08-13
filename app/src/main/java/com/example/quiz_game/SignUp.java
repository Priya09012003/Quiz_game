package com.example.quiz_game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    Toolbar toolbar;
    EditText signupEmail;
    EditText PasswordSignup;
    Button signupbtn;
    ProgressBar progressBar;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        toolbar = findViewById(R.id.toolbarf);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sign Up");
        signupEmail = findViewById(R.id.SignUpEmail);
        PasswordSignup = findViewById(R.id.SignUpPassword);
        signupbtn = findViewById(R.id.btnSignUp);
        progressBar = findViewById(R.id.progressBarID);

        progressBar.setVisibility(View.INVISIBLE);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupbtn.setClickable(false);
                String userEmail = signupEmail.getText().toString();
                String userPassword = PasswordSignup.getText().toString();

                if (!userEmail.isEmpty() && !userPassword.isEmpty()) {
                    signUpFirebase(userEmail, userPassword);
                } else {
                    Toast.makeText(SignUp.this, "Email and password cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void signUpFirebase(String userEmail, String userPassword) {
        progressBar.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "Your account is created", Toast.LENGTH_LONG).show();
                            finish();
                            progressBar.setVisibility(View.INVISIBLE);
                        } else {
                            Toast.makeText(SignUp.this, "There is a problem. Try again later", Toast.LENGTH_LONG).show();
                            Log.e("SignUp", "Error creating user: " + task.getException());
                        }
                    }
                });
    }
}
