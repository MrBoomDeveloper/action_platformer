package com.mrboomdev.fnafmultiplayer.GameView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import org.json.JSONException;
import org.json.JSONObject;

public class GameWidget extends View {
    private final Paint paint;
    public int mFps = 0;
    private int mFpsCounter = 0;
    private long mFpsTime = 0;
    public int renderedFinal = 0;
    private int mapWidth = 0;
    private int mapHeight = 0;
    private JSONObject map;
    private final Assets asset = new Assets();

    public GameWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
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
        int rendered = 0;
        for(int i = 0; i < mapHeight; i++) {
            for(int i1 = 0; i1 < mapWidth; i1++) {
                rendered++;
                loadObject(i + i1);
            }
        }
        renderedFinal = rendered;
        invalidate();
    }

    public void loadMap(Context context, String name) {
        String jsonString = asset.loadJson(context, "maps/" + name + ".json");
        try {
            map = new JSONObject(jsonString);
            mapWidth = map.getInt("width");
            mapHeight = map.getInt("height");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void loadCharacter(int id) {
        Log.d("load", "failed to load a character");
    }

    private void loadObject(int id) {

    }

    public int getLoaded() {
        return 0;
    }
}