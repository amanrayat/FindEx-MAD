package com.example.findex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FoundItemDetailActivity extends AppCompatActivity {

    Button locationButton;
    TextView charName;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_item_detail);
        charName = findViewById(R.id.FoundTitleText);
        imageView = findViewById(R.id.imageView1);

        String charVal = getIntent().getStringExtra("title");
        charName.setText(charVal);





        /*
        Adding dummy images and hardcoding images for demo.
         */
        if(charVal.equals("Iphone 6")) {
            imageView.setImageResource(R.drawable.iphone);
        }
        else if (charVal.equals("Water Bottle")) {
            imageView.setImageResource(R.drawable.bottle);
        }
        else if (charVal.equals("Mouse")) {
            imageView.setImageResource(R.drawable.mouse);
        }
        else if (charVal.equals("Keyboard")) {
            imageView.setImageResource(R.drawable.keyboard);
        }
        else if (charVal.equals("Umbrella")) {
            imageView.setImageResource(R.drawable.umbrella);
        }
        else if (charVal.equals("Shoes")) {
            imageView.setImageResource(R.drawable.shoes);
        }
        else if (charVal.equals("I Watch")) {
            imageView.setImageResource(R.drawable.watch);
        }
        else if (charVal.equals("MacBookPro")) {
            imageView.setImageResource(R.drawable.macbooktestimage);
        }
        else {
            imageView.setImageResource(R.drawable.charger);
        }

        locationButton = findViewById(R.id.claimButton);

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLocation();
            }
        });
    }

    public void openLocation(){
        Intent intent = new Intent(this, MapFragment.class);
        startActivity(intent);
    }
}
