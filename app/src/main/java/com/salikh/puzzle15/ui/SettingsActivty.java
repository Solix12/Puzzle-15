package com.salikh.puzzle15.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.salikh.puzzle15.R;
import com.salikh.puzzle15.core.app.App;
import com.salikh.puzzle15.core.cache.MemoryHelpr;

public class SettingsActivty extends BaseActivity {

    private Switch switchSound;
    private Button buttonClear;
    private TextView textView;
    private ImageView themeButton1, themeButton2, themeButton3;
    private LinearLayout root_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_activty);
        loadView();

        setButtonClear();
        setSwitchSound();
        setLastDat();
        setThemes();
    }

    private void setLastDat() {
        boolean isOff = MemoryHelpr.getHelpr().getSoundState();
        switchSound.setChecked(isOff);
        if (isOff) {
            textView.setText(getString(R.string.music_on));
        } else {
            textView.setText(getString(R.string.music_off));
        }
    }

    private void loadView() {
        switchSound = findViewById(R.id.sound_off);
        buttonClear = findViewById(R.id.clear_btn);
        textView = findViewById(R.id.text_switch);
        themeButton1 = findViewById(R.id.main_bac1);
        themeButton2 = findViewById(R.id.main_bac2);
        themeButton3 = findViewById(R.id.main_bac3);
        root_view = findViewById(R.id.root_view);
    }

    private void setButtonClear() {

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoryHelpr.getHelpr().clearData();
                Toast.makeText(SettingsActivty.this, "Cleared", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setSwitchSound() {

        switchSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                MemoryHelpr.getHelpr().setSoundState(b);

                if (b) {
                    App.stopMediaPlayer();
                } else {
                    App.startMediaPlayer();
                }
                setLastDat();
            }
        });

    }

    private void setThemes() {

        themeButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoryHelpr.getHelpr().setThemeOrder(R.drawable.main_bac);
                setThemeData();
            }
        });

        themeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoryHelpr.getHelpr().setThemeOrder(R.drawable.main_bac_2);
                setThemeData();

            }
        });

        themeButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoryHelpr.getHelpr().setThemeOrder(R.drawable.main_bac_3);
                setThemeData();
            }
        });

    }


    @Override
    public boolean shouldStopMusic() {
        return false;
    }

    @Override
    public void setThemeData() {
        root_view.setBackgroundResource(MemoryHelpr.getHelpr().getThemeOrder());
    }
}