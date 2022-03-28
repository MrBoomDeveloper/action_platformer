package com.mrboomdev.fnafmultiplayer.GameView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.annotation.NonNull;

public class Player {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private double posX, posY;
    private final double radius;

    public Player(double posX, double posY, double radius) {
        paint.setColor(Color.RED);
        this.posX = posX;
        this.posY = posY;
        this.radius = radius;
    }

    public void drawCanvas(@NonNull Canvas canvas) {
        canvas.drawCircle((float) posX, (float) posY, (float) radius, paint);
    }

    public void update() {
    }

    public void setPosition(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }
}