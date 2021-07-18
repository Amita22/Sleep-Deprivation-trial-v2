package com.example.trial_v1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MainActivity2 extends AppCompatActivity implements DialogInterface.OnClickListener {
    static String[] definition = {"Sleep Score"};
    TextView tv4, tv2;
    ProgressBar progr;
    Button btn;
//    Interpreter interpreter_physical, interpreter_sentiment, interpreter_active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        tv1 = findViewById(R.id.textView);
        tv2 = findViewById(R.id.textView2);
//        tv3 = findViewById(R.id.textView3);
        tv4 = findViewById(R.id.textView4);
        progr = findViewById(R.id.progressBar);
        btn = findViewById(R.id.button2);


        float physical_score = getIntent().getFloatExtra("Kphysicalscore", 0);
        float sentiment_score = getIntent().getFloatExtra("Ksentimentscore", 0);
        float active_score = getIntent().getFloatExtra("Kactivescore", 0);
        float sleep_score_2 = getIntent().getFloatExtra("Ksleepscore", 0);
        int sleep_score = (int) getIntent().getFloatExtra("Ksleepscore", 0);

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
//        int ageRec = Integer.parseInt(getIntent().getStringExtra("AGE"));
//        String stepGoalRec = (getIntent().getStringExtra("STEP_GOAL"));




//        tv1.setText("Predicted Physical Score : " + physical_score);
//        tv2.setText("Predicted Sentiment Score : " + sentiment_score);
//        tv3.setText("Predicted Active Score : " + active_score);
        Log.i("phy-", String.valueOf(physical_score));
        Log.i("sleep-", String.valueOf(sleep_score));
        tv4.setText(sleep_score+"%");
        progr.setProgress(sleep_score);

        if(sleep_score>=80 && sleep_score<=100){
            tv2.setText("You are not Sleep Deprived!!\n" + "'A good laugh and a long sleep are\n the best cures in the doctor's book.'");
            btn.setVisibility(View.GONE);
        }
        else{
            tv2.setText("You are Sleep Deprived.\n Click the button below to know your reasons for sleep deprivation.");
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                    intent.putExtra("Kphysicalscore1", physical_score);
                    intent.putExtra("Ksentimentscore1" , sentiment_score);
                    intent.putExtra("Kactivescore1" , active_score);
                    intent.putExtra("Ksleepscore1" , sleep_score_2);
                    intent.putExtra("Kphysicalscore", physical_score);
                    intent.putExtra("Ksentimentscore", sentiment_score);
                    intent.putExtra("Kactivescore" , active_score);
                    intent.putExtra("Ksleepscore", sleep_score);
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
//                    intent.putExtra("Ktimeasleep", time_asleep);
                    intent.putExtra("Ktimeawake", time_awake);
                    intent.putExtra("Ktimeinbed", time_in_bed);
                    intent.putExtra("Ksleepduration", sleep_duration);
                    intent.putExtra("Kfatigue" , fatigue);
                    intent.putExtra("Kmood" , mood);
                    intent.putExtra("Ksoreness", soreness);
                    intent.putExtra("Kstress" , stress);
//                    intent.putExtra("AGE", ageRec);
//                    intent.putExtra("STEP_GOAL", stepGoalRec);
                    startActivity(intent);
                }
            });
        }

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
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlert() {
        Context context = new ContextThemeWrapper(MainActivity2.this, R.style.AppTheme2);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        builder.setTitle("--ABOUT--");
//        +
        builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_bg, null));
        builder.setItems(definition, MainActivity2.this);
        builder.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Do something when clicked
            }
        });

        builder.show();
    }


public void showAlertDialog(String text, String item){
    Context context = new ContextThemeWrapper(MainActivity2.this, R.style.AppTheme2);
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
        String item = definition[pos];
        String text;
        switch (pos){
            case 0:
                text = "It is predicted using deep learning regression sequential model";
                showAlertDialog(text, item);
                break;
        }
    }
}
