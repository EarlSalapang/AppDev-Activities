package com.earl.diceroller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.Random;

public class DiceFragment extends Fragment {

    private Spinner spin;
    private TextView diceResult;
    private ImageView img, img1, img2, img3, img4;
    private final Random rand = new Random();
    private static final long DUR = 400L;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle s) {
        return inflater.inflate(R.layout.fragment_dice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle s) {
        // Activity -> Fragment changes: use root.findViewById and requireContext()
        spin = root.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(), R.array.dice_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        diceResult = root.findViewById(R.id.diceResult);
        img  = root.findViewById(R.id.diceImage);
        img1 = root.findViewById(R.id.diceImage1);
        img2 = root.findViewById(R.id.diceImage2);
        img3 = root.findViewById(R.id.diceImage3);
        img4 = root.findViewById(R.id.diceImage4);

        Button button = root.findViewById(R.id.rollbutton);
        button.setOnClickListener(v -> roll());
    }

    private void roll() {
        int r1 = rand.nextInt(6) + 1;
        int r2 = rand.nextInt(6) + 1;
        int r3 = rand.nextInt(6) + 1;
        int r4 = rand.nextInt(6) + 1;

        int diceNum;
        try { diceNum = Integer.parseInt(spin.getSelectedItem().toString()); }
        catch (Exception e) { diceNum = 1; }

        // Mirror your original visibility mapping
        if (diceNum == 1) {
            show(img); hide(img1, img2, img3, img4);
            diceResult.setText(String.valueOf(r1));
            img.animate().rotationBy(360f).setDuration(DUR).withEndAction(() -> setDieFace(img, r1)).start();

        } else if (diceNum == 2) {
            hide(img); show(img1, img2); hide(img3, img4);
            diceResult.setText(r1 + "," + r2);
            img1.animate().rotationBy(360f).setDuration(DUR).withEndAction(() -> setDieFace(img1, r1)).start();
            img2.animate().rotationBy(360f).setDuration(DUR).withEndAction(() -> setDieFace(img2, r2)).start();

        } else if (diceNum == 3) {
            show(img, img1, img2); hide(img3, img4);
            diceResult.setText(r1 + "," + r2 + "," + r3);
            img.animate().rotationBy(360f).setDuration(DUR).withEndAction(() -> setDieFace(img, r1)).start();
            img1.animate().rotationBy(360f).setDuration(DUR).withEndAction(() -> setDieFace(img1, r2)).start();
            img2.animate().rotationBy(360f).setDuration(DUR).withEndAction(() -> setDieFace(img2, r3)).start();

        } else {
            hide(img); show(img1, img2, img3, img4);
            diceResult.setText(r1 + "," + r2 + "," + r3 + "," + r4);
            img1.animate().rotationBy(360f).setDuration(DUR).withEndAction(() -> setDieFace(img1, r1)).start();
            img2.animate().rotationBy(360f).setDuration(DUR).withEndAction(() -> setDieFace(img2, r2)).start();
            img3.animate().rotationBy(360f).setDuration(DUR).withEndAction(() -> setDieFace(img3, r3)).start();
            img4.animate().rotationBy(360f).setDuration(DUR).withEndAction(() -> setDieFace(img4, r4)).start();
        }
    }

    private void setDieFace(ImageView img, int roll) {
        switch (roll) {
            case 1: img.setImageResource(R.drawable.dice1); break;
            case 2: img.setImageResource(R.drawable.dice2); break;
            case 3: img.setImageResource(R.drawable.dice3); break;
            case 4: img.setImageResource(R.drawable.dice4); break;
            case 5: img.setImageResource(R.drawable.dice5); break;
            case 6: img.setImageResource(R.drawable.dice6); break;
        }
    }

    private void show(ImageView... ivs) { for (ImageView iv: ivs) iv.setVisibility(View.VISIBLE); }
    private void hide(ImageView... ivs) { for (ImageView iv: ivs) iv.setVisibility(View.INVISIBLE); }
}

//package com.earl.diceroller; import android.os.Bundle; import android.view.View; import android.widget.ArrayAdapter; import android.widget.Button; import android.widget.ImageView; import android.widget.Spinner; import android.widget.TextView; import androidx.activity.EdgeToEdge; import androidx.appcompat.app.AppCompatActivity; import androidx.core.graphics.Insets; import androidx.core.view.ViewCompat; import androidx.core.view.WindowInsetsCompat; import java.util.Random; public class MainActivity extends AppCompatActivity { private Spinner spin; @Override protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); EdgeToEdge.enable(this); setContentView(R.layout.activity_main); ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> { Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()); v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom); return insets; }); myButtonListenerMethod(); spin = (Spinner) findViewById(R.id.spinner2); ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( this, R.array.dice_spinner, android.R.layout.simple_spinner_item ); adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); spin.setAdapter(adapter); } public void myButtonListenerMethod() { Button button = (Button) findViewById(R.id.rollbutton); button.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { Random rand = new Random(); int rollResult = rand.nextInt(6) + 1; int rollResult2 = rand.nextInt(6) + 1; int rollResult3 = rand.nextInt(6) + 1; int rollResult4 = rand.nextInt(6) + 1; TextView diceResult = (TextView) findViewById(R.id.diceResult); long dur = 400L; ImageView img = findViewById(R.id.diceImage); ImageView img1 = findViewById(R.id.diceImage1); ImageView img2 = findViewById(R.id.diceImage2); ImageView img3 = findViewById(R.id.diceImage3); ImageView img4 = findViewById(R.id.diceImage4); int diceNum = Integer.parseInt(spin.getSelectedItem().toString()); if (diceNum == 1) { img.setVisibility(View.VISIBLE); img1.setVisibility(View.INVISIBLE); img2.setVisibility(View.INVISIBLE); img3.setVisibility(View.INVISIBLE); img4.setVisibility(View.INVISIBLE); diceResult.setText(String.valueOf(rollResult)); img.animate().rotationBy(360f).setDuration(dur).withEndAction(() -> { setDieFace(img, rollResult); }).start(); } else if (diceNum == 2) { img.setVisibility(View.INVISIBLE); img1.setVisibility(View.VISIBLE); img2.setVisibility(View.VISIBLE); img3.setVisibility(View.INVISIBLE); img4.setVisibility(View.INVISIBLE); diceResult.setText(String.format("%d,%d", rollResult, rollResult2)); img1.animate().rotationBy(360f).setDuration(dur).withEndAction(() -> { setDieFace(img1, rollResult); }).start(); img2.animate().rotationBy(360f).setDuration(dur).withEndAction(() -> { setDieFace(img2, rollResult); }).start(); } else if (diceNum == 3) { img.setVisibility(View.VISIBLE); img1.setVisibility(View.VISIBLE); img2.setVisibility(View.VISIBLE); img3.setVisibility(View.INVISIBLE); img4.setVisibility(View.INVISIBLE); diceResult.setText(String.format("%d,%d,%d", rollResult, rollResult2, rollResult3)); img.animate().rotationBy(360f).setDuration(dur).withEndAction(() -> { setDieFace(img, rollResult); }).start(); img1.animate().rotationBy(360f).setDuration(dur).withEndAction(() -> { setDieFace(img1, rollResult2); }).start(); img2.animate().rotationBy(360f).setDuration(dur).withEndAction(() -> { setDieFace(img2, rollResult3); }).start(); } else { img.setVisibility(View.INVISIBLE); img1.setVisibility(View.VISIBLE); img2.setVisibility(View.VISIBLE); img3.setVisibility(View.VISIBLE); img4.setVisibility(View.VISIBLE); diceResult.setText(String.format("%d,%d,%d,%d", rollResult, rollResult2, rollResult3, rollResult4)); img1.animate().rotationBy(360f).setDuration(dur).withEndAction(() -> { setDieFace(img1, rollResult); }).start(); img2.animate().rotationBy(360f).setDuration(dur).withEndAction(() -> { setDieFace(img2, rollResult2); }).start(); img3.animate().rotationBy(360f).setDuration(dur).withEndAction(() -> { setDieFace(img3, rollResult3); }).start(); img4.animate().rotationBy(360f).setDuration(dur).withEndAction(() -> { setDieFace(img4, rollResult4); }).start(); } } }); } private void setDieFace(ImageView img, int roll) { switch (roll) { case 1: img.setImageResource(R.drawable.dice1); break; case 2: img.setImageResource(R.drawable.dice2); break; case 3: img.setImageResource(R.drawable.dice3); break; case 4: img.setImageResource(R.drawable.dice4); break; case 5: img.setImageResource(R.drawable.dice5); break; case 6: img.setImageResource(R.drawable.dice6); break; } } } // public void myButtonListenerMethod(){ // Button button = (Button) findViewById(R.id.rollbutton); // button.setOnClickListener(new // View.OnClickListener() { // @Override // public void onClick(View v) { // Random rand = new Random(); // int rollResult = rand.nextInt(6) + 1; // int rollResult2 = rand.nextInt(6) + 1; // // TextView diceResult = (TextView) findViewById(R.id.diceResult); // diceResult.setText(String.format("%d, %d", rollResult, rollResult2)); // // ImageView img = (ImageView) findViewById(R.id.diceImage); // ImageView img2 = (ImageView) findViewById(R.id.diceImage2); // // long dur = 400L; // // img.animate() // .rotationBy(360f) // .setDuration(dur) // .withEndAction(new Runnable() { // @Override public void run() { // switch (rollResult) { // case 1: img.setImageResource(R.drawable.dice1); break; // case 2: img.setImageResource(R.drawable.dice2); break; // case 3: img.setImageResource(R.drawable.dice3); break; // case 4: img.setImageResource(R.drawable.dice4); break; // case 5: img.setImageResource(R.drawable.dice5); break; // case 6: img.setImageResource(R.drawable.dice6); break; // } // } // }).start(); // // img2.animate() // .rotationBy(360f) // .setDuration(dur) // .withEndAction(new Runnable() { // @Override public void run() { // switch (rollResult2) { // case 1: img2.setImageResource(R.drawable.dice1); break; // case 2: img2.setImageResource(R.drawable.dice2); break; // case 3: img2.setImageResource(R.drawable.dice3); break; // case 4: img2.setImageResource(R.drawable.dice4); break; // case 5: img2.setImageResource(R.drawable.dice5); break; // case 6: img2.setImageResource(R.drawable.dice6); break; // } // } // }).start(); // } // }); // } this is my dice roller

