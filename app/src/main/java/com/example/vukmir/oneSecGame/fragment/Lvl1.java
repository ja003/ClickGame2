package com.example.vukmir.oneSecGame.fragment;

import android.graphics.drawable.Drawable;

import com.example.vukmir.clickgame.R;
import com.example.vukmir.oneSecGame.OneSecGame;

import java.util.Arrays;
import java.util.List;

public class Lvl1 extends BaseFragment {

    @Override
    public List<Drawable> setupDrawables() {
        OneSecGame.CORRECT_ANSWER = randInt();

        return Arrays.asList(
                getResources().getDrawable(R.drawable.box_blue),
                getResources().getDrawable(R.drawable.box_green),
                getResources().getDrawable(R.drawable.box_red),
                getResources().getDrawable(R.drawable.box_yellow)
        );
    }

    @Override
    public List<String> setupTexts() {
        return Arrays.asList(
                "Option 1",
                "Option 2",
                "Option 3",
                "Option 4"
        );
    }
}
