package com.example.interest_cal;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.view.View;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText principal;private int principalValue;
    private int timeperiodValue;
    private int periodVal;
    private Spinner freq;
    private EditText depositamount;
    private TextView YSD;
    private EditText rateofInterest;
    private int freqValue;
    private int depositVal;
    private SeekBar period;
    private float rateVal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        principal = findViewById(R.id.InitialInvestment);
        freq = findViewById(R.id.PaymentInterval);
        YSD= findViewById(R.id.YearsTxt);
        rateofInterest = findViewById(R.id.InterestRate);
        depositamount = findViewById(R.id.RegularPaymentVal);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Payment, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        freq.setAdapter(adapter);
        period = findViewById(R.id.Seekbar_years);
        period.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }@Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                YSD.setText(progress+" yearss");
                periodVal = progress;
            }
        });

        Button Calbtn = (Button)findViewById(R.id.CalculateBtn);
        Calbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                principalValue = Integer.parseInt(principal.getText().toString());
                String frequ = freq.getSelectedItem().toString();
                if(frequ.equals("Monthly"))
                    freqValue = 12;
                else if(frequ.equals("Quarterly"))
                    freqValue = 4;
                else if(frequ.equals("Semi-annually"))
                    freqValue = 2;
                else if(frequ.equals("Annually"))
                    freqValue = 1;
                double amount;
                int p = periodVal;
                depositVal = Integer.parseInt(depositamount.getText().toString());
                rateVal = Float.parseFloat(rateofInterest.getText().toString());
                float r = rateVal/100;
                amount = (principalValue * Math.pow((1+r), p)) + ((depositVal * freqValue) * ((Math.pow((1+r), p) - 1)/r));
                TextView CTD = findViewById(R.id.CIT);
                DecimalFormat df = new DecimalFormat("#.##");
                df.setRoundingMode(RoundingMode.UP);
                CTD.setText("Compound Interest: "+String.valueOf(df.format(amount)));

            }
        });

    }
}

