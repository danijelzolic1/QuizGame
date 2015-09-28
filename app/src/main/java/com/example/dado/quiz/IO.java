package com.example.dado.quiz;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

public class IO {

    private static final String STANDARDFRAGOR = "fragor.txt";
    private ArrayList<String> questions;
    private ArrayList<ArrayList<String>> allQuestions;
    private BufferedReader br;
    private AssetManager mngr;
    private InputStream is;
    

    public IO(Context context) throws IOException {
        Log.i("IO", "Start IO");
        mngr = context.getAssets();
        is = mngr.open("fragor.txt");
        br = new BufferedReader(new InputStreamReader(is));

    }

    /*
     * Method for reading questions from .txt file.
     */
    private void readFile() throws Exception {
        questions = new ArrayList<String>();
        allQuestions = new ArrayList<ArrayList<String>>();

			/*
			 * reads line by line and add them to an ArrayList of strings, if
			 * new row without signs the ArrayList will be added to a bigger
			 * ArrrayList
			 */
        String line;
        while ((line = br.readLine()) != null) {
            if (line.equals("")) {
                @SuppressWarnings("unchecked")
                ArrayList<String> tempQuest = ((ArrayList<String>) questions.clone());
                allQuestions.add(tempQuest);
                questions.clear();
            } else {
                questions.add(line);
            }
        }
        br.close();
    }

    /*
     * Method for returning all the questions read from file
     */
    public ArrayList<ArrayList<String>> getArray() throws Throwable {
        readFile();
        return allQuestions;
    }

}