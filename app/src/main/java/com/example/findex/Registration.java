package com.example.findex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {

    private Button registration;
    private EditText userNameText , passwordText, confirmPasswordText;
    private FirebaseAuth authReference;
    private String usernameData, passwordData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    private void registerUser() {
        authReference.createUserWithEmailAndPassword(usernameData, passwordData).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Registration Success", Toast.LENGTH_LONG).show();
                    openFoundList();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Registration is Unsuccessful", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void openFoundList(){
        Intent intent = new Intent(this, FoundItemList.class);
        startActivity(intent);
    }

}
