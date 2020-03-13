package com.example.barmansimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barmansimulator.camera.CameraActivity;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class BarmanActivity extends AppCompatActivity {

    private Timer myTimerResulta;
    private Timer myTimerText;
    private TextView textView;
    private boolean aBoolean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barman);
        textView=findViewById(R.id.textView);
       // startTimerResultat();
        startTimerText();
    }




















    public void startTimerResultat() {
        myTimerResulta = new Timer();
        TimerTask timerTask = new TimerTask() {
            boolean take=false;
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(BarmanActivity.this, "Open camera", Toast.LENGTH_SHORT).show();
                        if(!take){
                            ouvrirCamera();
                            myTimerResulta.cancel();
                        }
                        else {
                            myTimerResulta.cancel();
                        }
                    }
                });
            }
        };
        myTimerResulta.scheduleAtFixedRate(timerTask, 2000, 2000);
    }
    public void startTimerText() {
        myTimerText = new Timer();
        TimerTask timerTask = new TimerTask() {
            boolean take=false;
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        changeText();
                    }
                });
            }
        };
        myTimerText.scheduleAtFixedRate(timerTask, 1000, 5000);
    }

    private void changeText(){
        aBoolean=!aBoolean;
        if(aBoolean)
            textView.setText("Le risque");
        else textView.setText("Position");
    }
    public void ouvrirCamera() {
        Intent intent = new Intent(BarmanActivity.this, CameraActivity.class);
        startActivity(intent);
    }


}
