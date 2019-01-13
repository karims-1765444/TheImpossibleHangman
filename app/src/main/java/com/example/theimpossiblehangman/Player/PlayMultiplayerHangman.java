package com.example.theimpossiblehangman.Player;

import android.os.Bundle;

import com.example.theimpossiblehangman.Manager.ClassicHangmanManager;

public class PlayMultiplayerHangman extends PlayClassicHangman {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setupGame();
    }

    public void setupGame() {
        // open the dictionary file and read dictionary into an TreeSet
        String gameWord = getIntent().getExtras().getString("gameWord");
        ClassicHangmanManager hangman = new ClassicHangmanManager(gameWord);
        playGame(hangman);
    }
}
