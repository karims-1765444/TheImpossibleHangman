package com.example.theimpossiblehangman.Player;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.theimpossiblehangman.R;

public class SetupEvilHangman extends AppCompatActivity {
    private static final int MAX_WRONG_ANSWERS = 18;
    private static final int [] validWordLengths = {2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,24,28,29};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evil_hangman_setup);

        Button startGameBtn = findViewById(R.id.startGameBtn);
        startGameBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                EditText wordLengthEditText = findViewById(R.id.wordLengthEditText);
                if (wordLengthEditText.getText().length()==0){
                    wordLengthEditText.setHint(getString(R.string.emptyInput));
                    return;
                }
                int wordLength = Integer.parseInt(wordLengthEditText.getText().toString());
                EditText wrongAnswersEditText = findViewById(R.id.wrongAnswersEditText);
                if (wrongAnswersEditText.getText().length()==0){
                    wrongAnswersEditText.setHint(getString(R.string.emptyInput));
                    return;
                }
                int wrongAnswers = Integer.parseInt(wrongAnswersEditText.getText().toString());
                boolean lengthValid = false;
                for (int validLength : validWordLengths){
                    if (validLength == wordLength){
                        lengthValid = true;
                    }
                }
                if (wrongAnswers <= MAX_WRONG_ANSWERS && lengthValid) {
                        Intent playGame = new Intent(getApplicationContext(), PlayEvilHangman.class);
                        playGame.putExtra("length", wordLength);
                        playGame.putExtra("guesses", wrongAnswers);
                        startActivity(playGame);
                } else {
                    if (!lengthValid) {
                        TextView lengthWordErrorTextView = findViewById(R.id.lengthWordErrorTextView);
                        lengthWordErrorTextView.setText(getString(R.string.lengthError));
                    }
                    if (wrongAnswers > MAX_WRONG_ANSWERS) {
                        TextView wrongAnswersErrorTextView = findViewById(R.id.wrongAnswersErrorTextView);
                        wrongAnswersErrorTextView.setText(getString(R.string.manyWrongAnswers));
                    }
                }
            }
        });
    }
}
