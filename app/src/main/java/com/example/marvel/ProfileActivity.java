package com.example.marvel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    ImageView backbtn;
    TextView nickname;
    FirebaseDatabase firebase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        backbtn = findViewById(R.id.backbtn);
        nickname = findViewById(R.id.PlayerName);
        Intent intent = getIntent();
        nickname.setText(intent.getStringExtra("nickname"));
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void getuserdata(){
//        firebase = FirebaseDatabase.getInstance();
//        reference=firebase.getReference("users");
//        Query checkUser = reference.orderByChild("nickname").equalTo(nickname.getText().toString());
//
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists()){
//                    String passwordFromDB = dataSnapshot.child(nickname.getText().toString()).child("password").getValue(String.class);
//                    if(passwordFromDB.equals(password)){
////                        String nickname;
////                        nickname = dataSnapshot.child(nickname).child("nickname").getValue(String.class);
//                        nextpage(nickname);
//                    }
//                    else{
//                        ErrorAnnouncer.setText("Wrong Creditentials");
//                    }
//                }
//                else{
//                    ErrorAnnouncer.setText("Wrong Creditentials");
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }
}
