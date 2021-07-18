package com.example.trial_v1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.*;

public class MainActivity3 extends AppCompatActivity implements DialogInterface.OnClickListener{
    public String stepGoalText = "";
    static String[] scoresList = {"Step Score", "Sentiment Score", "Active Score"};
    int maximumDistance, maximumCalorie, maximumStepCount;
    ArrayList<String> distances = new ArrayList<>();
    ArrayList<Integer> calories = new ArrayList<>();
    ArrayList<Integer> stepCounts = new ArrayList<>();
    TextView tv1, tv5, tv7, tv8;
    Button alertBtn;
    ProgressBar progr1, progr2, progr3;
    ImageView c1, c2, c3, w1, w2, w3;
    FloatingActionButton fab;
//    int ageReceived, stepGoalReceived;
//    User_Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        fab = findViewById(R.id.returnToMenu);
        alertBtn = findViewById(R.id.btnDisplayAlert);
        tv5 = findViewById(R.id.textView5);
        tv8 = findViewById(R.id.textView8);
        tv7 = findViewById(R.id.textView7);
        progr1 = findViewById(R.id.progressBar2);
        progr2 = findViewById(R.id.progressBar3);
        progr3 = findViewById(R.id.progressBar4);
        c1 = findViewById(R.id.imageView2);
        c2 = findViewById(R.id.imageView3);
        c3 = findViewById(R.id.imageView4);
        w1 = findViewById(R.id.imageView5);
        w2 = findViewById(R.id.imageView6);
        w3 = findViewById(R.id.imageView7);
//        data = new User_Data();
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("user1");
        for(int i = 0; i <= 3; i++){
            dbref = dbref.child(""+i);
            dbref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                   for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                       User_Data data = (User_Data) dataSnapshot.getValue();
//                       distances.add((data.getDistance()));
                   }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
//                int ageRec = Integer.parseInt(i.getStringExtra("AGE"));
                String stepGoalRec = (getIntent().getStringExtra("STEP_GOAL"));
                Toast.makeText(MainActivity3.this, "Step Goal: "+stepGoalText, Toast.LENGTH_SHORT).show();
            }
        });

        int physical_score = (int)getIntent().getFloatExtra("Kphysicalscore1", 0);
        int sentiment_score = (int)getIntent().getFloatExtra("Ksentimentscore1", 0);
        int active_score = (int) getIntent().getFloatExtra("Kactivescore1", 0);
        int sleep_score = (int) getIntent().getFloatExtra("Ksleepscore1", 0);
//        int ageRec = Integer.parseInt(getIntent().getStringExtra("AGE"));
//        int stepGoalRec = Integer.parseInt(getIntent().getStringExtra("STEP_GOAL"));


//        if(sentiment_score>100){
//            sentiment_score = sentiment_score-100;
//        }

        float distance = getIntent().getFloatExtra("Kdistance", 0);
        float calorie = getIntent().getFloatExtra("Kcalorie", 0);
        float step_count = getIntent().getFloatExtra("Kstepcount", 0);
        float time_sedentary = getIntent().getFloatExtra("Ktimesedentary", 0);
        float time_lightly_active = getIntent().getFloatExtra("Ktimelightlyactive", 0);
        float time_moderately_active = getIntent().getFloatExtra("Ktimemoderatelyactive", 0);
        float time_very_active = getIntent().getFloatExtra("Ktimeveryactive", 0);
        float composition_score = getIntent().getFloatExtra("Kcompositionscore", 0);
        float revitalization_score = getIntent().getFloatExtra("Krevitalizationscore", 0);
        float duration_score = getIntent().getFloatExtra("Kdurationscore", 0);
        float deep_sleep = getIntent().getFloatExtra("Kdeepsleep", 0);
        float resting_heart_rate = getIntent().getFloatExtra("Krestingheartrate", 0);
        float restlessness = getIntent().getFloatExtra("Krestlessness", 0);
        float sleep_efficiency = getIntent().getFloatExtra("Ksleepefficiency", 0);
//        float time_asleep = getIntent().getFloatExtra("Ktimeasleep", 0);
        float time_awake = getIntent().getFloatExtra("Ktimeawake", 0);
        float time_in_bed = getIntent().getFloatExtra("Ktimeinbed", 0);
        float sleep_duration = getIntent().getFloatExtra("Ksleepduration", 0);
        float fatigue = getIntent().getFloatExtra("Kfatigue", 0);
        float mood = getIntent().getFloatExtra("Kmood", 0);
        float soreness = getIntent().getFloatExtra("Ksoreness", 0);
        float stress = getIntent().getFloatExtra("Kstress", 0);


        float phy_dist=0;
        float phy_cal=0;
        float phy_step_count=0;
        float ideal_distance = (float) 1028692.6973;
        float ideal_calories = (float) 3607.6128;
        float ideal_step_count = (float) 12622.8486;
        float ideal_time_sedentary = (float) 719.335;
        float ideal_time_lightly_active = (float) 247.7960;
        float ideal_time_moderately_active = (float) 146;
        float ideal_time_very_active = (float) 178;

        if(distance>ideal_distance)
        {
            phy_dist = 1;
        }
        else{
            phy_dist = (float) (distance/ideal_distance);
        }
        if(calorie>ideal_calories){
            phy_cal=1;
        }
        else {
            phy_cal = (float) (calorie/ideal_calories);
        }
        if(step_count>ideal_step_count){
            phy_step_count = 1;
        }
        else {
            phy_step_count = (float) (step_count/ideal_step_count);
        }
        physical_score = (int) ((phy_dist*33.33)+(phy_cal*33.33)+(phy_step_count*33.33));

        float ideal_active_score = (float) ((float) (0.1*ideal_time_sedentary)+(0.2*ideal_time_lightly_active)+(0.3*ideal_time_moderately_active)+(0.4*ideal_time_very_active));
        float x = (float) ((float) (0.1*time_sedentary)+(0.2*time_lightly_active)+(0.3*time_moderately_active)+(0.4*time_very_active));
        float y =  (x/ideal_active_score)*100;
        active_score = (int) y;
        Log.i("Active Score", String.valueOf(active_score));
        Log.i("Time sedentary", String.valueOf(time_sedentary)+" "+String.valueOf(time_lightly_active)+" "+String.valueOf(time_moderately_active)+" "+String.valueOf(time_very_active));


        progr1.setProgress(physical_score);
        progr2.setProgress(sentiment_score);
        progr3.setProgress(active_score);
        tv5.setText(physical_score+"%");
        tv7.setText(sentiment_score+"%");
        tv8.setText(active_score+"%");

        if(physical_score<80){
            c1.setVisibility(View.INVISIBLE);
        }
        else{
            w1.setVisibility(View.INVISIBLE);
        }

        if(sentiment_score<80){
            c2.setVisibility(View.INVISIBLE);
        }
        else {
            w2.setVisibility(View.INVISIBLE);
        }

        if(active_score<80){
            c3.setVisibility(View.INVISIBLE);
        }
        else{
            w3.setVisibility(View.INVISIBLE);
        }

//        tv1.setText(physical_score + "\n" + sentiment_score +"\n"+active_score+"\n"+sleep_score);
//        Log.i("phy3-", String.valueOf(physical_score));
//        Log.i("sleep3-", String.valueOf(sleep_score));
        StringBuilder result = new StringBuilder();

        if(physical_score<80){
           result.append("You step count for te day is very less.\n");
           if(calorie<ideal_calories){
               result.append("You have burned very less calories today.");
           }
        }
        if(active_score<80){
            result.append("You should try to do more vigorous activities(atleast 60 minutes) and 120 minutes of moderate activity.\n");
        }


        if(sentiment_score<80){

            if(mood!=3){
                if(mood<3){
                    result.append("From the rating entered, you seem sad due to which you are facing sleeplessness.\n");
                }
                else{
                    result.append("From the rating entered, you seem very happy, due to which anxiety increase and cause sleeplessness.\n");
                }
            }

            if(stress!=3){
                result.append("From the rating entered, your stress level is not normal.\n");
            }
            if(soreness!=3){
                if(soreness>3){
                    result.append("Don't do vigorous physical activities for more than one hour, it can cause health issues.\n");
                }
            }
        }

        Log.i("sleep score: ", String.valueOf(sleep_score));
        if(sleep_score<80){
            if(resting_heart_rate<60 || resting_heart_rate>100){
                result.append("Your heart rate during sleep is not normal.\n");
            }
            if(sleep_duration<6){
                result.append("You don't take enough sleep hours. Atleast take 6-8 hours of sleep.\n");
            }
            if(restlessness>0.1){
                result.append("You are very restless during sleep.\n");
            }
        }

        Log.i("activity3" , String.valueOf(result));
//        tv1.setText(result);
        alertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity3.this);
                builder.setTitle("SOME OBSERVATIONS");
                builder.setMessage(""+result);
//                builder.setMultiChoiceItems(mAlertItems, mSelectedItems, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                        //Do something here
//                    }
//                });
                builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_bg, null));
                builder.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Do something when clicked
                    }
                });
                builder.setNegativeButton("DISMISS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Do something when clicked
                    }
                });
                builder.show();

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.about_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.about: showAlert();return true;
            case R.id.steps: recordStepGoal();return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void recordStepGoal() {
        Context context = new ContextThemeWrapper(MainActivity3.this, R.style.AppTheme2);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        builder.setTitle("--STEP GOALS--");
        builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_bg, null));
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                stepGoalText = input.getText().toString();
                Toast.makeText(context, "Your Step Goal is recorded.. Thankyou!!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        builder.show();
    }


    private void showAlert() {
        Context context = new ContextThemeWrapper(MainActivity3.this, R.style.AppTheme2);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        builder.setTitle("--ABOUT--");
//        +
        builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_bg, null));
        builder.setItems(scoresList, MainActivity3.this);
        builder.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Do something when clicked
            }
        });

        builder.show();
    }


    public void showAlertDialog(String text, String item){
        Context context = new ContextThemeWrapper(MainActivity3.this, R.style.AppTheme2);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        builder.setTitle(""+item);
        builder.setMessage(""+text);
        builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_bg, null));
        builder.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Do something when clicked
            }
        });
        builder.setNegativeButton("DISMISS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Do something when clicked
            }
        });
        builder.show();
    }


    @Override
    public void onClick(DialogInterface dialog, int pos) {
        String item = scoresList[pos];
        String text;
        switch (pos){
            case 0:
                text = "It depends on steps taken, distance calculated based on the number of steps and calories burned.";
                showAlertDialog(text, item);
                break;

            case 1:
                text = "It is the combination of four scores i.e fatigue score, mood score, stress score and soreness score";
                showAlertDialog(text, item);
                break;

            case 2:
                text = "It depends on how much time you were very active, moderately active, lightly active and sedentary minutes.";
                showAlertDialog(text, item);
                break;

        }
    }
}