package dev.wixx.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnGetResult;
    EditText txtPrinciple, txtTime, txtRate;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtPrinciple = (EditText) findViewById(R.id.txtPrinciple);
        txtRate = (EditText) findViewById(R.id.txtRate);
        txtTime = (EditText) findViewById(R.id.txtTime);
        result = (TextView) findViewById(R.id.txtResult);

        btnGetResult = (Button) findViewById(R.id.btnResult);

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
            }
        });



    }

    private boolean checkIfNumberisEmpty(String principle, String rate, String time) {
        return !TextUtils.isEmpty(rate) && !TextUtils.isEmpty(time) && !TextUtils.isEmpty(principle);
    }

    private double calculateInterest(double principle, double rate, double time) {

        return principle * (rate / 100) * time;
    }

}
