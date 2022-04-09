package com.salikh.puzzle15.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.salikh.puzzle15.R;
import com.salikh.puzzle15.core.cache.MemoryHelpr;
import com.salikh.puzzle15.core.models.Userdat;

public class ResultActivity extends BaseActivity {


    private final int time = 0;
    private final int step = 0;
    private String name;

    private EditText editText;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        loadView();

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = editText.getText().toString();


                if (name.length() == 0) {
                    Toast.makeText(ResultActivity.this, "Input name !", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    MemoryHelpr.getHelpr().saveData(new Userdat(name, step, time));
                    Toast.makeText(ResultActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ResultActivity.this, StartActivity.class);
                    startActivity(intent);
                }

            }
        });

        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void loadView() {
        editText = findViewById(R.id.name_input);
        linearLayout = findViewById(R.id.content);
    }

    @Override
    public boolean shouldStopMusic() {
        return false;
    }

    @Override
    public void setThemeData() {

        linearLayout.setBackgroundResource(MemoryHelpr.getHelpr().getThemeOrder());

    }
}