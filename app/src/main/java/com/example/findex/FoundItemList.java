package com.example.findex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventListener;

import utils.CustomFoundListAdapter;
import utils.FoundItem;

public class FoundItemList extends AppCompatActivity {

    FloatingActionButton itemEntry;
    FloatingActionButton logout;
    ListView foundListView;
    private DatabaseReference mDatabase;
    ArrayList<FoundItem> foundItems = new ArrayList<FoundItem>();
    String TAG = "debug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(childEventListener);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_item_list);



        //List View
        foundListView = findViewById(R.id.myListView);
        CustomFoundListAdapter myAdapter = new CustomFoundListAdapter(this, foundItems);
        foundListView.setAdapter(myAdapter);

        // Will add item description page based on item id.
        foundListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(FoundItemList.this, FoundItemDetailActivity.class);
                intent.putExtra("title", foundItems.get(i).getTitle());
                startActivity(intent);
            }
        });

        itemEntry = findViewById(R.id.addButton);
        itemEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openItemEntry();
            }
        });


        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(FoundItemList.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    ChildEventListener childEventListener = new ChildEventListener() {

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
            Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
            FoundItem item = dataSnapshot.getValue(FoundItem.class);
            foundItems.add(item);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            Log.d("Child removed", dataSnapshot.getKey());
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w(TAG, "postComments:onCancelled", databaseError.toException());
            Toast.makeText(getApplicationContext(),"Registration is Unsuccessful", Toast.LENGTH_LONG).show();
        }
    };
    private void openItemEntry(){
        Intent intent = new Intent(this, ItemEntry.class);
        startActivity(intent);

    }
}
