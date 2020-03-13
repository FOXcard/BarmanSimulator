package com.example.barmansimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BarmanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barman);
        SharedPreferences prefs=this.getSharedPreferences("yourPrefsKey", Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("_", null);
        int size = prefs.getInt("_size", 0);

        List<String> data = new ArrayList<String>(size);
        for(int i=0; i<size; i++)
            data.add(prefs.getString("_"+i, null));
        Log.i("erzer", "onCreate: "+data);
    }
}
