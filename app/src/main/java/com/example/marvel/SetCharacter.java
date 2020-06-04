package com.example.marvel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.marvel.json.JsonComicModel;
import com.example.marvel.json.JsonEventsModel;
import com.example.marvel.json.JsonMarvelModel;
import com.example.marvel.json.JsonSeriesModel;
import com.example.marvel.json.JsonStoriesModel;
import com.example.marvel.json.JsonthumbnailModel;
import com.example.marvel.json.MarvelResultsModel;

import java.util.ArrayList;

public class SetCharacter extends AppCompatActivity {

    TextView nickname, description, charname;
    ImageView favouriteicon, backbtn, charimage;
    boolean selection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setcharacter);
        selection=false;
        nickname = findViewById(R.id.PlayerName);
        charname = findViewById(R.id.charactername);
        backbtn = findViewById(R.id.backbtn);
        description = findViewById(R.id.description);
        charimage = findViewById(R.id.charimage);
        favouriteicon = findViewById(R.id.favouriteicon);
        if (selection){
            favouriteicon.setImageResource(R.drawable.favouriteicon);
        }
        else{
            favouriteicon.setImageResource(R.drawable.favouriteiconempty);
        }
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        favouriteicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selection){
                    selection=false;
                    favouriteicon.setImageResource(R.drawable.favouriteiconempty);
                }else
                {
                    selection=true;
                    favouriteicon.setImageResource(R.drawable.favouriteicon);
                }
            }
        });

        Intent intent = getIntent();
        nickname.setText(intent.getStringExtra("nickname"));
        MarvelResultsModel character = intent.getParcelableExtra("characterdata");
        JsonthumbnailModel path = intent.getParcelableExtra("characterthumbnail");
        JsonComicModel comics = intent.getParcelableExtra("charactercomics");
        JsonSeriesModel series = intent.getParcelableExtra("characterseries");
        JsonStoriesModel stories = intent.getParcelableExtra("characterstories");
        JsonEventsModel events = intent.getParcelableExtra("characterevents");
        charname.setText(character.getName());
        String fullpath;
        fullpath= path.getPath() + "." + path.getExtension();
        Glide.with(this).load(fullpath).into(charimage);
        if(!character.getDescription().equals("")) {
            description.setText(character.getDescription());
        } else{
            description.setText("\nNo Description Available.\n");
        }


    }
}
