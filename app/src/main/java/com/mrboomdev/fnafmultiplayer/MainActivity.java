package com.mrboomdev.fnafmultiplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_FNaFMultiplayer);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}