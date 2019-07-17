package com.example.findex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

// AKA Login page
public class MainActivity extends AppCompatActivity {

    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // When "login" button is clicked, move to FoundList activity
        login = findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFoundList();
            }
        });
    }

    // Function used in onCreate to open FoundList activity
    public void openFoundList(){
        Intent intent = new Intent(this, FoundItemList.class);
        startActivity(intent);
    }
}
