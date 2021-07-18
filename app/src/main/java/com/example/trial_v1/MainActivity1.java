package com.example.trial_v1;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MainActivity1 extends AppCompatActivity {

    RatingBar fatigueRb1, moodRb2, sorenessRb3, stressRb4;

    float stressValue, fatigueValue, sorenessValue, moodValue;
    Button predict;
    Interpreter interpreter_physical, interpreter_sentiment, interpreter_active, interpreter_sleep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        fatigueRb1 = findViewById(R.id.rateFatigue); //fatigue
        moodRb2 = findViewById(R.id.rateMood);//mood
        sorenessRb3 = findViewById(R.id.rateSoreness);//soreness
        stressRb4 = findViewById(R.id.rateStress);//stress



        predict = findViewById(R.id.btnSendToNextPage);
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

//        float fatigue = (Float) rb1.getRating();
//        float mood =  (Float) rb2.getRating();
//        float soreness = (Float) rb3.getRating();
//        float stress = (Float) rb4.getRating();
//        float fatigue = 2;
//        float mood =  3;
//        float soreness = 2;
//        float stress = 3;



        fatigueRb1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                fatigueValue = ratingBar.getRating();
                Toast.makeText(MainActivity1.this, "Fatigue Value: "+fatigueValue, Toast.LENGTH_SHORT).show();
            }
        });
        sorenessRb3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                sorenessValue = ratingBar.getRating();
                Toast.makeText(MainActivity1.this, "Soreness Value: "+sorenessValue, Toast.LENGTH_SHORT).show();
            }
        });
        moodRb2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                moodValue = ratingBar.getRating();
                Toast.makeText(MainActivity1.this, "Mood Value: "+moodValue, Toast.LENGTH_SHORT).show();
            }
        });
        stressRb4.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                stressValue = ratingBar.getRating();
                Toast.makeText(MainActivity1.this, "Stress Value: "+stressValue, Toast.LENGTH_SHORT).show();
            }
        });


        Log.i("Mood", String.valueOf(moodValue));
        Log.i("stress", String.valueOf(stressValue));
        Log.i("fatigue", String.valueOf(fatigueValue));
        Log.i("soreness", String.valueOf(sorenessValue));


        try {
            interpreter_physical = new Interpreter(loadModelFile_physical(), null);
            interpreter_sentiment = new Interpreter(loadModelFile_sentiment(), null);
            interpreter_active = new Interpreter(loadModelFile_active(), null);
            interpreter_sleep = new Interpreter(loadModelFile_sleep(), null);

        } catch (IOException e) {
            e.printStackTrace();
        }

        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float physical_score = doInference_physical(String.valueOf(distance), String.valueOf(calorie), String.valueOf(step_count), String.valueOf(sorenessValue));
                float sentiment_score = doInference_sentiment(String.valueOf(fatigueValue), String.valueOf(moodValue), String.valueOf(stressValue), String.valueOf(sorenessValue));
                float active_score = doInference_active(String.valueOf(time_sedentary), String.valueOf(time_lightly_active), String.valueOf(time_moderately_active), String.valueOf(time_very_active));
                float sleep_score = doInference_sleepscore(String.valueOf(composition_score), String.valueOf(revitalization_score), String.valueOf(duration_score), String.valueOf(deep_sleep), String.valueOf(resting_heart_rate), String.valueOf(restlessness), String.valueOf(sleep_efficiency), String.valueOf(time_awake), String.valueOf(time_in_bed), String.valueOf(sleep_duration));

                Intent intent = new Intent(MainActivity1.this, MainActivity2.class);
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
//                intent.putExtra("Ktimeasleep", time_asleep);
                intent.putExtra("Ktimeawake", time_awake);
                intent.putExtra("Ktimeinbed", time_in_bed);
                intent.putExtra("Ksleepduration", sleep_duration);
                intent.putExtra("Kfatigue" , fatigueValue);
                intent.putExtra("Kmood" , moodValue);
                intent.putExtra("Ksoreness", sorenessValue);
                intent.putExtra("Kstress" , stressValue);
                startActivity(intent);
            }
        });

    }


    private MappedByteBuffer loadModelFile_physical() throws IOException
    {
        AssetFileDescriptor assestFileDescriptor = this.getAssets().openFd("physical.tflite");
        FileInputStream fileInputStream = new FileInputStream(assestFileDescriptor.getFileDescriptor());
        FileChannel fileChannel = fileInputStream.getChannel();
        long startOffset = assestFileDescriptor.getStartOffset();
        long length = assestFileDescriptor.getLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, length);
    }


    private MappedByteBuffer loadModelFile_sentiment() throws IOException
    {
        AssetFileDescriptor assestFileDescriptor = this.getAssets().openFd("emotion.tflite");
        FileInputStream fileInputStream = new FileInputStream(assestFileDescriptor.getFileDescriptor());
        FileChannel fileChannel = fileInputStream.getChannel();
        long startOffset = assestFileDescriptor.getStartOffset();
        long length = assestFileDescriptor.getLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, length);

    }

    private MappedByteBuffer loadModelFile_active() throws IOException
    {
        AssetFileDescriptor assestFileDescriptor = this.getAssets().openFd("active.tflite");
        FileInputStream fileInputStream = new FileInputStream(assestFileDescriptor.getFileDescriptor());
        FileChannel fileChannel = fileInputStream.getChannel();
        long startOffset = assestFileDescriptor.getStartOffset();
        long length = assestFileDescriptor.getLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, length);

    }

    private MappedByteBuffer loadModelFile_sleep() throws IOException
    {
        AssetFileDescriptor assestFileDescriptor = this.getAssets().openFd("sleepscore.tflite");
        FileInputStream fileInputStream = new FileInputStream(assestFileDescriptor.getFileDescriptor());
        FileChannel fileChannel = fileInputStream.getChannel();
        long startOffset = assestFileDescriptor.getStartOffset();
        long length = assestFileDescriptor.getLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, length);

    }

    public float doInference_physical(String val, String val2, String val3, String val4)
    {
        float[] input = new float[4];
        input[0] = Float.parseFloat(val);
        input[1] = Float.parseFloat(val2);
        input[2] = Float.parseFloat(val3);
        input[3] = Float.parseFloat(val4);

        float[][] output = new float[1][1];
        interpreter_physical.run(input, output);
        return output[0][0];
    }


    public float doInference_sentiment(String val, String val2, String val3, String val4)
    {
        float[] input = new float[4];
        input[0] = Float.parseFloat(val);
        input[1] = Float.parseFloat(val2);
        input[2] = Float.parseFloat(val3);
        input[3] = Float.parseFloat(val4);

        float[][] output = new float[1][1];
        interpreter_sentiment.run(input, output);
        return output[0][0];
    }

    public float doInference_active(String val, String val2, String val3, String val4)
    {
        float[] input = new float[4];
        input[0] = Float.parseFloat(val);
        input[1] = Float.parseFloat(val2);
        input[2] = Float.parseFloat(val3);
        input[3] = Float.parseFloat(val4);

        float[][] output = new float[1][1];
        interpreter_active.run(input, output);
        return output[0][0];
    }


    public float doInference_sleepscore(String val, String val2, String val3, String val4, String val5, String val6, String val7, String val8, String val9, String val10)
    {
        float[] input = new float[10];
        input[0] = Float.parseFloat(val);
        input[1] = Float.parseFloat(val2);
        input[2] = Float.parseFloat(val3);
        input[3] = Float.parseFloat(val4);
        input[4] = Float.parseFloat(val5);
        input[5] = Float.parseFloat(val6);
        input[6] = Float.parseFloat(val7);
        input[7] = Float.parseFloat(val8);
        input[8] = Float.parseFloat(val9);
        input[9] = Float.parseFloat(val10);
//        input[10] = Float.parseFloat(val11);

        float[][] output = new float[1][1];
        interpreter_sleep.run(input, output);
        return output[0][0];
    }

}
