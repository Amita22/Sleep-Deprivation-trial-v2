package com.example.trial_v1;

public class User_Data {
    public String distance, step_count,calories, time_sedentary, time_lightly_active,
            time_moderately_active, time_very_active;

    public User_Data() {
    }

    public User_Data(String distance, String step_count, String calories,
                     String time_sedentary, String time_lightly_active,
                     String time_moderately_active, String time_very_active) {
        this.distance = distance;
        this.step_count = step_count;
        this.calories = calories;
        this.time_sedentary = time_sedentary;
        this.time_lightly_active = time_lightly_active;
        this.time_moderately_active = time_moderately_active;
        this.time_very_active = time_very_active;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getStep_count() {
        return step_count;
    }

    public void setStep_count(String step_count) {
        this.step_count = step_count;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getTime_sedentary() {
        return time_sedentary;
    }

    public void setTime_sedentary(String time_sedentary) {
        this.time_sedentary = time_sedentary;
    }

    public String getTime_lightly_active() {
        return time_lightly_active;
    }

    public void setTime_lightly_active(String time_lightly_active) {
        this.time_lightly_active = time_lightly_active;
    }

    public String getTime_moderately_active() {
        return time_moderately_active;
    }

    public void setTime_moderately_active(String time_moderately_active) {
        this.time_moderately_active = time_moderately_active;
    }

    public String getTime_very_active() {
        return time_very_active;
    }

    public void setTime_very_active(String time_very_active) {
        this.time_very_active = time_very_active;
    }
}

