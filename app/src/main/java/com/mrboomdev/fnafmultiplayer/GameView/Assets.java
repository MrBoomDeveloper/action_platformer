package com.mrboomdev.fnafmultiplayer.GameView;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.NonNull;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Assets {
    public String loadFile(@NonNull Context context, String path) {
        String file;
        try {
            InputStream is = context.getAssets().open(path);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            file = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }

    public Bitmap loadBitmap(@NonNull Context context, String path) {
        AssetManager assetManager = context.getAssets();
        InputStream is;
        Bitmap bitmap = null;
        try {
            is = assetManager.open(path);
            bitmap = BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}