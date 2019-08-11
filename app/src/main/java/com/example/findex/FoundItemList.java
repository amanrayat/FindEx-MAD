package com.example.findex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utils.CustomFoundListAdapter;
import utils.FoundItem;

public class FoundItemList extends AppCompatActivity {

    FloatingActionButton itemEntry;
    ImageButton logout;
    ListView foundListView;
    private DatabaseReference mDatabase;
    private SearchView searchBar;
    ArrayList<FoundItem> foundItems = new ArrayList<>();
    String TAG = "debug";
    CustomFoundListAdapter myAdapter = new CustomFoundListAdapter(this, foundItems);
    GoogleSignInClient mGoogleSignInClient;
    List<String> keyList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.orderByChild("date");
        mDatabase.addChildEventListener(childEventListener);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_item_list);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id2))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //List View
        foundListView = findViewById(R.id.myListView);
        searchBar = findViewById(R.id.searchBar);
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

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Query query = mDatabase.orderByChild("title").startAt(searchBar.getQuery().toString()).endAt(searchBar.getQuery().toString() + "\uf8ff");
                foundItems.clear();
                query.addChildEventListener(childEventListener2);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                foundItems.clear();
                mDatabase.addChildEventListener(childEventListener);
                return false;
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
                mGoogleSignInClient.signOut().addOnCompleteListener(
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent intent = new Intent(FoundItemList.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });

            }
        });
    }
    ChildEventListener childEventListener2 = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
            FoundItem item = dataSnapshot.getValue(FoundItem.class);
            foundItems.add(item);
            keyList.add(dataSnapshot.getKey());

            Collections.reverse(foundItems);
            Collections.reverse(keyList);
            myAdapter.notifyDataSetChanged();
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

    ChildEventListener childEventListener = new ChildEventListener() {

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
            Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
            FoundItem item = dataSnapshot.getValue(FoundItem.class);
            foundItems.add(item);
            keyList.add(dataSnapshot.getKey());

            Collections.reverse(foundItems);
            Collections.reverse(keyList);
            myAdapter.notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            int index = keyList.indexOf(dataSnapshot.getKey());
            foundItems.remove(index);
            keyList.remove(index);
            myAdapter.notifyDataSetChanged();
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

    @Override
    public void onBackPressed() {
        }
    }

