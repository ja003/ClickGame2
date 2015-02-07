package com.example.vukmir.oneSecGame.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.vukmir.oneSecGame.OneSecGame;
import com.example.vukmir.clickgame.R;
import com.example.vukmir.oneSecGame.fragment.LevelFragment;
import com.example.vukmir.oneSecGame.fragment.SecondLevelFragment;
import com.example.vukmir.oneSecGame.objects.Answer;

import java.util.Random;


public class MainActivity extends ActionBarActivity {
    private static String SCORE = "Your score: ";
    private Toolbar mToolbar;
    private TextView mTvMission;
    private int WIN_SCORE = 3;
    private int LOSE_SCORE = -1;
    private RelativeLayout viewableOption01;
    private OneSecGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupActionBar();

        setTitle("Welcome in game!");

        game = new OneSecGame();



        mTvMission = (TextView) findViewById(R.id.tv_mission);
    }


    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    private void setScore(int score) {
        game.SCORE = score;
        setTitle(SCORE + game.SCORE);
    }

    private void refreshScore() {
        setTitle(SCORE + game.SCORE);
    }


    private void setupActionBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    public void anotherQuestion(){
        if(game.SCORE > WIN_SCORE*game.LEVEL){
            increaseLevel();
        }
        else{
            Random random = new Random();
            int questNum = random.nextInt(5);
            game.QUEST_NUMBER = questNum;
            mTvMission.setText(game.QUESTIONS_LIST.get(questNum));
            //OneSecApplication.VIEWABLE_OPTIONS.get(questNum).setVisibility(View.VISIBLE);
        }
    }


    private void increaseLevel() {
        game.LEVEL++;
        Fragment fragment = null;

        if (game.LEVEL == 1) {
            fragment = new LevelFragment();
            game.QUESTIONS_LIST.clear();
            game.QUESTIONS_LIST.add("Click on blue");
            game.QUESTIONS_LIST.add("Click on red");
            game.QUESTIONS_LIST.add("Click on yellow");
            game.QUESTIONS_LIST.add("Click on green");
            game.QUESTIONS_LIST.add("Click on blue");
            game.CORRECT_ANSWERS_LIST.clear();
            game.CORRECT_ANSWERS_LIST.add(new Answer(1));
            game.CORRECT_ANSWERS_LIST.add(new Answer(2));
            game.CORRECT_ANSWERS_LIST.add(new Answer(3));
            game.CORRECT_ANSWERS_LIST.add(new Answer(4));
            game.CORRECT_ANSWERS_LIST.add(new Answer(1));


            //game.setCorrectAnswers();

            //RelativeLayout viewableOption01 = (RelativeLayout) findViewById(R.id.viewableOpt01);
            RelativeLayout viewableOption02 = (RelativeLayout) findViewById(R.id.viewableOpt01);
            viewableOption01 = (RelativeLayout) findViewById(R.id.viewableOpt01);
            //viewableOption01.setGravity(1);                                               //CRASH HERE
            //viewableOption01.setVisibility(View.VISIBLE);                                 //CRASH HERE

            game.VIEWABLE_OPTIONS.clear();
            game.VIEWABLE_OPTIONS.add(viewableOption01);
            game.VIEWABLE_OPTIONS.add(viewableOption02);
            game.VIEWABLE_OPTIONS.add(viewableOption01);
            game.VIEWABLE_OPTIONS.add(viewableOption02);
            game.VIEWABLE_OPTIONS.add(viewableOption01);

            //game.showViewableOption(game.QUEST_NUMBER);                                   //CRASH HERE


        } else {
            fragment = new SecondLevelFragment();
        }

        //show and replace fragment
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_level, fragment).commitAllowingStateLoss();
        }
    }


    public void onClickBtnStart(View view) {
        setScore(0);
        view.setVisibility(View.GONE);
        mTvMission.setVisibility(View.VISIBLE);
        increaseLevel();
        anotherQuestion();
    }




    public void onClickBtn1(View view) {
        game.ANSWER.setAnswer(1);
        checkAnswers();
    }


    public void onClickBtn2(View view) {
        game.ANSWER.setAnswer(2);
        checkAnswers();
    }


    public void onClickBtn3(View view) {
        game.ANSWER.setAnswer(3);
        checkAnswers();
    }


    public void onClickBtn4(View view) {
        game.ANSWER.setAnswer(4);
        checkAnswers();
    }

    public void checkAnswers(){
        if(game.ANSWER.compareAnswers(game.CORRECT_ANSWERS_LIST.get(game.QUEST_NUMBER))){
            game.SCORE++;
            //Toast.makeText(getApplicationContext(),"+",Toast.LENGTH_SHORT).show();
            refreshScore();
            //OneSecApplication.VIEWABLE_OPTIONS.get(OneSecApplication.QUEST_NUMBER).setVisibility(View.INVISIBLE);
            anotherQuestion();
        }
        else{
            game.SCORE--;
            //Toast.makeText(getApplicationContext(),"-",Toast.LENGTH_SHORT).show();
            refreshScore();
            //OneSecApplication.VIEWABLE_OPTIONS.get(OneSecApplication.QUEST_NUMBER).setVisibility(View.INVISIBLE);
            anotherQuestion();
        }
    }
}