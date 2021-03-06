package com.example.danny.geometryhelper.Geometry;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.danny.geometryhelper.R;
import com.example.danny.geometryhelper.Tools;

public class AreaCircle extends AppCompatActivity {

    private EditText rad;

    private TextView area;

    private double r;
    private double a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_circle);

        Toolbar toolbar = (Toolbar) findViewById(R.id.circAreaToolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        area = (TextView)findViewById(R.id.circ_area_num);
        rad = (EditText)findViewById(R.id.editText3);

        rad.setOnKeyListener(
                new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        calculate();
                        return false;
                    }
                }
        );



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_area_circle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void calculate(){
        if(Tools.stringCheck(rad.getText().toString())){
            r = Double.valueOf(rad.getText().toString());
            a = Math.PI*r*r;

            int check = (int) a;

            if(check == a){
                area.setText(String.valueOf(check));
            }
            else{

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                String numDec = sharedPreferences.getString("example_list","4");
                System.out.println(numDec);

                String temp = String.format("%."+numDec+"f",a);
                temp = Tools.removeZeros(temp) + " " + getString(R.string.sq_unit);

                area.setText(temp);
            }


        }
    }
}
