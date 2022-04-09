package com.salikh.puzzle15.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.salikh.puzzle15.R;
import com.salikh.puzzle15.core.cache.MemoryHelpr;

public class choseMain extends BaseActivity implements View.OnClickListener {


    TextView game4x4, game3x3;

    private FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_main);

        loadViews();

        game3x3.setOnClickListener(this);
        game4x4.setOnClickListener(this);

    }

    private void loadViews() {
        game3x3 = findViewById(R.id.game3x3);
        game4x4 = findViewById(R.id.game4x4);
        frameLayout = findViewById(R.id.content);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.game3x3: {
                Intent intent = new Intent(choseMain.this, game3x3.class);
                startActivity(intent);
                break;
            }
            case R.id.game4x4: {
                Intent intent = new Intent(choseMain.this, game4x4.class);
                startActivity(intent);
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