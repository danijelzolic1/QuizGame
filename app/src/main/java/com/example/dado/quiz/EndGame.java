package com.example.dado.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class EndGame extends ActionBarActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_game);

        textView = (TextView) findViewById(R.id.scoreView);
        int score = getIntent().getIntExtra("Score", -1);
        textView.setText("Your final score is: " + score);
    }

}
