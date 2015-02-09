package com.example.vukmir.oneSecGame.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vukmir.clickgame.R;
import com.example.vukmir.oneSecGame.OneSecGame;
import com.example.vukmir.oneSecGame.activity.MainActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BaseFragment extends Fragment {
    private View mRootView;

    private static List<Drawable> listDrawables;
    private static List<String> listTexts;
    private static List<Button> listButtons;


    private static String QUEST = "Click on the ";
    private static String SCORE = "Your score: ";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_level, container, false);

        listDrawables = setupDrawables();
        listTexts = setupTexts();

        if (listDrawables.size() == 4) {
            //optional
            randomizeDrawablesList();

            setupButtons();
        }

        setQuest();

        return mRootView;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        // save current instance state
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }


    public List<Drawable> setupDrawables() {
        return Arrays.asList(getResources().getDrawable(R.drawable.box_blue));
    }


    public List<String> setupTexts() {
        return Arrays.asList("Option 1");
    }

    public static int randInt() {

        Random rand = new Random();

        int randomNum = rand.nextInt((4 - 1) + 1) + 1;

        return randomNum;
    }


    private void setQuest() {
        TextView quest = (TextView) mRootView.findViewById(R.id.tv_mission);
        quest.setText(QUEST + OneSecGame.CORRECT_ANSWER + " box");
    }


    private void setActionBarTitle(String title) {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(title);
    }


    private void setupButtons() {

        listButtons = Arrays.asList(
                findButtonById(R.id.button1),
                findButtonById(R.id.button2),
                findButtonById(R.id.button3),
                findButtonById(R.id.button4)
        );

        for (int i = 0; i < listButtons.size(); i++) {
            listButtons.get(i).setBackground(listDrawables.get(i));
            listButtons.get(i).setText(listTexts.get(i));
            listButtons.get(i).setTag(i + 1);
            listButtons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handleButtonClick(view);
                }
            });
        }
    }


    private void handleButtonClick(View view) {
        Toast.makeText(getActivity().getApplicationContext(), "button tag " + view.getTag(), Toast.LENGTH_SHORT).show();


        if (view.getTag().equals(OneSecGame.CORRECT_ANSWER)) {
            OneSecGame.SCORE++;
        } else {
            OneSecGame.SCORE--;
        }

        refreshScore();
        increaseLevel();
    }


    private Button findButtonById(int id) {
        Button button = (Button) mRootView.findViewById(id);
        return button;
    }


    private void randomizeDrawablesList() {
        long seed = System.nanoTime();
        Collections.shuffle(listDrawables, new Random(seed));
    }

    private void refreshScore() {
        setActionBarTitle(SCORE + OneSecGame.SCORE);
    }


    private void increaseLevel() {
        OneSecGame.LEVEL++;
        Fragment fragment = null;


        if (OneSecGame.LEVEL == 1) {
            fragment = new Lvl1();
        } else {
            fragment = new Lvl2();
        }


        //show and replace fragment
        if (fragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_level, fragment).commitAllowingStateLoss();
        }
    }
}