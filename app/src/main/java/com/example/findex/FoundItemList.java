package com.example.findex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Date;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_item_list);
        mDatabase = FirebaseDatabase.getInstance().getReference();



        //Spinner creation
//        final Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> mySpinnerAdapter = new ArrayAdapter<String>(FoundItemList.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.lists));
        mySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mySpinner.setAdapter(mySpinnerAdapter);


//        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String test = ((TextView) view).getText().toString();
//                if(test.equals("Lost Items")){
//                    mySpinner.setSelection(0);
//                    Intent intent = new Intent(view.getContext(), LostItemList.class);
//                    startActivity(intent);
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });


        //List View
        foundListView = findViewById(R.id.myListView);
//        PopulateList();
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
        mDatabase.addChildEventListener(childEventListener);
    }

    ChildEventListener childEventListener = new ChildEventListener() {

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
            Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
            FoundItem item = dataSnapshot.getValue(FoundItem.class);
            foundItems.add(new FoundItem(item.getId(),item.getimageUrl(), item.getTitle(), item.getDescription(), new Date(0)));
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            FoundItem item = dataSnapshot.getValue(FoundItem.class);
            foundItems.remove(new FoundItem(item.getId(),item.getimageUrl(), item.getTitle(), item.getDescription(), new Date(0)));
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
//    private void PopulateList(){
////        Date date = new Date(0);
////
////        foundItems.add(new FoundItem((long) 1,R.drawable.macbooktestimage, "MacBookPro", "This is a macbook pro. I found this item in snell on the second floor near the " +
////                "3d printing machines", date));
////        foundItems.add(new FoundItem((long) 1,R.drawable.iphone, "Iphone 6", "I found an Iphone at Snell", date));
////        foundItems.add(new FoundItem((long) 1,R.drawable.bottle, "Water Bottle", "Found this water bottle lying around in Curry", date));
////        foundItems.add(new FoundItem((long) 1,R.drawable.mouse, "Mouse", "Did found a Dell Mouse in CCIS Lab", date));
////        foundItems.add(new FoundItem((long) 1,R.drawable.keyboard, "Keyboard", "Apple wireless keyboard found in Snell First Floor", date));
////        foundItems.add(new FoundItem((long) 1,R.drawable.umbrella, "Umbrella", "Umbrella found", date));
////        foundItems.add(new FoundItem((long) 1,R.drawable.shoes, "Shoes", "Found a pair of shoes in changing room of marino", date));
////        foundItems.add(new FoundItem((long) 1,R.drawable.watch, "I Watch", "Found an Iwatch series 5 near water fountain in curry.", date));
////        foundItems.add(new FoundItem((long) 1,R.drawable.charger, "MacBookPro Charger", "I Found this charger in RY256", date));
////
////    }
}
