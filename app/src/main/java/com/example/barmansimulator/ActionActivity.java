package com.example.barmansimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ActionActivity extends AppCompatActivity {
    //ActionManager actionManager;
    int compteur = 0;
    private String TAG = "ActionActivity";
    List<String> val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        SharedPreferences prefs=this.getSharedPreferences("yourPrefsKey", Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("_", null);
        int size = prefs.getInt("_size", 0);

        List<String> data = new ArrayList<String>(size);
        for(int i=0; i<size; i++) {
            data.add(prefs.getString("_" + i, null));
        }
        val = new ArrayList<>();
        for(String m : data)
            addValue(m);
    }


    public void addValue(String value){
            TableLayout table = (TableLayout) findViewById(R.id.idTable); // on prend le tableau défini dans le layout
            TableRow row; // création d'un élément : ligne
            TextView tv1; // création des cellules
            Button tv3;
            val.add(value);
                row = new TableRow(this); // création d'une nouvelle ligne
                tv3 = new Button(this);
                tv1 = new TextView(this);
                tv1.setText(value);
                tv1.setGravity(Gravity.CENTER);
                tv1.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 20));
                tv3.setText("X");
                tv3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        TableLayout table = (TableLayout) findViewById(R.id.idTable);
                        Log.i(TAG, "onClick: "+v);
                        table.removeView((View) v.getParent());

                    }
                });
                tv3.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 2));
                // ajout des cellules à la ligne
                row.addView(tv1);
                row.addView(tv3);
                // ajout de la ligne au tableau
                table.addView(row);

        }

        public void add(View view){
            EditText editText = findViewById(R.id.editText);
            addValue(editText.getText().toString());
            save(view);
        }

        public void save(View view){
            SharedPreferences prefs=this.getSharedPreferences("yourPrefsKey", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            int size = prefs.getInt("_size", 0);

            // clear the previous data if exists
            for(int i=0; i<size; i++)
                editor.remove("_"+i);

            // write the current list
            for(int i=0; i<val.size(); i++)
                editor.putString("_"+i, val.get(i));

            editor.putInt("_size", val.size());
            editor.commit();
        }

}
