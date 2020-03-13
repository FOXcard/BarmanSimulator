package com.example.barmansimulator.camera;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.barmansimulator.MainActivity;
import com.example.barmansimulator.R;

import java.io.File;

public class ResultatCaptureActivity extends AppCompatActivity {
    private String path;
    private static final int CAMERA_RESULT = 1;
    private Bitmap cameraImageBitmap;
    private ImageView cameraImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat_capture);
        cameraImage = findViewById(R.id.resultat_imag);
        sendFile();
    }

    private void loadImageFromStorage(String path) {
        File imgFile = new File(path);

        if (imgFile.exists()) {
            Log.i("Resulltat","file charge");
            cameraImageBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            cameraImage.setImageBitmap(cameraImageBitmap);

        }
    }

    private void sendFile() {
        Intent intent = getIntent();
        path = intent.getStringExtra("filePath");
        loadImageFromStorage(path);
    }

    public void rePlay(View view) {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
