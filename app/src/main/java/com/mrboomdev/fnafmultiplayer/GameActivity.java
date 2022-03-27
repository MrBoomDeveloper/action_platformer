package com.mrboomdev.fnafmultiplayer;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.mrboomdev.fnafmultiplayer.GameView.GameView;

public class GameActivity extends AppCompatActivity {
    GameView game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        game = findViewById(R.id.game);

        //game.loadMap(this, "map1");
        //game.loadCharacter(0);
    }
}