package com.example.theimpossiblehangman.Player;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.theimpossiblehangman.External.DatamuseClient;
import com.example.theimpossiblehangman.Manager.ClassicHangmanManager;
import com.example.theimpossiblehangman.R;
import com.google.common.base.Function;

import java.util.List;

public class PlayClassicHangman extends PlayHangman {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        setupGame();
    }

    private void setupGame() {
        // open the dictionary file and read dictionary into an TreeSet
        List<String> dictionary = setupDictionary();
        int randomWordIndex = (int) (Math.random()*dictionary.size()+1);
        ClassicHangmanManager hangman = new ClassicHangmanManager(dictionary.get(randomWordIndex));
        playGame(hangman);
    }

    public void playGame(final ClassicHangmanManager hangman) {
        final TextView updateTextView = findViewById(R.id.updateTextView);
        final EditText guessEditText = findViewById(R.id.guessEditText);
        final Button guessBtn = findViewById(R.id.guessBtn);
        final Button hintBtn = findViewById(R.id.hintBtn);
        final TextView hintTextView = findViewById(R.id.hintTextView);
        hintBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hintBtn.setVisibility(View.GONE);
                hintTextView.setVisibility(View.VISIBLE);

                DatamuseClient.getHint(hangman.getGameWord(), new Function<String, Void>() {
                    @Override
                    public Void apply(String hint) {
                        hintTextView.setText(hint);
                        return null;
                    }
                });
            }
        });
        updateTextView.setText(hangman.displayGameState());
        guessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessEditText.setHint("Enter a word...");
                String guess = guessEditText.getText().toString().toLowerCase();
                if (guess.length() < 1 ) {
                    guessEditText.setHint("Please enter a word.");
                    return;
                }
                guessEditText.getText().clear();
                String guessResult = hangman.record((guess).charAt(0));
                playSound(guessResult);
                updateTextView.setText(guessResult+"\n"+hangman.displayGameState());
                //if user has one more guess, adds the option of a hint (only when hint is not already displayed)
                if (hangman.guessesLeft() == 1 && !(hintTextView.getVisibility()==View.VISIBLE)) {
                    hintBtn.setVisibility(View.VISIBLE);
                } else
                //checks if game is over
                if ((hangman.guessesLeft() == 0 || (!(hangman.pattern().contains("-"))))) {
                    guessBtn.setVisibility(View.GONE);
                    guessEditText.setVisibility(View.GONE);
                    updateTextView.setText(showResults(hangman));
                    guessBtn.setVisibility(View.GONE);
                    guessEditText.setVisibility(View.GONE);
                    hintBtn.setVisibility(View.GONE);
                    hintTextView.setVisibility(View.GONE);
                }
            }
        });
    }

    private String showResults (ClassicHangmanManager hangman) {
        String answer = hangman.getGameWord();
        String finalMessage = "answer: " + answer + "\n";
        finalMessage+= super.showResults(hangman);
        return finalMessage;
    }
}
