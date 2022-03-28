package com.mrboomdev.fnafmultiplayer.GameView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private final gameLoop gameLoop;
    private final Player player;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        gameLoop = new gameLoop(this, holder);
        
        player = new Player(100, 100, 50);
        
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                player.setPosition(event.getX(), event.getY());
                return true;
            case MotionEvent.ACTION_UP:
                performClick();
                return true;
        }
        return false;
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
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
        player.drawCanvas(canvas);
    }

    public void drawDebug(@NonNull Canvas canvas) {
        String averageUps = Double.toString(gameLoop.averageUps);
        String averageFps = Double.toString(gameLoop.averageFps);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(12);
        canvas.drawText("Ups: " + averageUps, 10, 10, paint);
        canvas.drawText("Fps: " + averageFps, 10, 30, paint);
    }

    public void update() {
        player.update();
    }
}