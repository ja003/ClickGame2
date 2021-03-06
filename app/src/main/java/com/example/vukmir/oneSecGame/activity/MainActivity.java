package com.example.vukmir.oneSecGame.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.vukmir.clickgame.R;
import com.example.vukmir.oneSecGame.OneSecGame;
import com.example.vukmir.oneSecGame.fragment.Lvl1;


public class MainActivity extends ActionBarActivity {
    private static String YOUR_SCORE = "Your score: ";
    private Toolbar mToolbar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setupActionBar();
        
        setTitle("Welcome in game!");
        
    }
    
    
    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }
    
    
    private void setScore(int score) {
        OneSecGame.SCORE = score;
        setTitle(YOUR_SCORE + OneSecGame.SCORE);
    }
    
    
    private void setupActionBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }
    
    
    public void onClickBtnStart(View view) {
        setScore(0);
        ((RelativeLayout) findViewById(R.id.container_info)).setVisibility(View.GONE);
        
        // start LVL 1
        Fragment fragment = new Lvl1();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_level, fragment).commitAllowingStateLoss();
    }
    
    
}