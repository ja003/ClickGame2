package com.example.vukmir.oneSecGame.objects;

/**
 * Created by Vukmir on 6.2.2015.
 */
public class Answer {
    int answer;

    public Answer(int answer) {
        this.answer = answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getAnswer() {
        return answer;
    }

    public boolean compareAnswers(Answer ans) {
        if (ans.getAnswer() == answer) {
            return true;
        } else
            return false;
    }
}
