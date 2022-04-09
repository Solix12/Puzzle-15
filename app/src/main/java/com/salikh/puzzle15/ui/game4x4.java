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
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.salikh.puzzle15.R;
import com.salikh.puzzle15.core.cache.MemoryHelpr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class game4x4 extends BaseActivity {


    private final ArrayList<Integer> numbers = new ArrayList<>();

    private final Button[][] buttons = new Button[4][4];
    boolean isPaus = false;
    private RelativeLayout buttonGroup;
    private Timer timer;
    private TextView timeView;
    private TextView steepView;
    private LinearLayout resartView;
    private FrameLayout frameLayout;
    private FrameLayout content;
    private MediaPlayer mediaPlayerBtn;
    private int stap = 0;
    private int time = 0;
    private int emptyI = 3;
    private int emptyJ = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4x4);

        mediaPlayerBtn = MediaPlayer.create(this, R.raw.clic);

        loadViews();

        if (savedInstanceState != null) {
            loadLastData(savedInstanceState);
        } else {
            loadNumbers();
            setDataToView();
        }

        checkWin();
        creatTime();
        setOnClickListener();


    }

    private void checkWin() {
        boolean isWin = true;
        for (int i = 0; i < 15; i++) {
            Button button = (Button) buttonGroup.getChildAt(i);

            if (!button.getText().toString().equals(i + 1 + "")) {
                isWin = false;
                break;
            }
        }
        if (isWin) {
            Toast.makeText(this, "Congration !!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadLastData(Bundle bundle) {

        time = bundle.getInt("time");
        setTime(time);
        stap = bundle.getInt("step");
        setStep();

        emptyI = bundle.getInt("emptyI");
        emptyJ = bundle.getInt("emptyJ");


        ArrayList<String> buttonsText = bundle.getStringArrayList("buttons");

        for (int i = 0; i < 16; i++) {
            buttons[i / 4][i % 4].setText(buttonsText.get(i));
        }

        buttons[0][0].setBackgroundResource(R.drawable.ic_mars);
        buttons[0][1].setBackgroundResource(R.drawable.ic_oy);
        buttons[0][2].setBackgroundResource(R.drawable.luna);
        buttons[0][3].setBackgroundResource(R.drawable.uran);
        buttons[1][0].setBackgroundResource(R.drawable.ic_saturn);
        buttons[1][1].setBackgroundResource(R.drawable.yupiter);
        buttons[1][2].setBackgroundResource(R.drawable.ic_yer);
        buttons[1][3].setBackgroundResource(R.drawable.ic_kok);
        buttons[2][0].setBackgroundResource(R.drawable.kla);
        buttons[2][1].setBackgroundResource(R.drawable.sayora);
        buttons[2][2].setBackgroundResource(R.drawable.sayora2);
        buttons[2][3].setBackgroundResource(R.drawable.luna);
        buttons[3][0].setBackgroundResource(R.drawable.ic_yer);
        buttons[3][1].setBackgroundResource(R.drawable.sayora3);
        buttons[3][2].setBackgroundResource(R.drawable.uran);
        buttons[3][3].setBackgroundResource(R.drawable.ic_saturn);

        buttons[3][3].setBackgroundResource(R.drawable.luna);
        buttons[emptyI][emptyJ].setBackgroundResource(R.drawable.empty_button);

    }

    public void loadNumbers() {
        for (int i = 1; i < 16; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);
    }

    private void loadViews() {
        frameLayout = findViewById(R.id.pause_view);
        timeView = findViewById(R.id.cloc_view);
        steepView = findViewById(R.id.step_view);
        buttonGroup = findViewById(R.id.btn_group);
        content = findViewById(R.id.content);
        resartView = findViewById(R.id.resart_bnt);

        int a = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j] = (Button) buttonGroup.getChildAt(a++);
            }
        }

    }

    private void setDataToView() {
        setStep();


        for (int i = 0; i < 15; i++) {
            Button button = buttons[i / 4][i % 4];
            button.setText(String.valueOf(numbers.get(i)));
        }

        buttons[0][0].setBackgroundResource(R.drawable.ic_mars);
        buttons[0][1].setBackgroundResource(R.drawable.ic_oy);
        buttons[0][2].setBackgroundResource(R.drawable.luna);
        buttons[0][3].setBackgroundResource(R.drawable.uran);
        buttons[1][0].setBackgroundResource(R.drawable.ic_saturn);
        buttons[1][1].setBackgroundResource(R.drawable.yupiter);
        buttons[1][2].setBackgroundResource(R.drawable.ic_yer);
        buttons[1][3].setBackgroundResource(R.drawable.ic_kok);
        buttons[2][0].setBackgroundResource(R.drawable.kla);
        buttons[2][1].setBackgroundResource(R.drawable.sayora);
        buttons[2][2].setBackgroundResource(R.drawable.sayora2);
        buttons[2][3].setBackgroundResource(R.drawable.luna);
        buttons[3][0].setBackgroundResource(R.drawable.ic_yer);
        buttons[3][1].setBackgroundResource(R.drawable.sayora3);
        buttons[3][2].setBackgroundResource(R.drawable.uran);
        buttons[3][3].setBackgroundResource(R.drawable.ic_saturn);

        buttons[emptyI][emptyJ].setText("");
        buttons[emptyI][emptyJ].setBackgroundResource(R.drawable.empty_button);

    }

    private void setStep() {
        // find gilib set qilasiz
        steepView.setText(String.valueOf(stap));

    }

    private void creatTime() {

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

    private void setTime(int n) {

        int soat = n / 3600;
        int minut = n % 3600 / 60;
        int sekund = n % 60;

        timeView.setText(String.format("%02d:%02d:%02d", soat, minut, sekund));
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
            stap++;

            setStep();

            mediaPlayerBtn.start();

            if (emptyI == 3 && emptyJ == 3) {
                checkToWin();

            }
        }
    }

    private void checkToWin() {

        boolean isWin = true;

        for (int i = 0; i < 15; i++) {
            Button button = (Button) buttonGroup.getChildAt(i);
            if (!button.getText().toString().equals(i + 1 + "")) {
                isWin = false;
                break;
            }
        }

        if (isWin) {
            Intent intent = new Intent(game4x4.this, ResultActivity.class);

            startActivity(intent);
        }

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
        stap = 0;

        emptyI = 3;
        emptyJ = 3;
        setTime(time);

        setStep();

        creatTime();

        loadNumbers();

        setDataToView();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putInt("step", stap);
        outState.putInt("time", time);

        outState.putInt("emptyI", emptyI);
        outState.putInt("emptyJ", emptyJ);

        ArrayList<String> buttonsText = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            buttonsText.add(buttons[i / 4][i % 4].getText().toString());
        }

        outState.putStringArrayList("buttons", buttonsText);

        super.onSaveInstanceState(outState);

    }

    public void onClickPause(View view) {
        if (!isPaus) {
            frameLayout.setVisibility(View.VISIBLE);
            buttonGroup.setVisibility(View.GONE);
            timer.cancel();
        } else {
            buttonGroup.setVisibility(View.VISIBLE);
            frameLayout.setVisibility(View.GONE);
            creatTime();
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