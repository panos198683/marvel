package com.example.marvel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class SetCharacter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setcharacter);
        getIntent().getStringExtra("nickname");
        getIntent().getStringExtra("position");
        Bundle extra = getIntent().getBundleExtra("extra");
        ArrayList<ListItem> charsList = (ArrayList<ListItem>) extra.getSerializable("charslist");
    }
}
