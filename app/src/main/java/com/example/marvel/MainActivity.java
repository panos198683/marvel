package com.example.marvel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText editText1,editText2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        editText1=findViewById(R.id.editText1);
        editText2=findViewById(R.id.editText2);
        textView = findViewById(R.id.textView);
        FirebaseUser currentUser =mAuth.getCurrentUser();
        if(currentUser==null)
            textView.setText("NO USER YET");
            textView.setTextColor(Color.parseColor("#ffffff"));
        
        
    }
    public void signupuser(View view){
        mAuth.createUserWithEmailAndPassword(editText1.getText().toString(),
                editText2.getText().toString()).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            addUserDetails("marvel",user);
                            textView.setText(user.getUid());
                        } else {
                            textView.setText(task.getException().getMessage());
                        }
                    }
                });
    }
    private void addUserDetails(String displayName, FirebaseUser user){
        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(displayName)
                .build();
        user.updateProfile(profileChangeRequest)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                            Toast.makeText(getApplicationContext(),"Success!",Toast.LENGTH_LONG).show();
                    }
                });
    }
    public void signinuser(View view){
        mAuth.signInWithEmailAndPassword(editText1.getText().toString(),
                editText2.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            textView.setText(user.getUid());
                            //mAuth.signOut();
                        }
                        else{
                            textView.setText("failed to log in.");
                        }
                    }
                });
    }
    public void forgotpassword(View view){
        textView.setTextColor(Color.parseColor("#F0131E"));
        textView.setText("Forgot Password Screen");


    }

    public void passwordvisible(View view){

    }




}