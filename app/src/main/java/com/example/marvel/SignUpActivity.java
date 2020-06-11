package com.example.marvel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity {
    DatabaseData mDatabaseHelper;
    FirebaseDatabase firebase;
    DatabaseReference reference;

    User user;
EditText firstname,lastname,nickname,emailsu,passwordsu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //mAuth = FirebaseAuth.getInstance();
        mDatabaseHelper= new DatabaseData(this);

        Button button = findViewById(R.id.signup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupclicked(v);
            }
        });

        TextView backtologin = findViewById(R.id.backsignin);
        backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotosignin(v);
            }
        });


    }
    public void gotosignin(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void signupclicked(View view){
        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        nickname=findViewById(R.id.nickname);
        emailsu=findViewById(R.id.emailsu);
        passwordsu=findViewById(R.id.passwordsu);

        if (firstname.length()!=0 && lastname.length()!=0 && nickname.length()!=0 && emailsu.length()!=0 && passwordsu.length()!=0){


            AddData(firstname.getText().toString(),lastname.getText().toString(),nickname.getText().toString(),emailsu.getText().toString(),passwordsu.getText().toString());



            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void AddData(String firstname,String lastname,String nickname,String email,String password){
//        boolean insertData = mDatabaseHelper.addData(firstname,lastname,nickname,email,password);
//        if(!insertData){
//            new IllegalArgumentException().printStackTrace();
//        }
        user = new User(firstname,lastname,email,password,nickname);
        firebase = FirebaseDatabase.getInstance();
        reference = firebase.getReference();
        reference.child("users").child(user.getNickname()).setValue(user);
    }

    private void userdetails(EditText firstname, EditText lastname, EditText nickname){
    }

}
