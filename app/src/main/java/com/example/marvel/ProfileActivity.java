package com.example.marvel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    ImageView backbtn;
    TextView nickname;
    EditText firstname,lastname,emailsu,passwordsu;
    Button savebtn;
    FirebaseDatabase firebase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        backbtn = findViewById(R.id.backbtn);
        nickname = findViewById(R.id.PlayerName);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        emailsu = findViewById(R.id.emailsu);
        passwordsu = findViewById(R.id.passwordsu);
        savebtn = findViewById(R.id.buttonsave);
        Intent intent = getIntent();
        nickname.setText(intent.getStringExtra("nickname"));
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedata();
            }
        });
        getuserdata();
    }

    public void getuserdata() {
        firebase = FirebaseDatabase.getInstance();
        reference = firebase.getReference().child("users").child(nickname.getText().toString());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                List<String> data = new ArrayList<>();
                for (DataSnapshot x : dataSnapshot.getChildren()) {
                    data.add(String.valueOf(x.getValue()));
                }
                String email = data.get(0);
                emailsu.setText(data.get(0));
                firstname.setText(data.get(1));
                lastname.setText(data.get(2));
                passwordsu.setText(data.get(4));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void savedata() {
        String email,pass,first,last;
        email=emailsu.getText().toString();
        first=firstname.getText().toString();
        last=lastname.getText().toString();
        pass=passwordsu.getText().toString();
        if(emailsu.getText().length()>0&&firstname.getText().length()>0&&lastname.getText().length()>0&passwordsu.getText().length()>0)
        {
            firebase = FirebaseDatabase.getInstance();
            reference = firebase.getReference();
            reference.child("users").child(nickname.getText().toString()).child("email").setValue(email);
            reference.child("users").child(nickname.getText().toString()).child("firstname").setValue(first);
            reference.child("users").child(nickname.getText().toString()).child("lastname").setValue(last);
            reference.child("users").child(nickname.getText().toString()).child("password").setValue(pass);
        }
    }
}
