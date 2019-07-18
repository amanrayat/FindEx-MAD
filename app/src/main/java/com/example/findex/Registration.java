package com.example.findex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Registration extends AppCompatActivity {

    Button registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);



        registration = findViewById(R.id.buttonRegistration);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFoundList();
            }
        });
    }

    public void openFoundList(){
        Intent intent = new Intent(this, FoundItemList.class);
        startActivity(intent);
    }

}
