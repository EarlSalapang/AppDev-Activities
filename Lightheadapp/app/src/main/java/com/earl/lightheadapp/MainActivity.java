package com.earl.lightheadapp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ConstraintLayout bgElement = (ConstraintLayout)findViewById(R.id.activity_main);
        bgElement.setBackgroundColor(Color.WHITE);
        myButtonListenerMethod();
        setupCycleButton();
    }

    public void myButtonListenerMethod(){
        button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintLayout bgElement= (ConstraintLayout)findViewById(R.id.activity_main);
                int color=((ColorDrawable)bgElement.getBackground()).getColor();
                if (color == Color.RED) {
                    bgElement.setBackgroundColor(Color.BLUE);
                }
                else {
                    bgElement.setBackgroundColor(Color.RED);
                }

            }

        });
    }
    private Button buttonCycle;
    private final int[] cycleColors = new int[]{ Color.BLUE, Color.RED, Color.WHITE };
    private int cycleIndex = 0;
    private boolean isCycling = false;
    private final android.os.Handler cycleHandler = new android.os.Handler(android.os.Looper.getMainLooper());
    private final Runnable cycleTask = new Runnable() {
        @Override public void run() {
            ConstraintLayout bg = findViewById(R.id.activity_main);
            bg.setBackgroundColor(cycleColors[cycleIndex]);
            cycleIndex = (cycleIndex + 1) % cycleColors.length;
            if (isCycling) cycleHandler.postDelayed(this, 600); // speed (ms)
        }
    };

    private void setupCycleButton() {
        buttonCycle = findViewById(R.id.button_cycle);
        if (buttonCycle == null) return; // safety if XML not added yet
        buttonCycle.setText("Start");
        buttonCycle.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (!isCycling) {
                    isCycling = true;
                    buttonCycle.setText("End");
                    cycleHandler.post(cycleTask);
                } else {
                    isCycling = false;
                    buttonCycle.setText("Start");
                    cycleHandler.removeCallbacks(cycleTask);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cycleHandler.removeCallbacks(cycleTask);
    }

}