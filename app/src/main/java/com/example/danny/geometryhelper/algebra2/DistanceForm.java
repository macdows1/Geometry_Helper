package com.example.danny.geometryhelper.algebra2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.danny.geometryhelper.R;
import com.example.danny.geometryhelper.Tools;

public class DistanceForm extends AppCompatActivity {

    private EditText editx1;
    private EditText edity1;
    private EditText editx2;
    private EditText edity2;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_form);

        Toolbar toolbar = (Toolbar) findViewById(R.id.distToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editx1 = (EditText)findViewById(R.id.editX1);
        edity1 = (EditText)findViewById(R.id.editY1);
        editx2 = (EditText)findViewById(R.id.editX2);
        edity2 = (EditText)findViewById(R.id.editY2);
        textView = (TextView)findViewById(R.id.textView8);

        editx1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                calculate();
                return false;
            }
        });

        editx2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                calculate();
                return false;
            }
        });
        edity1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                calculate();
                return false;
            }
        });
        edity2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                calculate();
                return false;
            }
        });


    }


    private void calculate(){

        String sy1 = edity1.getText().toString();
        String sy2 = edity2.getText().toString();
        String sx1 = editx1.getText().toString();
        String sx2 = editx2.getText().toString();

        if(Tools.stringCheck(sy1) && Tools.stringCheck(sy2) && Tools.stringCheck(sx1) && Tools.stringCheck(sx2)){

            double x1 = Double.parseDouble(sx1);
            double x2 = Double.parseDouble(sx2);
            double y1 = Double.parseDouble(sy1);
            double y2 = Double.parseDouble(sy2);

            Log.d("Distance Form", String.valueOf(x1));
            Log.d("Distance Form", String.valueOf(y1));
            Log.d("Distance Form", String.valueOf(x2));
            Log.d("Distance Form", String.valueOf(y2));

            double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            String numDec = sharedPreferences.getString("example_list","4");
            Log.d("Dec Preference", numDec);

            String temp = String.format("%." + numDec + "f", distance);
            temp = Tools.removeZeros(temp);
            temp = "Distance = " + temp + R.string.unit;

            textView.setText(temp);

        }

    }
}
