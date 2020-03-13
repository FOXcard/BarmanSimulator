package com.example.barmansimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class ActionActivity extends AppCompatActivity {
    //ActionManager actionManager;
    int compteur = 0;
    private String TAG = "ActionActivity";
    final String [] col1 = {"col1:ligne1","col1:ligne2","col1:ligne3","col1:ligne4","col1:ligne5"," tv1.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1));"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
    }


    public void addValue(String value){
            TableLayout table = (TableLayout) findViewById(R.id.idTable); // on prend le tableau défini dans le layout
            TableRow row; // création d'un élément : ligne
            TextView tv1; // création des cellules
            Button tv3;
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
        }
   /* public void checkData(View view){
        Cursor c = actionManager.getActions();
        if (c.moveToFirst())
        {
            do {
                Log.d("test",
                        c.getInt(c.getColumnIndex(ActionManager.KEY_ACTION)) + "," +
                                c.getString(c.getColumnIndex(ActionManager.KEY_ID_ACTION))
                );
            }
            while (c.moveToNext());
        }
        c.close();
    }

    public void add(View view){
        i++;
        Action a = new Action(i,"bis");
        actionManager.addAction(a);
    }
*/

}
