package com.example.vukmir.oneSecGame;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.vukmir.oneSecGame.objects.Answer;

import java.util.ArrayList;
import java.util.List;


public class OneSecGame extends Application {
    private static OneSecGame mInstance;

    public static int LEVEL;
    public static int SCORE;
    public static int QUEST_NUMBER;
    public static Answer ANSWER;
    public static List<Answer> CORRECT_ANSWERS_LIST;
    public static List<String> QUESTIONS_LIST;
    public static List<RelativeLayout> VIEWABLE_OPTIONS;


    public OneSecGame() {
        mInstance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // force AsyncTask to be initialized in the main thread due to the bug:
        // http://stackoverflow.com/questions/4280330/onpostexecute-not-being-called-in-asynctask-handler-runtime-exception
        try {
            Class.forName("android.os.AsyncTask");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        LEVEL = 0;
        SCORE = 0;
        QUEST_NUMBER = 0;
        ANSWER = new Answer(0);
        CORRECT_ANSWERS_LIST = new ArrayList<>();
        QUESTIONS_LIST = new ArrayList<>();
        VIEWABLE_OPTIONS = new ArrayList<>();
    }

    public static Context getContext() {
        return mInstance;
    }


    public void setCorrectAnswers(List<Answer> answers){
        if(answers == null){
            throw new NullPointerException();
        }
        CORRECT_ANSWERS_LIST.clear();
        CORRECT_ANSWERS_LIST.addAll(answers);
    }

    public void showViewableOption(int i){
        VIEWABLE_OPTIONS.get(i).setVisibility(View.VISIBLE);
    }

    public void hideViewableOption(int i){
        VIEWABLE_OPTIONS.get(i).setVisibility(View.GONE);
    }

}