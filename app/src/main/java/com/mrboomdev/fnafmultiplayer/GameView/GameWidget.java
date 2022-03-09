package com.mrboomdev.fnafmultiplayer.GameView;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GameWidget extends View {
    private final Paint paint;
    private int mFps = 0;
    private int mFpsCounter = 0;
    private long mFpsTime = 0;
    private int renderedFinal = 0;
    List<Bitmap> bitmaps = new ArrayList<>();

    public GameWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (SystemClock.uptimeMillis() - mFpsTime > 1000) {
            mFpsTime = SystemClock.uptimeMillis();
            mFps = mFpsCounter;
            mFpsCounter = 0;
        } else {
            mFpsCounter++;
        }
        if(bitmaps.size() > 0) {
            int rendered = 0;
            for (int i = 0; i < bitmaps.size(); i++) {
                canvas.drawBitmap(bitmaps.get(i), 0, 0, paint);
                rendered++;
            }
            renderedFinal = rendered;
            invalidate();
        }
    }
    
    public void loadBitmap(@NonNull Context context, String path, int width, int height) {
        AssetManager assetManager = context.getAssets();
        InputStream stream;
        Bitmap bitmap = null;
        try {
            stream = assetManager.open(path);
            bitmap = BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            Log.e("load", "Failed to load asset");
        }
        bitmaps.add(Bitmap.createScaledBitmap(bitmap, width, height, false));
    }
    
    public void addObject() {
    	
    }

    public int getFps() {
        return mFps;
    }
    public int getRendered() {
        return renderedFinal;
    }
}
