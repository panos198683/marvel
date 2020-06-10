package com.example.marvel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    FirebaseDatabase firebase;
    DatabaseReference reference;
    TextInputEditText editText1,editText2;
    TextView ErrorAnnouncer;
    DatabaseData mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1=findViewById(R.id.editText1);
        editText2=findViewById(R.id.editText2);

        ErrorAnnouncer = findViewById(R.id.layoutannouncer);

        mDatabaseHelper= new DatabaseData(this);
        Button btn = findViewById(R.id.buttonlogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinuser(v);
            }
        });
        TextView createaccount = findViewById(R.id.createanaccount);
        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupuser(v);
            }
        });


        
    }
    public void signupuser(View view){

        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }



    public void signinuser(View view){

        String email,password;
        email=editText1.getText().toString();
        password=editText2.getText().toString();
        if (email.length()!=0 && password.length()!=0) {
            ErrorAnnouncer.setText("");
            Loginchecker(email, password);
        }
        else{
            ErrorAnnouncer.setText("A field is not filled!");
        }
    }

    public void forgotpassword(View view){
        ErrorAnnouncer.setText("Forgot Password Screen");


    }

    public void Loginchecker(String email, final String password){
//        Cursor data = mDatabaseHelper.getData(email,password);
//        String nickname="";
//        boolean founder=false;
//        if(data.moveToFirst()) {
//            founder=true;
//            nickname=data.getString(0);
//        }
//        if (founder==true){
//            Intent intent = new Intent(this, CharactersActivity.class);
//            intent.putExtra("nickname",nickname);
//            startActivity(intent);
//        }
//        else{
//            ErrorAnnouncer.setText("Wrong Username or Password");
//        }
        firebase = FirebaseDatabase.getInstance();
        reference=firebase.getReference("users");
        Query checkUser = reference.orderByChild("email").equalTo(email);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String passwordFromDB = dataSnapshot.child("email").child("password").getValue(String.class);
                    if(passwordFromDB.equals(password)){
                        String nickname;
                        nickname = dataSnapshot.child("email").child("nickname").getValue(String.class);
                        nextpage(nickname);
                    }
                    else{
                        ErrorAnnouncer.setText("Wrong Creditentials");
                    }
                }
                else{
                    ErrorAnnouncer.setText("Wrong Creditentials");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void nextpage(String nickname){
        Intent intent = new Intent(this, CharactersActivity.class);
        intent.putExtra("nickname",nickname);
        startActivity(intent);
    }


}
