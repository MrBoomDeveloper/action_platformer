package com.mrboomdev.fnafmultiplayer;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.mrboomdev.fnafmultiplayer.GameView.GameWidget;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    GameWidget game;
    TextView fps;
    TextView rendered;
    TextView loaded;
    int myId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        game = findViewById(R.id.game);
        fps = findViewById(R.id.fps);
        rendered = findViewById(R.id.rendered);
        loaded = findViewById(R.id.loaded);

        game.loadMap(this, "map1");
        game.loadCharacter(0);
        myId = 0;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                update();
            }}, 0, 1000);
    }

    public void update() {
        runOnUiThread(() -> {
            fps.setText(String.valueOf(game.getFps()));
            rendered.setText(String.valueOf(game.getRendered()));
            loaded.setText(String.valueOf(game.getLoaded()));
        });
    }
}