package com.example.danny.geometryhelper.Geometry;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.danny.geometryhelper.ImaginaryNumber;
import com.example.danny.geometryhelper.R;

public class QuadForm extends AppCompatActivity {

    private EditText txtA;
    private EditText txtB;
    private EditText txtC;

    private double numA;
    private double numB;
    private double numC;
    private double numAns;
    private double numAns1;

    private double dis;

    private TextView txtAns;

    Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quad_form);

        txtA = (EditText)findViewById(R.id.quadANum);
        txtB = (EditText)findViewById(R.id.quadBNum);
        txtC = (EditText)findViewById(R.id.quadCNum);

        txtAns = (TextView)findViewById(R.id.quadAns);

        clear = (Button)findViewById(R.id.quadClear);

        txtA.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                calculate();
                return false;
            }
        });
        txtB.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                calculate();
                return false;
            }
        });
        txtC.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                calculate();
                return false;
            }
        });

        txtC.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                calculate();
                return false;
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtA.setText("");
                txtB.setText("");
                txtC.setText("");
                txtAns.setText("X = 0");
                txtA.requestFocus();
                ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quad_form, menu);
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

        if(!(txtB.getText().toString().equals("") || txtA.getText().toString().equals("") || txtC.getText().toString().equals("")
           || txtB.getText().toString().equals("-") || txtA.getText().toString().equals("-") || txtC.getText().toString().equals("-"))){


            numA = Double.parseDouble(txtA.getText().toString());
            numB = Double.parseDouble(txtB.getText().toString());
            numC = Double.parseDouble(txtC.getText().toString());
            dis = (Math.pow(numB, 2)) + (- 4*numA*numC);

            if(dis < 0){

                ImaginaryNumber pos = new ImaginaryNumber(-numB/2*numA, Math.sqrt(-dis)/2*numA);
                ImaginaryNumber neg = new ImaginaryNumber(-numB/2*numA, Math.sqrt(dis)/2*numA);

                String ans = "X = " + pos.toString()+ ", " + neg.toString();

                txtAns.setText(ans);
            }
            else{
                numAns = ((-numB) + Math.sqrt(dis))/(2*numA);
                numAns1 = ((-numB) - Math.sqrt(dis))/(2*numA);

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                String numDec = sharedPreferences.getString("example_list","4");
                System.out.println(numDec);

                String ans = "X = " + String.format("%."+numDec+"f",numAns) + ", " + String.format("%."+numDec+"f",numAns1);

                txtAns.setText(ans);
            }
        }
    }
}
