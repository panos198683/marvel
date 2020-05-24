package com.example.marvel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    DatabaseReference myRef;

    User user= new User();
EditText firstname,lastname,nickname,emailsu,passwordsu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();


    }
    public void gotosignin(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void signupclicked(View view){
        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        nickname=findViewById(R.id.nickname);

        if (!firstname.getText().toString().equals("") || !lastname.getText().toString().equals("") || !nickname.getText().toString().equals("") || !emailsu.getText().toString().equals("") || !passwordsu.getText().toString().equals("")){
                    mAuth.createUserWithEmailAndPassword(emailsu.getText().toString(),
                passwordsu.getText().toString()).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            myRef= FirebaseDatabase.getInstance().getReference().child("User");
                            userdetails(firstname,lastname,nickname);
                            addUserDetails("marvel",user);
                        } else {
                        }
                    }
                });
        }
    }

    private void userdetails(EditText firstname, EditText lastname, EditText nickname){
        user.setFirstname(firstname.getText().toString());
        user.setLastname(lastname.getText().toString());
        user.setNickname(nickname.getText().toString());
        myRef.push().setValue(user);
        Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();

    }

    private void addUserDetails(String displayName, FirebaseUser user){
        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(displayName)
                .build();
        user.updateProfile(profileChangeRequest)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}
