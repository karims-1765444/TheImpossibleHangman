package com.example.theimpossiblehangman.Player;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;

import com.example.theimpossiblehangman.Manager.HangmanManager;
import com.example.theimpossiblehangman.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayHangman extends AppCompatActivity {

    public List<String> setupDictionary() {
        // open the dictionary file and read dictionary into an TreeSet
        List<String> dictionary = new ArrayList<>();
        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(getAssets().open("dictionary.txt")));
            String word = bReader.readLine();
            while (word != null) {
                dictionary.add(word);
                word = bReader.readLine();
            }
            bReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> dictionary2 = Collections.unmodifiableList(dictionary);
        return dictionary2;
    }

    public String showResults(HangmanManager hangman) {
        String finalMessage;
        if (hangman.guessesLeft() > 0) {
            finalMessage = (getString(R.string.winner));
        } else {
            finalMessage = (getString(R.string.loser));
        }
        return finalMessage;
    }

    public void playSound(String result){
        if (result.startsWith("\nYes")){
            final MediaPlayer correctSoundFX = MediaPlayer.create(this, R.raw.correctsound);
            correctSoundFX.start();
        }
        else {
            final MediaPlayer wrongSoundFX = MediaPlayer.create(this, R.raw.wrongsound);
            wrongSoundFX.start();
        }
    }
}
