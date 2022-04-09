package com.salikh.puzzle15.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.salikh.puzzle15.R;
import com.salikh.puzzle15.core.cache.MemoryHelpr;

public class StartActivity extends BaseActivity implements View.OnClickListener {


    private TextView startButton, settingButton, exitButton, resultButton;

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadView();

        exitButton.setOnClickListener(this);
        settingButton.setOnClickListener(this);
        startButton.setOnClickListener(this);
        resultButton.setOnClickListener(this);

    }


    private void loadView() {

        settingButton = findViewById(R.id.setting_btn);
        startButton = findViewById(R.id.start_btn);
        exitButton = findViewById(R.id.exit_btn);
        resultButton = findViewById(R.id.result_btn);
        frameLayout = findViewById(R.id.content);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_btn: {
                Intent intent = new Intent(StartActivity.this, choseMain.class);
                startActivity(intent);
                break;
            }
            case R.id.setting_btn: {
                Intent intent = new Intent(StartActivity.this, SettingsActivty.class);
                startActivity(intent);
                break;
            }
            case R.id.result_btn: {
                Intent intent = new Intent(StartActivity.this, ResultGame.class);
                startActivity(intent);
                break;
            }
            case R.id.exit_btn: {
                finish();
                break;
            }
        }
    }


    @Override
    public boolean shouldStopMusic() {
        return false;
    }

    @Override
    public void setThemeData() {

        frameLayout.setBackgroundResource(MemoryHelpr.getHelpr().getThemeOrder());
    }
}