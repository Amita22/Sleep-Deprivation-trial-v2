package com.example.trial_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity0 extends AppCompatActivity{
    static String[] fields= {"DISTANCE-VALUE","CALORIE-VALUE","STEP COUNT",
            "TIME SEDENTARY","TIME LIGHTLY ACTIVE","TIME MODERATELY ACTIVE","TIME VERY ACTIVE",
            "COMPOSITION SCORE","REVITALIZATION SCORE","DURATION","DEEP SLEEP",
            "RESTING HEART RATE","RESTLESSNESS-VALUE","SLEEP EFFICIENCY","TIME ASLEEP",
            "TIME AWAKE","TIME IN BED","SLEEP DURATION"};


    EditText ed1, ed2, ed3, ed4, ed5, ed6, ed7, ed8, ed9, ed10, ed11, ed12, ed13, ed14, ed15, ed16, ed17, ed18;
    Button next;
    //float physical_score, sentiment_score, active_score, sleep_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main0);
        ed1 =findViewById(R.id.distanceID); //Distance
        ed2 = findViewById(R.id.calorieID); //Calorie
        ed3 =findViewById(R.id.stepCountID); //Step Count
        ed4 =findViewById(R.id.timeSedentaryID); //Time Sedentary
        ed5 =findViewById(R.id.timeLightlyActiveID); //Time Lightly Active
        ed6 =findViewById(R.id.timeModeratelyActiveID); // Time Moderately Active
        ed7 =findViewById(R.id.timeVeryActiveID); //Time Very Active
        ed8 =findViewById(R.id.compositionScoreID); //Composition Score
        ed9 =findViewById(R.id.revitalizationScoreID); //Revitalization Score
        ed10 =findViewById(R.id.durationScoreID); //Duration Score
        ed11 =findViewById(R.id.deepSleepID); //Deep Sleep
        ed12 =findViewById(R.id.restingHeartRateID); //Resting Heart Rate
        ed13 =findViewById(R.id.restlessnessID); //Restlessness
        ed14 =findViewById(R.id.sleepEfficiencyID); //Sleep Efficiency
//        ed15 =findViewById(R.id.timeAsleepID); //Time Asleep
        ed16 =findViewById(R.id.timeAwakeID); //Time Awake
        ed17 =findViewById(R.id.timeInBedID); //Time in Bed
        ed18 =findViewById(R.id.sleepDurationID); //Sleep Duration

        next = findViewById(R.id.nxt);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float distance = Float.parseFloat(ed1.getText().toString());
                float calorie = Float.parseFloat(ed2.getText().toString());
                float step_count = Float.parseFloat(ed3.getText().toString());
                float time_sedentary = Float.parseFloat(ed4.getText().toString());
                float time_lightly_active = Float.parseFloat(ed5.getText().toString());
                float time_moderately_active = Float.parseFloat(ed6.getText().toString());
                float time_very_active = Float.parseFloat(ed7.getText().toString());
                float composition_score = Float.parseFloat(ed8.getText().toString());
                float revitalization_score = Float.parseFloat(ed9.getText().toString());
                float duration_score = Float.parseFloat(ed10.getText().toString());
                float deep_sleep = Float.parseFloat(ed11.getText().toString());
                float resting_heart_rate = Float.parseFloat(ed12.getText().toString());
                float restlessness = Float.parseFloat(ed13.getText().toString());
                float sleep_efficiency = Float.parseFloat(ed14.getText().toString());
//                float time_asleep = Float.parseFloat(ed15.getText().toString());
                float time_awake = Float.parseFloat(ed16.getText().toString());
                float time_in_bed = Float.parseFloat(ed17.getText().toString());
                float sleep_duration = Float.parseFloat(ed18.getText().toString());

                Intent intent = new Intent(MainActivity0.this, MainActivity1.class);
                intent.putExtra("Kdistance", distance);
                intent.putExtra("Kcalorie", calorie);
                intent.putExtra("Kstepcount", step_count);
                intent.putExtra("Ktimesedentary", time_sedentary);
                intent.putExtra("Ktimelightlyactive", time_lightly_active);
                intent.putExtra("Ktimemoderatelyactive", time_moderately_active);
                intent.putExtra("Ktimeveryactive", time_very_active);
                intent.putExtra("Kcompositionscore", composition_score);
                intent.putExtra("Krevitalizationscore", revitalization_score);
                intent.putExtra("Kdurationscore", duration_score);
                intent.putExtra("Kdeepsleep", deep_sleep);
                intent.putExtra("Krestingheartrate", resting_heart_rate);
                intent.putExtra("Krestlessness", restlessness);
                intent.putExtra("Ksleepefficiency", sleep_efficiency);
//                intent.putExtra("Ktimeasleep", time_asleep);
                intent.putExtra("Ktimeawake", time_awake);
                intent.putExtra("Ktimeinbed", time_in_bed);
                intent.putExtra("Ksleepduration", sleep_duration);
//                intent.putExtra("AGE",ageReceived);
//                intent.putExtra("STEP_GOAL", stepGoalReceived);
                startActivity(intent);

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.autofill, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.fillData: fillDetails();return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fillDetails() {
        ed1.setText("757520");
        ed2.setText("3190.41");
        ed3.setText("9469");
        ed4.setText("849");
        ed5.setText("206");
        ed6.setText("10");
        ed7.setText("11");
        ed8.setText("17");
        ed9.setText("13");
        ed10.setText("37");
        ed11.setText("65");
        ed12.setText("52");
        ed13.setText("0.064472");
        ed14.setText("97");
        ed16.setText("0.9");
        ed17.setText("6.1");
        ed18.setText("5.2");
    }

//    private void showAlert() {
//        Context context = new ContextThemeWrapper(MainActivity0.this, R.style.AppTheme2);
//        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
//        builder.setTitle("--ABOUT--");
////        +
//        builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_bg, null));
//        builder.setItems(fields, MainActivity0.this);
//        builder.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //Do something when clicked
//            }
//        });
//
//        builder.show();
//    }
//
//    public void showAlertDialog(String text, String item){
//        Context context = new ContextThemeWrapper(MainActivity0.this, R.style.AppTheme2);
//        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
//        builder.setTitle(""+item);
//        builder.setMessage(""+text);
////                builder.setMultiChoiceItems(mAlertItems, mSelectedItems, new DialogInterface.OnMultiChoiceClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
////                        //Do something here
////                    }
////                });
//        builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_bg, null));
//        builder.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //Do something when clicked
//            }
//        });
//        builder.setNegativeButton("DISMISS", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //Do something when clicked
//            }
//        });
//        builder.show();
//    }
//
//
//    @Override
//    public void onClick(DialogInterface dialog, int pos) {
//        String item = fields[pos];
//        String text;
//        switch (pos) {
//            case 0:
//                text = "It shows the total distance the person has walked till now";
//                showAlertDialog(text, item);
//                break;
//            case 1:
//                text = "It shows the total calorie the person has gained from foods";
//                showAlertDialog(text, item);
//                break;
//            case 2:
//                text = "It shows the count of steps the person has walked till now";
//                showAlertDialog(text, item);
//                break;
//            case 3:
//                text = "It shows the time sedentary of the person ";
//                showAlertDialog(text, item);
//                break;
//            case 4:
//                text = "It shows the time the person was lightly active";
//                showAlertDialog(text, item);
//                break;
//            case 5:
//                text = "It shows the time the person was moderately active";
//                showAlertDialog(text, item);
//                break;
//            case 6:
//                text = "It shows the time the person was very active";
//                showAlertDialog(text, item);
//                break;
//            case 7:
//                text = "It shows the score composed of various factors";
//                showAlertDialog(text, item);
//                break;
//            case 8:
//                text = "It shows the revitalization score of the person";
//                showAlertDialog(text, item);
//                break;
//            case 9:
//                text = "It shows the duration score of the person";
//                showAlertDialog(text, item);
//                break;
//            case 10:
//                text = "It shows the total time the person was having deep sleep";
//                showAlertDialog(text, item);
//                break;
//            case 11:
//                text = "It shows the resting heart rate of the person";
//                showAlertDialog(text, item);
//                break;
//            case 12:
//                text = "It shows the restlessness value of the person";
//                showAlertDialog(text, item);
//                break;
//            case 13:
//                text = "It shows the sleep efficiency of the person";
//                showAlertDialog(text, item);
//                break;
//            case 14:
//                text = "It shows the total time of sleeping of the person";
//                showAlertDialog(text, item);
//                break;
//            case 15:
//                text = "It shows the time the person was very awake";
//                showAlertDialog(text, item);
//                break;
//            case 16:
//                text = "It shows the time the person was in bed";
//                showAlertDialog(text, item);
//                break;
//            case 17:
//                text = "It shows the total sleep duration";
//                showAlertDialog(text, item);
//                break;
//        }
//
//    }
}