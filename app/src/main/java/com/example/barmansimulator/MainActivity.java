package com.example.barmansimulator;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barmansimulator.camera.CameraActivity;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;



import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private String TAG = "MainActivity";
    private SensorManager sensorManager;
    private Sensor mLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        Log.i(TAG, "onCreate: "+mLight);
    }




        @Override
        public final void onAccuracyChanged(Sensor sensor, int accuracy) {
            // Do something here if sensor accuracy changes.
        }

        @Override
        public final void onSensorChanged(SensorEvent event) {
            // The light sensor returns a single value.
            // Many sensors return 3 values, one for each axis.
            float lux = event.values[0];
            Log.i(TAG, "onSensorChanged: "+lux);
            if(lux == 0){
                onPause();
                this.finish();
            }
        }

        @Override
        protected void onResume() {
            super.onResume();
            sensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
            Log.i(TAG, "onResume: "+sensorManager);
        }

        @Override
        protected void onPause() {
            super.onPause();
            sensorManager.unregisterListener(this);
        }

        public void play(View view){
            Intent intent=new Intent(MainActivity.this, BarmanActivity.class);
            startActivity(intent);
            Log.i(TAG, "play: ");
        }

        public void library(View view){
            Log.i(TAG, "library: ");
        }
    }
