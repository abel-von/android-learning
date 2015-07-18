package com.pheng.abel.geoquiz;

/**
 * Created by mokan on 2015/7/4.
 */
public class TrueFalse {
    private int mQuestion;

    private boolean mQuestionAnswer;

    public TrueFalse(final int mQuestion, final boolean mQuestionAnswer) {
        this.mQuestion = mQuestion;
        this.mQuestionAnswer = mQuestionAnswer;
    }

    public boolean ismQuestionAnswer() {
        return mQuestionAnswer;
    }

    public void setmQuestionAnswer(boolean mQuestionAnswer) {
        this.mQuestionAnswer = mQuestionAnswer;
    }

    public int getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }
}
