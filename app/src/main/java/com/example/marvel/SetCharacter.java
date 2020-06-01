package com.example.marvel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SetCharacter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setcharacter);
        getIntent().getStringExtra("nickname");
        getIntent().getStringExtra("position");
        getIntent().getStringExtra("charlist");
    }
}
