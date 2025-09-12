package com.earl.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        myButtonListenerMethod();
    }
    public void myButtonListenerMethod(){
        Button button= (Button) findViewById(R.id.button);
        button.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final EditText heightText = (EditText) findViewById(R.id.heightinput);
                String heightStr = heightText.getText().toString();
                double height = Double.parseDouble(heightStr);

                final EditText weightText = (EditText) findViewById(R.id.weightinput);
                String weightStr = weightText.getText().toString();
                double weight = Double.parseDouble(weightStr);

                double BMI= (weight)/(height*height);
                final EditText BMIResult = (EditText) findViewById(R.id.BMIResult);

                BMIResult.setText(Double.toString(BMI));

                String BMI_Cat;
                if (BMI < 15)
                    BMI_Cat = "Very severely underweight";
                else if (BMI < 16)
                    BMI_Cat ="Severely underweight";
                else if (BMI < 18.5)
                    BMI_Cat ="Underweight";
                else if (BMI < 25)
                    BMI_Cat ="Normal";
                else if (BMI < 30)
                    BMI_Cat ="Overweight";
                else if (BMI < 35)
                    BMI_Cat ="Obese Class 1 - Moderately Obese";
                else if (BMI < 40)
                    BMI_Cat ="Obese Class 2 - Severely Obese";
                else
                    BMI_Cat ="Obese Class 3 - Very Severely Obese";

                final TextView BMICategory = (TextView) findViewById(R.id.BMICategory);
                BMICategory.setText(BMI_Cat);
            }
        });
    }



}