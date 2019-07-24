package com.example.findex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

// AKA Login page
public class MainActivity extends AppCompatActivity {

    private Button login;
    private Button register;
    private EditText userNameText , passwordText;
    private FirebaseAuth authReference;
    private String usernameData, passwordData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameText = findViewById(R.id.loginEmail);
        passwordText = findViewById(R.id.loginPassword);

        authReference = FirebaseAuth.getInstance();

        // When "login" button is clicked, move to FoundList activity
        login = findViewById(R.id.button);

        /*
        Click listener for the login Button.
         */
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameData = userNameText.getText().toString();
                passwordData = passwordText.getText().toString();
                authenticateUserLogin();
            }
        });

        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegistration();
            }
        });
    }

    /**
     * This function is used to authenticate the user and change the intent.
     */
    private void authenticateUserLogin(){
        authReference.signInWithEmailAndPassword(usernameData,passwordData).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    openFoundList();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Login Failed - Use correct Username / Password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // Function used in onCreate to open FoundList activity
    public void openFoundList(){
        Intent intent = new Intent(this, FoundItemList.class);
        startActivity(intent);
    }

    public void openRegistration(){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
}
