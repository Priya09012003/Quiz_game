package com.example.quiz_game;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class login_activity extends AppCompatActivity {

    EditText mail;
    EditText password;
    Button signIn;
    SignInButton signInGoogle;
    TextView signUp;
    TextView forgotPassword;
    ProgressBar progressBarlogin;
   GoogleSignInClient googleSignInClient;

    FirebaseAuth auth =FirebaseAuth.getInstance();

    ActivityResultLauncher<Intent>activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerActivityForGoogleSignIn();
        setContentView(R.layout.activity_login);
        mail=findViewById(R.id.LoginEmail);
        password=findViewById(R.id.LoginPassword);
        signIn=findViewById(R.id.btnsignIn);
        signInGoogle=findViewById(R.id.signInButtongoogle);
        signUp=findViewById(R.id.signup);
        forgotPassword=findViewById(R.id.forgotPass);
        progressBarlogin=findViewById(R.id.progressBarSignIn);


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userEmail =mail.getText().toString();
                String userPassword =password.getText().toString();
                signInWithFirebase(userEmail,userPassword);
            }
        });

        signInGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signInGoogle();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login_activity.this,SignUp.class);
                startActivity(intent);
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(login_activity.this, Forget_Activity.class);
                startActivity(i);

            }
        });

    }

    private void signInGoogle() {
        GoogleSignInOptions gso =new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("34511378423-ghdj4i0bv03sf4gmlvoicarvjmpqcmfi.apps.googleusercontent.com")
                .requestEmail().build();

        googleSignInClient = GoogleSignIn.getClient(this,gso);
        signIn();
    }

    private void signIn() {
        Intent signInIntent =googleSignInClient.getSignInIntent();
        activityResultLauncher.launch(signInIntent);
    }

    public void  registerActivityForGoogleSignIn()
    {
        activityResultLauncher
                =registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
                , new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        int resultCode =result.getResultCode();
                        Intent data =result.getData();

                        if(resultCode ==RESULT_OK && data !=null){
                            Task<GoogleSignInAccount>task =GoogleSignIn.getSignedInAccountFromIntent(data);
                            firebasesignInwithGoogle(task);
                        }


                    }
                });
    }

    private void firebasesignInwithGoogle(Task<GoogleSignInAccount> task) {

        try {
            GoogleSignInAccount account =task.getResult(ApiException.class);
            Toast.makeText(this,"Successful",Toast.LENGTH_SHORT).show(); 
            Intent i = new Intent(login_activity.this,MainActivity.class);
            startActivity(i);
            finish();
            firebaseGoogleAccount(account);
        } catch (ApiException e) {
            e.printStackTrace();
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }

    }

    private void firebaseGoogleAccount(GoogleSignInAccount account) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        auth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                }else {

                }

            }
        });
    }

    public void signInWithFirebase(String userEmail,String userPassword)
    {
        progressBarlogin.setVisibility(View.VISIBLE);
        signIn.setClickable(false);

        auth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Intent i=new Intent(login_activity.this,MainActivity.class);
                            startActivity(i);
                            finish();
                            progressBarlogin.setVisibility(View.INVISIBLE);
                            Toast.makeText(login_activity.this,"Sign is Successful",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(login_activity.this,"Sign In is not successful"
                            ,Toast.LENGTH_SHORT).show();;
                        }
                    }
                });
    }
    protected void onStart() {

        super.onStart();

        FirebaseUser user = auth.getCurrentUser();
        if(user!=null)
        {
            Intent i =new Intent(login_activity.this,MainActivity.class);
            startActivity(i);
            finish();
        }
    }


}