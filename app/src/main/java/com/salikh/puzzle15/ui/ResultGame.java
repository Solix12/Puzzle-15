package com.salikh.puzzle15.ui;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;

import com.salikh.puzzle15.R;
import com.salikh.puzzle15.core.cache.MemoryHelpr;
import com.salikh.puzzle15.core.models.Userdat;

import java.util.ArrayList;

public class ResultGame extends BaseActivity {

    private LinearLayout rootGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_game);

        rootGroup = findViewById(R.id.content);

        ArrayList<Userdat> list = MemoryHelpr.getHelpr().getLastResults();

        for (Userdat data : list) {

            TextView text = new TextView(this);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            StringBuilder builder = new StringBuilder();

            builder.append("<b>Name: </b>");
            builder.append(data.getName());
            builder.append("    ");
            builder.append("Step: ");
            builder.append(data.getStep());
            builder.append("    ");
            builder.append("Time: ");
            builder.append(getTime(data.getTime()));

            text.setText(HtmlCompat.fromHtml(builder.toString(), HtmlCompat.FROM_HTML_MODE_COMPACT));
            text.setLayoutParams(params);

            int size = getResources().getDimensionPixelSize(R.dimen.Textdp);
            int color = getResources().getColor(R.color.white);
            text.setTextColor(color);
            text.setTextSize(size);
            rootGroup.addView(text);

        }

    }

    private String getTime(int n) {

        int soat = n / 3600;
        int minut = n % 3600 / 60;
        int sekund = n % 60;

        return String.format("%02d:%02d:%02d", soat, minut, sekund);
    }

    @Override
    public boolean shouldStopMusic() {
        return false;
    }

    @Override
    public void setThemeData() {

        rootGroup.setBackgroundResource(MemoryHelpr.getHelpr().getThemeOrder());

    }
}