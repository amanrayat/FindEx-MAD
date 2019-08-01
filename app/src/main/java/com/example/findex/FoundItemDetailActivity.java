package com.example.findex;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


import utils.FoundItem;

public class FoundItemDetailActivity extends AppCompatActivity {

    private Button locationButton;
    private TextView charName;
    private TextView description;
    private TextView dateFound;
    private TextView reportedByText;
    private ImageView imageView;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_found_item_detail);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        charName = findViewById(R.id.FoundTitleText);
        description = findViewById(R.id.description);
        imageView = findViewById(R.id.imageView1);
        dateFound = findViewById(R.id.dateFound);
        reportedByText = findViewById(R.id.reportedByText);

        String charVal = getIntent().getStringExtra("title");
        charName.setText(charVal);
        Query query = mDatabase.orderByChild("title").equalTo(charVal);
        query.addChildEventListener(childEventListener);
        locationButton = findViewById(R.id.claimButton);
        deleteButton = findViewById(R.id.deleteButton);

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLocation();
            }
        });


    }


    ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull final DataSnapshot dataSnapshot, @Nullable String s) {
            final FoundItem item = dataSnapshot.getValue(FoundItem.class);
            charName.setText(item.getTitle().toUpperCase());
            description.setText(item.getDescription());
            Picasso.get().load(item.getimageUrl()).into(imageView);
            dateFound.setText(item.getDate().toString());
            reportedByText.setText("Reported By: " + item.getReportedByEmail());

            if(!(mAuth.getCurrentUser().getEmail().equals(item.getReportedByEmail()))){
                deleteButton.setVisibility(View.GONE);
            }
//
//            deleteButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    DatabaseReference delete = FirebaseDatabase.getInstance().getReference("findex-1562652361242").child(item.getId().toString());
//                    delete.removeValue();
//                }
//            });
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    public void openLocation(){
        Intent intent = new Intent(this, MapFragment.class);
        startActivity(intent);
    }
}
