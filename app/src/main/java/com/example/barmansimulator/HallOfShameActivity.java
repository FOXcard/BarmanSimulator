package com.example.barmansimulator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.lang.reflect.Field;
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

        getAllImages();
        customListview = new CustomListview(this,imgs,names);
        lst.setAdapter(customListview);

        databaseAPI = new DatabaseAPI(this);
        databaseAPI.getImages("uploads");
    }


    public void refresh(Uri uri) {
        System.out.println("saaaaal "+uri);
        String[] names = {"Hola","Test"};
        Integer[] imgs = {R.drawable.hola,R.drawable.test};
        customListview = new CustomListview(this,imgs,names);
        lst.setAdapter(customListview);
    }

    public void addImage(Uri uri) {
        System.out.println(uri);
    }


    public void getAllImages(){
        Field[] drawablesFields = com.example.barmansimulator.R.drawable.class.getFields();
        ArrayList<Drawable> drawables = new ArrayList<>();

        for (Field field : drawablesFields) {
            try {
                drawables.add(getResources().getDrawable(field.getInt(null)));
                Drawable u = getResources().getDrawable(field.getInt(null));
                System.out.println("iiiiiiiooooo"+u.getOpacity());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("iiiiiii"+R.drawable.hola);

    }
}
