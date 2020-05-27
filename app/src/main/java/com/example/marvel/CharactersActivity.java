package com.example.marvel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CharactersActivity extends AppCompatActivity {

    TextView playername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        playername=findViewById(R.id.PlayerName);
        String nickname=getIntent().getStringExtra("nickname");
        playername.setText(nickname.toString());
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    

}
