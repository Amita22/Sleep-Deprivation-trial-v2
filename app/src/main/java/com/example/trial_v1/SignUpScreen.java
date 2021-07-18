package com.example.trial_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpScreen extends AppCompatActivity {
//    static SignUpScreen INSTANCE;
    private EditText nameEditText, weightEditText, ageEditText, passwordEditText;
    private EditText emailEditText, stepGoalEditText;
    private ImageView picImageView;
    private CheckBox maleCheckBox, femaleCheckBox;
    private Button registerButton;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private static final String USERS = "users";
    private String TAG = "RegisterActivity";
    private String username, fname, email, weight, phone;
    private int age;
    private TextView reg;
    private String password;
    private User user;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        INSTANCE = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        nameEditText = findViewById(R.id.registerName);
        weightEditText = findViewById(R.id.registerProfession);
        ageEditText = findViewById(R.id.registerAge);
//        phoneEditText = findViewById(R.id.registerPhoneNumber);
        passwordEditText = findViewById(R.id.registerPassword);
        emailEditText = findViewById(R.id.registerEmailID);
//        stepGoalEditText = findViewById(R.id.registerStepGoal);
        picImageView = findViewById(R.id.signupImage);
//        maleCheckBox = findViewById(R.id.);
//        femaleCheckBox = findViewById(R.id.female_checkbox);
        registerButton = findViewById(R.id.registerButtonAndGetToLoginPage);
        reg = findViewById(R.id.LoginIfAlreadyHaveAccount);
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(USERS);
        mAuth = FirebaseAuth.getInstance();



//        reg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(SignUpScreen.this, LoginScreen.class));
//            }
//        });

//        picImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(SignUpScreen.this, LoginScreen.class));
//            }
//        });


//        reg.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(MotionEvent.ACTION_UP == event.getAction())
//                    startActivity(new Intent(SignUpScreen.this, LoginScreen.class));
//                return false;
//            }
//        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpScreen.this, TestActivity.class));
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //insert data into firebase database
                if(emailEditText.getText().toString() != null && passwordEditText.getText().toString() != null) {
                    fname = nameEditText.getText().toString();
                    email = emailEditText.getText().toString();
//                    phone = phoneEditText.getText().toString();
                    weight = weightEditText.getText().toString();
                    age = Integer.parseInt(ageEditText.getText().toString());
                    password = passwordEditText.getText().toString();

                    user = new User(fname, email, weight, phone, age);
                    registerUser();
                }
            }
        });







    }
    public void registerUser() {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
//    public static SignUpScreen getInstance(){
//        return INSTANCE;
//    }
//    public int getDataAge(){
//        return this.age;
//    }
//    public int getDataStepGoal(){
//        return this.stepGoal;
//    }
    public void updateUI(FirebaseUser currentUser) {
        String keyid = mDatabase.push().getKey();
        mDatabase.child(keyid).setValue(user); //adding user info to database
        startActivity(new Intent(SignUpScreen.this, MainActivity0.class));
    }
}
