package dev.wixx.calculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnGetResult, btnRetrieve, btnClear;
    EditText txtPrinciple, txtTime, txtRate;
    TextView result;

    SharedPreferences preferenceDatabase;
    SharedPreferences.Editor editor;

    ArrayList<String> preferencesResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtPrinciple = (EditText) findViewById(R.id.txtPrinciple);
        txtRate = (EditText) findViewById(R.id.txtRate);
        txtTime = (EditText) findViewById(R.id.txtTime);
        result = (TextView) findViewById(R.id.txtResult);
        btnClear = (Button)findViewById(R.id.btnClear);
        btnGetResult = (Button) findViewById(R.id.btnResult);
        btnRetrieve = (Button)findViewById(R.id.btnRetrieve);
        preferenceDatabase = getSharedPreferences("data", Context.MODE_PRIVATE);

        btnGetResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkIfNumberisEmpty(txtPrinciple.getText().toString(), txtRate.getText().toString(),
                        txtTime.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please Enter all Fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                double principle = Double.parseDouble(txtPrinciple.getText().toString());
                double rate = Double.parseDouble(txtRate.getText().toString());
                double time = Double.parseDouble(txtTime.getText().toString());


                result.setText(String.valueOf(calculateInterest(principle,rate,time)));
                saveValuesPreferences(principle,rate,time,calculateInterest(principle,rate,time));
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkIfNumberisEmpty(txtPrinciple.getText().toString(),txtRate.getText().toString(),txtTime.getText().toString())){
                    Toast.makeText(MainActivity.this, "Fields are Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                clearEditTexts(txtPrinciple,txtRate,txtTime,result);
            }
        });
        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPrinciple.setText(fetchValues().get(0));
                txtRate.setText(fetchValues().get(1));
                txtTime.setText(fetchValues().get(2));
                result.setText(fetchValues().get(3));

            }
        });

    }

    private boolean checkIfNumberisEmpty(String principle, String rate, String time) {
        return !TextUtils.isEmpty(rate) && !TextUtils.isEmpty(time) && !TextUtils.isEmpty(principle);
    }

    private double calculateInterest(double principle, double rate, double time) {

        return principle * (rate / 100) * time;
    }
    private void clearEditTexts(EditText txtPrinciple, EditText txtRate,EditText txtTime, TextView txtResult){
        txtPrinciple.setText("");
        txtRate.setText("");
        txtTime.setText("");
        txtResult.setText("");
    }
    private void saveValuesPreferences(double principle,double rate,double time, double result){
        editor = preferenceDatabase.edit();
        editor.putString("principle",String.valueOf(principle));
        editor.putString("rate",String.valueOf(rate));
        editor.putString("time",String.valueOf(time));
        editor.putString("result",String.valueOf(result));
        editor.apply();
    }

    private ArrayList<String> fetchValues(){
        preferencesResult = new ArrayList<>();
        String principle =preferenceDatabase.getString("principle","null");
        String rate = preferenceDatabase.getString("rate","null");
        String time = preferenceDatabase.getString("time","null");
        String result = preferenceDatabase.getString("result","null");
        preferencesResult.add(principle);
        preferencesResult.add(rate);
        preferencesResult.add(time);
        preferencesResult.add(result);

        return preferencesResult;
    }


}
