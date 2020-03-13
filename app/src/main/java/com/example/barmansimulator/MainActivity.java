package com.example.barmansimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    DatabaseAPI databaseAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //HallOfShameActivity hallOfShame = new HallOfShameActivity();
        Intent intent = new Intent(MainActivity.this, HallOfShameActivity.class);
        startActivityForResult(intent,1);
    }
}
