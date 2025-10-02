package com.earl.diceroller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BmiFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Use your BMI layout here. If your file is activity_bmi.xml, inflate that.
        return inflater.inflate(R.layout.fragment_bmi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle s) {
        myButtonListenerMethod(root);
    }

    // Your code, unchanged in logic. Only "root.findViewById" instead of "findViewById".
    public void myButtonListenerMethod(View root) {
        Button button = (Button) root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText heightText = (EditText) root.findViewById(R.id.heightinput);
                String heightStr = heightText.getText().toString();
                double height = Double.parseDouble(heightStr);
                // If your height input is in CM, uncomment next line:
                // height = height / 100.0;

                final EditText weightText = (EditText) root.findViewById(R.id.weightinput);
                String weightStr = weightText.getText().toString();
                double weight = Double.parseDouble(weightStr);

                double BMI = (weight) / (height * height);
                final EditText BMIResult = (EditText) root.findViewById(R.id.BMIResult);
                BMIResult.setText(Double.toString(BMI));

                String BMI_Cat;
                if (BMI < 15)
                    BMI_Cat = "Very severely underweight";
                else if (BMI < 16)
                    BMI_Cat = "Severely underweight";
                else if (BMI < 18.5)
                    BMI_Cat = "Underweight";
                else if (BMI < 25)
                    BMI_Cat = "Normal";
                else if (BMI < 30)
                    BMI_Cat = "Overweight";
                else if (BMI < 35)
                    BMI_Cat = "Obese Class 1 - Moderately Obese";
                else if (BMI < 40)
                    BMI_Cat = "Obese Class 2 - Severely Obese";
                else
                    BMI_Cat = "Obese Class 3 - Very Severely Obese";

                final TextView BMICategory = (TextView) root.findViewById(R.id.BMICategory);
                BMICategory.setText(BMI_Cat);

                final TextView deltaInfo = (TextView) root.findViewById(R.id.deltaInfo);
                StringBuilder sb = new StringBuilder();

                if (BMI < 15) {
                    sb.append(String.format("You need to gain %.1f BMI to reach 15.0", (15 - BMI)));
                } else if (BMI < 16) {
                    sb.append(String.format("Range: 15.0 - 16.0. You are %.1f above the lower bound and need %.1f more to reach the upper bound.",
                            (BMI - 15), (16 - BMI)));
                } else if (BMI < 18.5) {
                    sb.append(String.format("Range: 16.0 - 18.5. You are %.1f above the lower bound and need %.1f more to reach the upper bound.",
                            (BMI - 16), (18.5 - BMI)));
                } else if (BMI < 25) {
                    sb.append(String.format("Range: 18.5 - 25.0. You are %.1f above the lower bound and need %.1f more to reach the upper bound.",
                            (BMI - 18.5), (25 - BMI)));
                } else if (BMI < 30) {
                    sb.append(String.format("Range: 25.0 - 30.0. You are %.1f above the lower bound and need %.1f more to reach the upper bound.",
                            (BMI - 25), (30 - BMI)));
                } else if (BMI < 35) {
                    sb.append(String.format("Range: 30.0 - 35.0. You are %.1f above the lower bound and need %.1f more to reach the upper bound.",
                            (BMI - 30), (35 - BMI)));
                } else if (BMI < 40) {
                    sb.append(String.format("Range: 35.0 - 40.0. You are %.1f above the lower bound and need %.1f more to reach the upper bound.",
                            (BMI - 35), (40 - BMI)));
                } else {
                    sb.append(String.format("You are %.1f above the lower bound (40.0+).", (BMI - 40)));
                }

                deltaInfo.setText(sb.toString());
            }
        });
    }
}
