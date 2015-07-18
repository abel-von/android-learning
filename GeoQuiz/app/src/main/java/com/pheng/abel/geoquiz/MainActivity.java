package com.pheng.abel.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 程序怎么可以没有注释？
 * 注释中文字体怎么会这么大?
 * OK, English comment, the font is the same to code
 */
public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private static final String KEY_BANK = "bank";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private boolean hasCheated;
    private TextView mQuestionTextView;
    private TextView mVersionTextView;
    private QuestionBank mQuestionBank;
    private TrueFalse currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);
        if (null == savedInstanceState) {
            mQuestionBank = new QuestionBank();
        } else {
            mQuestionBank = savedInstanceState.getParcelable(KEY_BANK);
        }
        currentQuestion = mQuestionBank.getCurrent();
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuestion = mQuestionBank.getNext();
                mQuestionTextView.setText(currentQuestion.getmQuestion());
                hasCheated = false;
            }
        });
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setText(currentQuestion.getmQuestion());

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CheatActivity.class);
                boolean answer = currentQuestion.ismQuestionAnswer();
                i.putExtra(CheatActivity.EXTRA_ANSWER, answer);
                startActivityForResult(i, 0);
            }
        });

        mVersionTextView = (TextView)findViewById(R.id.version_text_view);
        mVersionTextView.setText(Build.VERSION.SDK_INT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (null == data) {
            return;
        }
        hasCheated = data.getBooleanExtra(CheatActivity.EXTRA_CHEATED,false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestory() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_BANK, mQuestionBank);
    }

    private void checkAnswer(boolean clientAnswer) {

        boolean realAnswer = currentQuestion.ismQuestionAnswer();
        int messageId;
        if(hasCheated){
            messageId = R.string.judgment_toast;
        } else if (realAnswer == clientAnswer) {
            messageId = R.string.correct_toast;
        } else {
            messageId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show();
    }
}
