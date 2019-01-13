package com.example.theimpossiblehangman.Player;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.theimpossiblehangman.Manager.EvilHangmanManager;
import com.example.theimpossiblehangman.R;

import java.util.List;

public class PlayEvilHangman extends PlayHangman {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        int guesses = getIntent().getExtras().getInt("guesses");
        int length = getIntent().getExtras().getInt("length");
        EvilHangmanManager hangman = setupGame(length, guesses);
        playGame(hangman);
    }

    private EvilHangmanManager setupGame(int length, int guesses) {
        // open the dictionary file and read dictionary into an TreeSet
        List<String> dictionary = setupDictionary();
        return new EvilHangmanManager(dictionary, length, guesses);
        }

    private String showResults (EvilHangmanManager hangman) {
        String answer = hangman.words().iterator().next();
        String finalMessage = "answer: " + answer + "\n";
        finalMessage+= super.showResults(hangman);
        return finalMessage;
    }

    public void playGame(final EvilHangmanManager hangman) {
        final TextView updateTextView = findViewById(R.id.updateTextView);
        final EditText guessEditText = findViewById(R.id.guessEditText);
        final Button guessBtn = findViewById(R.id.guessBtn);
        final LinearLayout mainLayout = findViewById(R.id.linearLayout);
        final InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        updateTextView.setText(hangman.displayGameState());
        guessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm.hideSoftInputFromWindow(mainLayout.getWindowToken(), 0);
                if (guessEditText.getText().toString().equals(getString(R.string.password)) && !EvilHangmanManager.CHEAT_CODE) {
                    EvilHangmanManager.CHEAT_CODE = true;
                    updateTextView.setText(getString(R.string.cheater)+"\n"+hangman.displayGameState());
                    return;
                }
                String guess = guessEditText.getText().toString().toLowerCase();
                if (guess.length() < 1 ) {
                    guessEditText.setHint("Please enter a word.");
                    return;
                }
                guessEditText.getText().clear();
                String guessResult = hangman.record(guess.charAt(0));
                playSound(guessResult);
                updateTextView.setText(guessResult+"\n"+hangman.displayGameState());
                //checks if game is over
                if ((hangman.guessesLeft() == 0 || !(hangman.pattern().contains("-")))) {
                    guessBtn.setVisibility(View.GONE);
                    guessEditText.setVisibility(View.GONE);
                    updateTextView.setText(showResults(hangman));
                    guessBtn.setVisibility(View.GONE);
                    guessEditText.setVisibility(View.GONE);
                }
            }
    });
    }
}