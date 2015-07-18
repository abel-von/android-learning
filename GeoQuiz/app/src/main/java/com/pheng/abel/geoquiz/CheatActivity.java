package com.pheng.abel.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mokan on 2015/7/5.
 */
public class CheatActivity extends Activity {
    public static final String EXTRA_ANSWER = "com.pheng.abel.geoqiuz.answer";
    public static final String EXTRA_CHEATED ="com.pheng.abel.geoquiz.cheated";
    private boolean mAnswer;

    private TextView mAnswerTextView;
    private Button mShowAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswer = getIntent().getBooleanExtra(EXTRA_ANSWER,false);
        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
        mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
        setResult(false);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswer){
                    mAnswerTextView.setText(R.string.true_button);
                }
                else{
                    mAnswerTextView.setText(R.string.false_button);
                }
                setResult(true);
            }
        });
    }
    private void setResult(boolean cheated){
        Intent i = new Intent();
        i.putExtra(EXTRA_CHEATED,cheated);
        setResult(RESULT_OK, i);
    }
}
