package com.example.theimpossiblehangman;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.theimpossiblehangman.External.RequestManager;
import com.example.theimpossiblehangman.Player.PlayClassicHangman;
import com.example.theimpossiblehangman.Player.SetupEvilHangman;
import com.example.theimpossiblehangman.Player.SetupMultiplayerHangman;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RequestManager.instantiateRequestManager(getApplicationContext());
        setContentView(R.layout.main_activity);
        MediaPlayer backgroundMusic = MediaPlayer.create(this,R.raw.backgroundmusic);
        backgroundMusic.start();
        backgroundMusic.setLooping(true);
        Button classicBtn = findViewById(R.id.classicBtn);
        classicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playClassicGame =  new Intent(getApplicationContext(), PlayClassicHangman.class);
                startActivity(playClassicGame);
            }
        });
        Button multiplayerBtn = findViewById(R.id.multiplayerBtn);
        multiplayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playMultiplayerGame =  new Intent(getApplicationContext(), SetupMultiplayerHangman.class);
                startActivity(playMultiplayerGame);
            }
        });
        Button evilBtn = findViewById(R.id.evilBtn);
        evilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playEvilGame = new Intent(getApplicationContext(), SetupEvilHangman.class);
                startActivity(playEvilGame);
            }
        });
    }
}
