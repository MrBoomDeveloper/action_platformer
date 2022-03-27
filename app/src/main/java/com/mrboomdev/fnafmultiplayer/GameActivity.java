package com.mrboomdev.fnafmultiplayer;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.mrboomdev.fnafmultiplayer.GameView.GameView;

public class GameActivity extends AppCompatActivity {
    GameView game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        game = findViewById(R.id.game);
        ImageView jump = findViewById(R.id.jump);
        ImageView left = findViewById(R.id.left);
        ImageView right = findViewById(R.id.right);
        ImageView attack = findViewById(R.id.attack);

        //game.loadMap(this, "map1");
        //game.loadCharacter(0);

        jump.setOnClickListener(v -> finishAffinity());
        left.setOnClickListener(v -> finishAffinity());
        right.setOnClickListener(v -> finishAffinity());
        attack.setOnClickListener(v -> finishAffinity());
    }
}