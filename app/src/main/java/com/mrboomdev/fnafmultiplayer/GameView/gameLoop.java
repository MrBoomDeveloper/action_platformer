package com.mrboomdev.fnafmultiplayer.GameView;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class gameLoop extends Thread {
    //Set the maximum number of frames per second
    private static final double MAX_UPS = 30.0;
    private static final double UPS_PERIOD = 1E+3/MAX_UPS;
    private boolean isRunning = false;
    private SurfaceHolder surfaceHolder;
    private GameView game;
    private double averageUps;
    private double averageFps;

    public gameLoop(GameView game, SurfaceHolder holder) {
        this.game = game;
        this.surfaceHolder = holder;
    }

    public double getAverageUps() {
        return averageUps;
    }

    public double getAverageFps() {
        return averageFps;
    }

    public void startLoop() {
        isRunning = true;
        start();
    }

    @Override
    public void run() {
        super.run();
        int updateCount = 0, frameCount = 0;
        long startTime, elapsedTime = 0, sleepTime = 0;
        Canvas canvas = null;
        startTime = System.currentTimeMillis();

        while (isRunning) {
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    game.update();
                    updateCount++;
                    game.draw(canvas);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } finally {
                if(canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long)(updateCount * UPS_PERIOD - elapsedTime);
            if(sleepTime > 0) {
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while(sleepTime < 0 && updateCount < MAX_UPS - 1) {
                game.update();
                updateCount++;
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long)(updateCount * UPS_PERIOD - elapsedTime);
            }
            elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= 1000) {
                averageUps = updateCount / (1E-3 * elapsedTime);
                averageFps = frameCount / (1E-3 * elapsedTime);
                updateCount = 0;
                frameCount = 0;
                startTime = System.currentTimeMillis();
            }
        }
    }
}