package com.example.theimpossiblehangman.Player;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.theimpossiblehangman.R;

public class SetupMultiplayerHangman extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_multiplayer_hangman);

        Button startGame = findViewById(R.id.startGameBtn);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText wordLengthEditText = findViewById(R.id.gameWordEditText);
                String gameWord = wordLengthEditText.getText().toString();
                if (gameWord.length()<1){
                    wordLengthEditText.setHint(getString(R.string.emptyInput));
                    return;
                }
                Intent playGame = new Intent(getApplicationContext(), PlayMultiplayerHangman.class);
                playGame.putExtra("gameWord", gameWord);
                startActivity(playGame);
            }
        });
    }
}
