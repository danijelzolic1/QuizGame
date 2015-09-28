package com.example.dado.quiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class ActivityTwo extends ActionBarActivity {

    private Button answer1, answer2, answer3, answer4;
    private TextView question;
    private String correntAnswer;
    private ArrayList<String> questions;
    private ArrayList<String> buttons;
    private ArrayList<Button> btn;
    private Questions quest;
    private int nr, nrOfRightAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        buttons = new ArrayList<String>();
        questions = new ArrayList<String>();
        btn = new ArrayList<Button>();
        nr = 0;
        nrOfRightAnswers= 0;
        question = (TextView)findViewById(R.id.questionView);
        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);
        btn.add(answer1);
        btn.add(answer2);
        btn.add(answer3);
        btn.add(answer4);
        Log.i("Activity Two", "Innan objekt");
        try {
            quest = new Questions(getApplicationContext());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Log.i("Activity Two", "Efter objekt");
        getQuest();
    }

    private void getQuest(){
        nr++;
        resetButtonsColor();
        questions = quest.getQuestion();
        Log.i("Activity Two", questions.toString());

        nextQuestion(questions);
    }

    private void nextQuestion(ArrayList<String> q){

        correntAnswer = questions.get(1);
        for(int i = 1; i<5; i++){
            buttons.add(questions.get(i));
        }
        Collections.shuffle(buttons);

        question.setText(questions.get(0));
        answer1.setText(buttons.get(0));
        answer2.setText(buttons.get(1));
        answer3.setText(buttons.get(2));
        answer4.setText(buttons.get(3));
        buttonsOn();
        questions.clear();

        for(int i = 0; i<btn.size(); i++){
            btn.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button b = (Button) view;
                    if(b.getText().equals(correntAnswer)) {
                        b.setBackgroundColor(Color.GREEN);
                        nrOfRightAnswers++;
                    }

                    else {
                        b.setBackgroundColor(Color.RED);
                        for(int i = 0; i<btn.size(); i++){
                            if(btn.get(i).getText().equals(correntAnswer))
                                btn.get(i).setBackgroundColor(Color.GREEN);
                        }

                    }
                    if(nr == 5){
                        Intent intent = new Intent(getApplicationContext(), EndGame.class);
                        intent.putExtra("Score", nrOfRightAnswers);
                        startActivity(intent);
                    }
                    buttonsOff();

                }
            });
        }
    }

    private void buttonsOff() {

        for(int i = 0; i<btn.size(); i++){
            btn.get(i).setClickable(false);
        }
        buttons.clear();

        getQuest();
    }

    private void buttonsOn(){
        for(int i = 0; i<btn.size(); i++){
            btn.get(i).setClickable(true);
        }
    }

    private void resetButtonsColor(){
        for(int i = 0; i<btn.size(); i++){
            btn.get(i).setBackgroundColor(Color.WHITE);
        }
    }

}
