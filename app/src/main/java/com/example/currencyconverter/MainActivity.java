package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText amountEditText;
    private Button convertToUsdButton;
    private Button convertToSarButton;
    private TextView resultTextView;

    private static final double SAR_TO_USD_RATE = 3.75;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountEditText = findViewById(R.id.amountEditText);
        convertToUsdButton = findViewById(R.id.convertToUsdButton);
        convertToSarButton = findViewById(R.id.convertToSarButton);
        resultTextView = findViewById(R.id.resultTextView);

        convertToUsdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency(true); // Convert to USD
            }
        });

        convertToSarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency(false); // Convert to SAR
            }
        });
    }

    private void convertCurrency(boolean toUsd) {
        String amountString = amountEditText.getText().toString();
        if (amountString.isEmpty()) {
            Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double amount = Double.parseDouble(amountString);
            double convertedAmount;
            String resultText;

            if (toUsd) {
                convertedAmount = amount / SAR_TO_USD_RATE;
                resultText = String.format("Result: %.2f USD", convertedAmount);
            } else {
                convertedAmount = amount * SAR_TO_USD_RATE;
                resultText = String.format("Result: %.2f SAR", convertedAmount);
            }
            resultTextView.setText(resultText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid amount entered", Toast.LENGTH_SHORT).show();
        }
    }
}
