package com.pheng.abel.geoquiz;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mokan on 2015/7/5.
 */
public class QuestionBank implements Parcelable {

    private int currentIndex;

    public QuestionBank() {
    }

    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_oceans, true)
    };

    public TrueFalse getCurrent() {
        return mQuestionBank[currentIndex];
    }

    public TrueFalse getNext() {
        return mQuestionBank[(++currentIndex) % mQuestionBank.length];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(currentIndex);
    }

    public static final Parcelable.Creator<QuestionBank> CREATOR = new Parcelable.Creator<QuestionBank>() {

        @Override
        public QuestionBank createFromParcel(Parcel source) {
            return new QuestionBank(source);
        }

        @Override
        public QuestionBank[] newArray(int size) {
            return new QuestionBank[0];
        }
    };

    private QuestionBank(Parcel in) {
        currentIndex = in.readInt();
    }
}
