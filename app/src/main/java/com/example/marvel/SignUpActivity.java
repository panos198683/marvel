package com.example.marvel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;

//import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity {
    //private FirebaseAuth mAuth;
    //DatabaseReference myRef;
    DatabaseData mDatabaseHelper;

    User user= new User();
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
//                    mAuth.createUserWithEmailAndPassword(emailsu.getText().toString(),
//                            passwordsu.getText().toString()).addOnCompleteListener(this,
//                            new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()){
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            myRef= FirebaseDatabase.getInstance().getReference().child("User");
//                            userdetails(firstname,lastname,nickname);
//                            addUserDetails("marvel",user);
//                        } else {
//                        }
//                    }
//                });
        }
    }

    public void AddData(String firstname,String lastname,String nickname,String email,String password){
        boolean insertData = mDatabaseHelper.addData(firstname,lastname,nickname,email,password);
        if(!insertData){
            new IllegalArgumentException().printStackTrace();
        }
    }

    private void userdetails(EditText firstname, EditText lastname, EditText nickname){
        user.setFirstname(firstname.getText().toString());
        user.setLastname(lastname.getText().toString());
        user.setNickname(nickname.getText().toString());
        //myRef.push().setValue(user);
        Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();

    }

//    private void addUserDetails(String displayName, FirebaseUser user){
//        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
//                .setDisplayName(displayName)
//                .build();
//        user.updateProfile(profileChangeRequest)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//    }

}
