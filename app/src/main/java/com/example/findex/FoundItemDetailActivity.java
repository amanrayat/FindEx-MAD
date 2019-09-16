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
import com.google.android.gms.tasks.OnSuccessListener;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    private String itemKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the firebase instance.
        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_found_item_detail);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Get the refernce of the elements.
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
    }
    
    /**
     * childeventlistener is the listener which responds to the 
     * 
     */
    ChildEventListener childEventListener = new ChildEventListener() {

        /**
         * This function is invoked when a new child is added to the list.
         * @param dataSnapshot
         * @param s
         */
        @Override
        public void onChildAdded(@NonNull final DataSnapshot dataSnapshot, @Nullable String s) {
            itemKey = dataSnapshot.getKey();
            final FoundItem item = dataSnapshot.getValue(FoundItem.class);
            charName.setText(item.getTitle().toUpperCase());
            description.setText(item.getDescription());
            Picasso.get().load(item.getimageUrl()).into(imageView);
            DateFormat df2 = new SimpleDateFormat("EEE, MMM d, ''yy");

            dateFound.setText(df2.format(item.getDate()));
            reportedByText.setText("Reported By: " + item.getReportedByEmail());

            /**
             * Show the delete button only if the item was reported by the current user.
             */
            if(!(mAuth.getCurrentUser().getEmail().equals(item.getReportedByEmail()))){
                deleteButton.setVisibility(View.GONE);
            }

            /**
             * OnClick listener to delete a post.
             */
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatabaseReference delete = FirebaseDatabase.getInstance().getReference().child(itemKey);
                    delete.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent intent = new Intent(FoundItemDetailActivity.this, FoundItemList.class);
                            startActivity(intent);
                        }
                    });
                }
            });

            /**
             * OnClick Listener to open the maps.
             */
            locationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openLocation(item.getLocation().toString());
                }
            });
        }

        /**
         * Unimplemented childlistener methods.
         * @param dataSnapshot
         * @param s
         */
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

    /**
     * This function is used to open the Maps.
     * The function sets the intent and invokes the activity.
     * @param location
     */

    public void openLocation(String location){
        Intent intent = new Intent(this, MapFragment.class);
        intent.putExtra("location" , location);
        startActivity(intent);
    }
}
