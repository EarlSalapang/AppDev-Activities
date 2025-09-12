package com.earl.diceroller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

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
        Button button = (Button) findViewById(R.id.rollbutton);
        button.setOnClickListener(new
                  View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          Random rand = new Random();
                          int rollResult  = rand.nextInt(6) + 1;
                          int rollResult2 = rand.nextInt(6) + 1;

                          TextView diceResult = (TextView) findViewById(R.id.diceResult);
                          diceResult.setText(rollResult + rollResult2);

                          ImageView img  = (ImageView) findViewById(R.id.diceImage);
                          ImageView img2 = (ImageView) findViewById(R.id.diceImage2);

                          long dur = 400L;

                          img.animate()
                                  .rotationBy(360f)
                                  .setDuration(dur)
                                  .withEndAction(new Runnable() {
                                      @Override public void run() {
                                          switch (rollResult) {
                                              case 1: img.setImageResource(R.drawable.dice1); break;
                                              case 2: img.setImageResource(R.drawable.dice2); break;
                                              case 3: img.setImageResource(R.drawable.dice3); break;
                                              case 4: img.setImageResource(R.drawable.dice4); break;
                                              case 5: img.setImageResource(R.drawable.dice5); break;
                                              case 6: img.setImageResource(R.drawable.dice6); break;
                                          }
                                      }
                                  }).start();

                          img2.animate()
                                  .rotationBy(360f)
                                  .setDuration(dur)
                                  .withEndAction(new Runnable() {
                                      @Override public void run() {
                                          switch (rollResult2) {
                                              case 1: img2.setImageResource(R.drawable.dice1); break;
                                              case 2: img2.setImageResource(R.drawable.dice2); break;
                                              case 3: img2.setImageResource(R.drawable.dice3); break;
                                              case 4: img2.setImageResource(R.drawable.dice4); break;
                                              case 5: img2.setImageResource(R.drawable.dice5); break;
                                              case 6: img2.setImageResource(R.drawable.dice6); break;
                                          }
                                      }
                                  }).start();
                      }
                  });
    }
}