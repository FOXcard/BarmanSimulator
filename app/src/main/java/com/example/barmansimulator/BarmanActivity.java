package com.example.barmansimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class BarmanActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    ImageView view;
    double ax,ay,az;   // these are the acceleration in x,y and z axis
    int changeSpeed;
    double[] history = new double[3];
    int axis = 0;
    String [] direction = {"NONE","NONE"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        view = findViewById(R.id.ViewGlass);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barman);
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        view = findViewById(R.id.ViewGlass);
        if (event.sensor.getType()==Sensor.TYPE_GYROSCOPE){
            double xChange = history[0] - event.values[0];
            double yChange = history[1] - event.values[1];
            double zChange = history[2] - event.values[2];

            history[0] += event.values[0];
            history[1] += event.values[1];
            history[2] += event.values[2];

            if (event.values[2] > 5) {
                changeSpeed = 2;
            } else if (event.values[2] < 5) {
                changeSpeed = 2;
            } else if (event.values[2] > 3) {
                changeSpeed = 1;
            } else if (event.values[2] < 3) {
                changeSpeed = 1;
            } else {
                changeSpeed = 0;
            }

            if (xChange > 0.5){
                direction[0] = "RIGHT";
                //axis ++;
            }
            else if (xChange < -0.5){
                direction[0] = "LEFT";
                //axis --;
            }
            else {
                direction[0] = "MID";
            }

            if (yChange > 0.4){
                direction[1] = "DOWN";
                //axis++;
            }
            else if (yChange < -0.4){
                direction[1] = "UP";
                //axis--;
            }
            else {
                direction[1] = "MID";
            }
            if (zChange > 0.4){
                direction[1] = "DOWN";
                axis--;
            }
            else if (zChange < -0.4){
                direction[1] = "UP";
                axis++;
            }
            else {
                direction[1] = "MID";
            }

            if (axis > 5) {
                view.setImageDrawable(getResources().getDrawable(R.drawable.verre_droite90, getApplicationContext().getTheme()));
            } else if (axis < -5){
                view.setImageDrawable(getResources().getDrawable(R.drawable.verre_gauche90, getApplicationContext().getTheme()));
            } else if (axis == -4){
                view.setImageDrawable(getResources().getDrawable(R.drawable.verre_gauche, getApplicationContext().getTheme()));
            } else if (axis == 4){
                view.setImageDrawable(getResources().getDrawable(R.drawable.verre_droite, getApplicationContext().getTheme()));
            } else if (axis == -3){
                view.setImageDrawable(getResources().getDrawable(R.drawable.verre_gauche20, getApplicationContext().getTheme()));
            } else if (axis == 3){
                view.setImageDrawable(getResources().getDrawable(R.drawable.verre_droite20, getApplicationContext().getTheme()));
            } else if (axis == -2){
                view.setImageDrawable(getResources().getDrawable(R.drawable.verre_gauche10, getApplicationContext().getTheme()));
            } else if (axis == 2){
                view.setImageDrawable(getResources().getDrawable(R.drawable.verre_droite10, getApplicationContext().getTheme()));
            }
            else {
                view.setImageDrawable(getResources().getDrawable(R.drawable.verre_base, getApplicationContext().getTheme()));
            }

            System.out.println(direction[0] + ";" + direction[1]);
            System.out.println("AXIS : " + axis);
            System.out.println("changeSpeed = " + event.values[2]);
        }

    }
}
