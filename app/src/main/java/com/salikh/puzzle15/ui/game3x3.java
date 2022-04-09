package com.salikh.puzzle15.ui;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.salikh.puzzle15.R;
import com.salikh.puzzle15.core.cache.MemoryHelpr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class game3x3 extends BaseActivity {

    private final ArrayList<Integer> numbers = new ArrayList<>();

    private final Button[][] buttons = new Button[3][3];
    public MediaPlayer mediaPlayerBtn;
    Timer timer;
    TextView steepView;
    TextView timeView;
    boolean isPaus = false;
    private LinearLayout resartView;
    private FrameLayout frameLayout;
    private FrameLayout content;
    private RelativeLayout buttonGrop;
    private int emptyI = 2;
    private int emptyJ = 2;
    private int steep = 0;
    private int time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3x3);

        mediaPlayerBtn = MediaPlayer.create(this, R.raw.clic);


        loadViews();


        if (savedInstanceState != null) {
            loadLastData(savedInstanceState);
        } else {
            loadNumbers();
            setDataToView();
        }


        checkWin();
        creatTimer();
        setOnClickListener();


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putInt("step", steep);
        outState.putInt("time", time);

        outState.putInt("emptyI", emptyI);
        outState.putInt("emptyJ", emptyJ);

        ArrayList<String> buttonsText = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            buttonsText.add(buttons[i / 3][i % 3].getText().toString());
        }

        outState.putStringArrayList("buttons", buttonsText);

        super.onSaveInstanceState(outState);

    }

    private void loadLastData(Bundle bundle) {

        time = bundle.getInt("time");
        setTime(time);
        steep = bundle.getInt("step");
        setSteep();

        emptyI = bundle.getInt("emptyI");
        emptyJ = bundle.getInt("emptyJ");

        buttons[2][2].setBackgroundResource(R.drawable.luna);
        buttons[emptyI][emptyJ].setBackgroundResource(R.drawable.empty_button);

        ArrayList<String> buttonsText = bundle.getStringArrayList("buttons");

        for (int i = 0; i < 9; i++) {
            buttons[i / 3][i % 3].setText(buttonsText.get(i));
        }


    }

    private void creatTimer() {
        timer = null;
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        setTime(time);
                    }
                });
            }
        }, 1000, 1000);

    }

    private void setTime(int time) {

        int soat = time / 36000;
        int minut = time % 3600 / 60;
        int sikund = time % 60;

        timeView.setText(String.format("%02d:%02d:%02d", soat, minut, sikund));
    }

    private void checkWin() {

        boolean isWin = true;
        for (int i = 0; i < 8; i++) {
            Button button = (Button) buttonGrop.getChildAt(i);

            if (!button.getText().toString().equals(i + 1 + "")) {
                isWin = false;
                break;
            }
        }

        if (isWin) {
            Intent intent = new Intent(game3x3.this, ResultActivity.class);
            startActivity(intent);
        }
    }

    private void setDataToView() {

        setSteep();

        for (int i = 0; i < 8; i++) {
            Button button = buttons[i / 3][i % 3];
            button.setText(String.valueOf(numbers.get(i)));
        }
        buttons[emptyI][emptyJ].setText("");

        buttons[0][0].setBackgroundResource(R.drawable.ic_mars);
        buttons[0][1].setBackgroundResource(R.drawable.moon);
        buttons[0][2].setBackgroundResource(R.drawable.kla);
        buttons[1][0].setBackgroundResource(R.drawable.uran);
        buttons[1][1].setBackgroundResource(R.drawable.yupiter);
        buttons[1][2].setBackgroundResource(R.drawable.ic_yer);
        buttons[2][0].setBackgroundResource(R.drawable.ic_saturn);
        buttons[2][1].setBackgroundResource(R.drawable.ic_kok);
        buttons[emptyI][emptyJ].setBackgroundResource(R.drawable.empty_button);
    }

    private void loadViews() {

        frameLayout = findViewById(R.id.pause_view);
        content = findViewById(R.id.content);
        resartView = findViewById(R.id.resart_bnt);
        timeView = findViewById(R.id.timeView);
        steepView = findViewById(R.id.steepView);
        buttonGrop = findViewById(R.id.btn_group);

        int a = 0;
        for (int i = 0; i < 9; i++) {
            buttons[i / 3][i % 3] = (Button) buttonGrop.getChildAt(a++);
        }

    }

    private void loadNumbers() {

        numbers.clear();

        for (int i = 1; i < 9; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

    }

    public void buttoning(View view) {

        Button button = (Button) view;
        String tag = button.getTag().toString();

        String[] indexes = tag.split(":");

        int i = Integer.parseInt(indexes[0]);
        int j = Integer.parseInt(indexes[1]);

        int deltaI = Math.abs(i - emptyI);
        int deltaJ = Math.abs(j - emptyJ);

        if ((deltaI == 1 && deltaJ == 0) || (deltaI == 0 && deltaJ == 1)) {
            buttons[emptyI][emptyJ].setText(button.getText());
            buttons[emptyI][emptyJ].setBackground(button.getBackground());

            button.setText("");
            button.setBackgroundResource(R.drawable.empty_button);

            emptyI = i;
            emptyJ = j;
            steep++;

            setSteep();
            mediaPlayerBtn.start();

            if (emptyI == 2 && emptyJ == 2) {
                checkWin();
            }
        }
    }

    private void setSteep() {


        steepView.setText(String.valueOf(steep));

    }

    public void setOnClickListener() {
        resartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restxartGame();
            }
        });
    }

    private void restxartGame() {
        timer.cancel();

        time = 0;
        steep = 0;

        emptyI = 2;
        emptyJ = 2;
        setTime(time);

        setSteep();

        creatTimer();

        loadNumbers();

        setDataToView();
    }

    public void onClickPause(View view) {
        if (!isPaus) {
            frameLayout.setVisibility(View.VISIBLE);
            buttonGrop.setVisibility(View.GONE);
            timer.cancel();
        } else {
            buttonGrop.setVisibility(View.VISIBLE);
            frameLayout.setVisibility(View.GONE);
            creatTimer();
        }

        isPaus = !isPaus;
    }

    @Override
    public boolean shouldStopMusic() {
        return false;
    }

    @Override
    public void setThemeData() {

        content.setBackgroundResource(MemoryHelpr.getHelpr().getThemeOrder());
        frameLayout.setBackgroundResource(MemoryHelpr.getHelpr().getThemeOrder());

    }


}