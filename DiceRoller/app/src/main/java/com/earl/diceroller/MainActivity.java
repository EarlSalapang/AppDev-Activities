package com.earl.diceroller;

import android.os.Bundle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_main);

        MaterialToolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView nav = findViewById(R.id.nav_view);

        ActionBarDrawerToggle t =
                new ActionBarDrawerToggle(this, drawer, tb, R.string.nav_open, R.string.nav_close);
        drawer.addDrawerListener(t); t.syncState();

        nav.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_bmi) {
                show(new BmiFragment(), "BMI");
            } else if (id == R.id.menu_dice) {
                show(new DiceFragment(), "Dice");
            }
            drawer.closeDrawers();
            return true;
        });

        if (s == null) { // default screen
            nav.setCheckedItem(R.id.menu_bmi);
            show(new BmiFragment(), "BMI");
        }
    }

    private void show(Fragment f, String title) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_container, f)
                .commit();
        if (getSupportActionBar() != null) getSupportActionBar().setTitle(title);
    }
}
