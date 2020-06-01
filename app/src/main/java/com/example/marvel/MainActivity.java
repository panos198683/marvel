package com.example.marvel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class MainActivity extends AppCompatActivity {
    //private FirebaseAuth mAuth;
    EditText editText1,editText2;
    TextView ErrorAnnouncer;
    DatabaseData mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mAuth = FirebaseAuth.getInstance();
        editText1=findViewById(R.id.layoutEmail);
        editText2=findViewById(R.id.layoutPassword);
        ErrorAnnouncer = findViewById(R.id.layoutannouncer);

        mDatabaseHelper= new DatabaseData(this);
//        FirebaseUser currentUser =mAuth.getCurrentUser();
//        if(currentUser==null)
//            ErrorAnnouncer.setText("NO USER YET");
//        ErrorAnnouncer.setTextColor(Color.parseColor("#ffffff"));

        Button btn = findViewById(R.id.buttonlogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinuser(v);
            }
        });

        
    }
    public void signupuser(View view){

        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }



    public void signinuser(View view){
//        mAuth.signInWithEmailAndPassword(editText1.getText().toString(),
//                editText2.getText().toString())
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            textView.setText(user.getUid());
//                            //mAuth.signOut();
//                        }
//                        else{
//                            textView.setText("failed to log in.");
//                        }
//                    }
//                });
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

    public void Loginchecker(String email,String password){
        Cursor data = mDatabaseHelper.getData(email,password);
        String nickname="";
        boolean founder=false;
        if(data.moveToFirst()) {
            founder=true;
            nickname=data.getString(0);
        }
        if (founder==true){
            Intent intent = new Intent(this, CharactersActivity.class);
            intent.putExtra("nickname",nickname);
            startActivity(intent);
        }
        else{
            ErrorAnnouncer.setText("Wrong Username or Password");
        }


    }


    public void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public void cleardtbs(View view){
        mDatabaseHelper.cleartable();
    }



}
