package com.example.findex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends AppCompatActivity {

    private Button registration;
    private EditText userNameText , passwordText, confirmPasswordText;
    private FirebaseAuth authReference;
    private String usernameData, passwordData;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        userNameText = findViewById(R.id.registerEmail);
        passwordText = findViewById(R.id.registerPassword);
        confirmPasswordText = findViewById(R.id.registerPassword);

        authReference = FirebaseAuth.getInstance();
        registration = findViewById(R.id.buttonRegistration);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameData = userNameText.getText().toString();
                passwordData = passwordText.getText().toString();
                registerUser();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            openFoundList(currentUser);
            Log.d("current user", currentUser.getEmail());
        }
    }

    private void registerUser() {
        authReference.createUserWithEmailAndPassword(usernameData, passwordData).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    Toast.makeText(getApplicationContext(),"Registration Success for: " +  mAuth.getCurrentUser(), Toast.LENGTH_LONG).show();
                    openFoundList(currentUser);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Registration is Unsuccessful", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void openFoundList(FirebaseUser currentUser){
        Intent intent = new Intent(this, FoundItemList.class);
        intent.putExtra("useremail" , currentUser.getEmail());
        startActivity(intent);
    }

}
