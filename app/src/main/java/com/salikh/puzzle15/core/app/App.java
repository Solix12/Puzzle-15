package com.salikh.puzzle15.core.app;

import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;

import com.salikh.puzzle15.R;
import com.salikh.puzzle15.core.cache.MemoryHelpr;

public class App extends Application {

    public static MediaPlayer mediaPlayer;
    private static Context appContext;

    public static void startMediaPlayer() {
        mediaPlayer = MediaPlayer.create(appContext, R.raw.usk);
        mediaPlayer.setLooping(true);
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    public static void stopMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        MemoryHelpr.init(this);

        if (!MemoryHelpr.getHelpr().getSoundState()) {
            startMediaPlayer();
        }


    }

    @Override
    public void onTerminate() {
        stopMediaPlayer();
        mediaPlayer = null;
        super.onTerminate();
    }
}