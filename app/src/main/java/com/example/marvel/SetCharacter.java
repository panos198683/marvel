package com.example.marvel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.marvel.json.MarvelResultsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SetCharacter extends AppCompatActivity {

    TextView nickname, description, charname, textbox, comicstxt, seriestxt, storiestxt, eventstxt;
    ImageView favouriteicon, backbtn, charimage,sharebtn;
    boolean selection;
    int selectedtab;
    MarvelResultsModel character;
    FirebaseDatabase firebase;
    DatabaseReference reference;
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
        sharebtn = findViewById(R.id.sharebtn);
        description = findViewById(R.id.description);
        charimage = findViewById(R.id.charimage);
        favouriteicon = findViewById(R.id.favouriteicon);
        textbox = findViewById(R.id.textbox);
        comicstxt = findViewById(R.id.comics);
        seriestxt = findViewById(R.id.series);
        storiestxt = findViewById(R.id.stories);
        eventstxt = findViewById(R.id.events);
        textbox.setMovementMethod(new ScrollingMovementMethod());
        initialization();
        Intent intent = getIntent();
        nickname.setText(intent.getStringExtra("nickname"));
        character = intent.getParcelableExtra("characterdata");
        findfavourite();
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
                    removefavourite();
                }else
                {
                    selection=true;
                    favouriteicon.setImageResource(R.drawable.favouriteicon);
                    addfavourite();
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
        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareSub = character.getName();
                String shareBody = character.getDescription();
                String ShareImageLink = character.getThumbnail().getExtension()+"."+character.getThumbnail().getPath();
                //String htmlimage= "<img src=\""+ShareImageLink+"\"alt=\"Marvel Hero\">";
                //shareBody=htmlimage + " "+ shareBody;
                myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(myIntent,"Share using"));
            }
        });
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
                        textbox.setText(textinbox.substring(0,textinbox.length()-2));
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
                        textbox.setText(textinbox.substring(0,textinbox.length()-2));
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
                        textbox.setText(textinbox.substring(0,textinbox.length()-2));
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
                        textbox.setText(textinbox.substring(0,textinbox.length()-2));
                    }
                    else{
                        textbox.setText("No Events found.");
                    }
                    break;
            }
            selectedtab=tab;
        }
    }
    public void addfavourite(){
        firebase = FirebaseDatabase.getInstance();
        reference = firebase.getReference();
        String charid=String.valueOf(character.getId());
        reference.child("favourites").child(nickname.getText().toString()).child(charid).setValue(charid);
        String message = "You just added " + character.getName()+  " to your favourite marvel characters!";


        NotificationCompat.Builder builder = new NotificationCompat.Builder(SetCharacter.this)
                .setSmallIcon(R.drawable.marvelpedialogolow).setContentTitle("MARVEL-PEDIA")
                .setContentText(message)
                .setAutoCancel(true);

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(SetCharacter.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());
    }
    public void removefavourite(){
        firebase = FirebaseDatabase.getInstance();
        reference = firebase.getReference();
        String charid=String.valueOf(character.getId());
        reference.child("favourites").child(nickname.getText().toString()).child(charid).removeValue();
    }

    public void findfavourite(){
        String fullid=String.valueOf(character.getId());
        firebase = FirebaseDatabase.getInstance();
        reference=firebase.getReference().child("favourites").child(nickname.getText().toString()).child(fullid);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    String isfav = dataSnapshot.getValue(String.class);
                    if(isfav.equals(String.valueOf(character.getId()))){
                        favouriteicon.setImageResource(R.drawable.favouriteicon);
                        selection=true;
                    }
                    else{
                        favouriteicon.setImageResource(R.drawable.favouriteiconempty);
                        selection=false;
                    }
                }catch(Exception e){
                    favouriteicon.setImageResource(R.drawable.favouriteiconempty);
                    selection=false;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                favouriteicon.setImageResource(R.drawable.favouriteiconempty);
                selection=false;

            }
        });
    }


}
