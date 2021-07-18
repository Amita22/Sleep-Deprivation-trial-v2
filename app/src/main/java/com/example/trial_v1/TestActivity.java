package com.example.trial_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class TestActivity extends AppCompatActivity {
EditText emailField, PwdField;
//FloatingActionButton fb;
Button login;
TextView register, fgtPwd;
private CoordinatorLayout mSnackbarLayout;
FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //fb = findViewById(R.id.noAccount);
        emailField = findViewById(R.id.LoginEmailID);
        PwdField = findViewById(R.id.LoginPasswordID);
        login = findViewById(R.id.btnLoginToSystem);
        register = findViewById(R.id.btnRegisterInTheSystem);
        fgtPwd = findViewById(R.id.btnForgotPassword);
        mSnackbarLayout = findViewById(R.id.snackbar_layout);
        mAuth = FirebaseAuth.getInstance();

        fgtPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar snackbar = Snackbar.make(v, "STILL IN DEVELOPMENT", Snackbar.LENGTH_LONG);
                snackbar.setDuration(10000);
//                snackbar.setAnchorView(mFloatingActionButton);;
                snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
//                snackbar.setAction("OKAY", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // Do Something here
//                    }
//                });
                snackbar.show();

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestActivity.this, SignUpScreen.class));
            }
        });

//        fb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), SignUpScreen.class));
//            }
//        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailField.getText().toString();
                String pwd = PwdField.getText().toString();
                mAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(TestActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(TestActivity.this, MainActivity0.class));
                        }
                        else{
                            Toast.makeText(TestActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });






    }
}