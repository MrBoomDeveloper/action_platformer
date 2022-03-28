/*
PLEASE DELETE THIS WHOLE FILE, WHEN THE NEW GAME VIEW WILL BE FINISHED
*/

package com.mrboomdev.fnafmultiplayer.GameView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

@Deprecated
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
    List<Bitmap> bitmaps = new ArrayList<>();

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
        for(int y = 0; y < mapHeight; y++) {
            for(int x = 0; x < mapWidth; x++) {
                try {
                    Bitmap bitmap = bitmaps.get(map.getJSONArray("objects").getInt(x+y*10-10));
                    canvas.drawBitmap(bitmap, x*50, y*50-50, paint);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                rendered++;
            }
        }
        renderedFinal = rendered;
        invalidate();
    }

    public void loadMap(Context context, String name) {
        String jsonString = asset.loadFile(context, "maps/" + name + ".json");
        try {
            map = new JSONObject(jsonString);
            mapWidth = map.getInt("width");
            mapHeight = map.getInt("height");
            for(int i = 0; i < 2; i++) {
                bitmaps.add(asset.loadBitmap(context, "textures/" + i + ".png"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void loadCharacter(int id) {
        Log.d("load", "failed to load a character" + id);
    }
}
