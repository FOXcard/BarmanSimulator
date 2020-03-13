package com.example.barmansimulator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class HallOfShameActivity extends AppCompatActivity {

    ListView lst;
    String[] names = {"Hola","Test","cap"};
    Integer[] imgs = {R.drawable.hola,R.drawable.test,R.drawable.cartman_cop};
    DatabaseAPI databaseAPI;
    CustomListview customListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        lst = findViewById(R.id.listView);
        customListview = new CustomListview(this,imgs,names);
        lst.setAdapter(customListview);

    }


    public void refresh(Uri uri) {
        System.out.println(uri);
        String[] names = {"Hola","Test"};
        Integer[] imgs = {R.drawable.hola,R.drawable.test};
        customListview = new CustomListview(this,imgs,names);
    }

    public void addImage(Uri uri) {
        System.out.println(uri);
    }
}
