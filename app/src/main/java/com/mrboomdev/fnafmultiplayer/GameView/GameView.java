package com.mrboomdev.fnafmultiplayer.GameView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private Context context;
    private gameLoop gameLoop;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        this.context = context;
        gameLoop = new gameLoop(this, holder);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawDebug(canvas);
    }

    public void drawDebug(Canvas canvas) {
        String averageUps = Double.toString(gameLoop.getAverageUps());
        String averageFps = Double.toString(gameLoop.getAverageFps());
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(12);
        canvas.drawText("Ups: " + averageUps, 10, 10, paint);
        canvas.drawText("Fps: " + averageFps, 10, 30, paint);
    }

    public void update() {
    }
}