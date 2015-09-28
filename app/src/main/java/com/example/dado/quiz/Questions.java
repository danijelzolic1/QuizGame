package com.example.dado.quiz;


import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

public class Questions {

    private ArrayList<ArrayList<String>> allQuestion;
    private ArrayList<String> question;
    private IO io;
    int i = 0;
    public Questions(Context context) throws Throwable {
        Log.i("Questions", "Innan IO");
        io = new IO(context);
        Log.i("Questions", "Efter IO");
        getArray();
    }

    private void getArray() throws Throwable {
        Log.i("Question", "inne i getArray");
        allQuestion = io.getArray();
        Collections.shuffle(allQuestion);

    }

    public ArrayList<String> getQuestion(){
        question = allQuestion.get(i);
        i++;
        return question;
    }
}
