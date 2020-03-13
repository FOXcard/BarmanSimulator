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

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_RESULT = 1;
    private Bitmap cameraImageBitmap;
    private ImageView cameraImage;
    private Timer myTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraImage = (ImageView) findViewById(R.id.cameraImage);
        startTimer();
    }
    public void startTimer() {
        myTimer = new Timer();
        TimerTask timerTask = new TimerTask() {
            boolean take=false;
            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Open camera", Toast.LENGTH_SHORT).show();
                        if(!take){
                            ouvrirCamera();
                            myTimer.cancel();
                        }
                        else {
                            myTimer.cancel();

                        }
                    }
                });
            }
        };
        myTimer.scheduleAtFixedRate(timerTask, 2000, 2000);
    }

    public void ouvrirCamera() {
        Intent intent = new Intent(MainActivity.this, CameraActivity.class);
        startActivityForResult(intent, CAMERA_RESULT);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (CAMERA_RESULT): {
                if (resultCode == Activity.RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                    String returnValue = data.getStringExtra("filePath");

                    loadImageFromStorage(returnValue);

                }
                break;
            }
        }
    }
    private void loadImageFromStorage(String path) {
        File imgFile = new File(path);
        Toast.makeText(MainActivity.this, "Main acitivity" + imgFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        if (imgFile.exists()) {
            Toast.makeText(MainActivity.this, "Main acitivity" + imgFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            Toast.makeText(MainActivity.this, "" + imgFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            cameraImageBitmap= BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            TextView textView=findViewById(R.id.tv);
            textView.setText(""+path);
            cameraImage.setImageBitmap(cameraImageBitmap);

        }
    }
}
