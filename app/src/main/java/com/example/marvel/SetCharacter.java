package com.example.marvel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.marvel.json.JsonComicModel;
import com.example.marvel.json.JsonEventsModel;
import com.example.marvel.json.JsonMarvelModel;
import com.example.marvel.json.JsonSeriesModel;
import com.example.marvel.json.JsonStoriesModel;
import com.example.marvel.json.MarvelResultsModel;

import java.util.ArrayList;

public class SetCharacter extends AppCompatActivity {

    TextView nickname;
    TextView charname;
    String position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setcharacter);
        nickname= findViewById(R.id.PlayerName);
        charname= findViewById(R.id.charactername);
        Intent intent = getIntent();
        nickname.setText(intent.getStringExtra("nickname"));
        MarvelResultsModel character = intent.getParcelableExtra("characterdata");
        JsonComicModel comics = intent.getParcelableExtra("charactercomics");
        JsonSeriesModel series = intent.getParcelableExtra("characterseries");
        JsonStoriesModel stories = intent.getParcelableExtra("characterstories");
        JsonEventsModel events = intent.getParcelableExtra("characterevents");
        charname.setText(character.getName());
    }
}
