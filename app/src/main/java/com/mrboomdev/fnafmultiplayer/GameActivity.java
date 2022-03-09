package com.mrboomdev.fnafmultiplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.mrboomdev.fnafmultiplayer.GameView.GameWidget;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    GameWidget game;
    TextView fps;
    TextView rendered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        game = findViewById(R.id.game);
        fps = findViewById(R.id.fps);
        rendered = findViewById(R.id.rendered);

        game.loadBitmap(this, "amogus.png", 150, 150);
        
        game.addObject(

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
        });
    }
}