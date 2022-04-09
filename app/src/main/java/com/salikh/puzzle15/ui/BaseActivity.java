package com.salikh.puzzle15.ui;

import androidx.appcompat.app.AppCompatActivity;

import com.salikh.puzzle15.core.app.App;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onDestroy() {
        if (shouldStopMusic()) {
            App.stopMediaPlayer();
        }
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        setThemeData();
        super.onResume();
    }


    public abstract boolean shouldStopMusic();

    public abstract void setThemeData();
}
