package com.example.marvel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.marvel.json.MarvelResultsModel;

public class SetCharacter extends AppCompatActivity {

    TextView nickname, description, charname, textbox, comicstxt, seriestxt, storiestxt, eventstxt;
    ImageView favouriteicon, backbtn, charimage;
    boolean selection;
    int selectedtab;
    MarvelResultsModel character;
    ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setcharacter);
        selection=false;
        selectedtab=3;
        nickname = findViewById(R.id.PlayerName);
        charname = findViewById(R.id.charactername);
        backbtn = findViewById(R.id.backbtn);
        description = findViewById(R.id.description);
        charimage = findViewById(R.id.charimage);
        favouriteicon = findViewById(R.id.favouriteicon);
        textbox = findViewById(R.id.textbox);
        comicstxt = findViewById(R.id.comics);
        seriestxt = findViewById(R.id.series);
        storiestxt = findViewById(R.id.stories);
        eventstxt = findViewById(R.id.events);
        initialization();
        Intent intent = getIntent();
        nickname.setText(intent.getStringExtra("nickname"));
        character = intent.getParcelableExtra("characterdata");
        charname.setText(character.getName());
        String fullpath;
        fullpath= character.getThumbnail().getPath() + "." + character.getThumbnail().getExtension();
        Glide.with(this).load(fullpath).into(charimage);
        if(!character.getDescription().equals("")) {
            description.setText(character.getDescription());
        } else{
            description.setText("\nNo Description Available.\n");
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
        comicstxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabssetup(0);
            }
        });
        seriestxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabssetup(1);
            }
        });
        storiestxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabssetup(2);
            }
        });
        eventstxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabssetup(3);
            }
        });
        tabssetup(0);
    }

    public void initialization(){
        if (selection){
            favouriteicon.setImageResource(R.drawable.favouriteicon);
        }
        else{
            favouriteicon.setImageResource(R.drawable.favouriteiconempty);
        }
    }
    public void tabssetup(int tab){
        if(tab==selectedtab){

        }
        else{
            String textinbox="";
            switch(selectedtab){
                case(0):
                    comicstxt.setBackgroundResource(0);
                    break;
                case(1):
                    seriestxt.setBackgroundResource(0);
                    break;
                case(2):
                    storiestxt.setBackgroundResource(0);
                    break;
                case(3):
                    eventstxt.setBackgroundResource(0);
                    break;
            }
            switch(tab){
                case(0):
                    comicstxt.setBackgroundResource(R.drawable.selectedcategory);
                    if(character.getComics().getItems().size()>0) {
                        for (int i = 0; i < character.getComics().getItems().size(); i++) {
                            textinbox = textinbox + character.getComics().getItems().get(i).getName() + "\n\n";
                        }
                        textbox.setText(textinbox);
                    }
                    else{
                        textbox.setText("No Comics found.");
                    }
                    break;
                case(1):
                    seriestxt.setBackgroundResource(R.drawable.selectedcategory);
                    if(character.getSeries().getItems().size()>0) {
                        for (int i = 0; i < character.getSeries().getItems().size(); i++) {
                            textinbox =textinbox + character.getSeries().getItems().get(i).getName() + "\n\n";
                        }
                        textbox.setText(textinbox);
                    }
                    else{
                        textbox.setText("No Series found.");
                    }
                    break;
                case(2):
                    storiestxt.setBackgroundResource(R.drawable.selectedcategory);
                    if(character.getStories().getItems().size()>0) {
                        for (int i = 0; i < character.getStories().getItems().size(); i++) {
                            textinbox = textinbox + character.getStories().getItems().get(i).getName() + "\n\n";
                        }
                        textbox.setText(textinbox);
                    }
                    else{
                        textbox.setText("No Stories found.");
                    }
                    break;
                case(3):
                    eventstxt.setBackgroundResource(R.drawable.selectedcategory);
                    if(character.getEvents().getItems().size()>0) {
                        for (int i = 0; i < character.getEvents().getItems().size(); i++) {
                            textinbox = textinbox + character.getEvents().getItems().get(i).getName() + "\n\n";
                        }
                        textbox.setText(textinbox);
                    }
                    else{
                        textbox.setText("No Events found.");
                    }
                    break;
            }
            selectedtab=tab;
        }
    }
}
