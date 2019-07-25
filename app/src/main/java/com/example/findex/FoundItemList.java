package com.example.findex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Date;

import utils.CustomFoundListAdapter;
import utils.FoundItem;

import static com.example.findex.LocationEnum.snell;

public class FoundItemList extends AppCompatActivity {

    FloatingActionButton itemEntry;
    FloatingActionButton logout;
    ListView foundListView;
    ArrayList<FoundItem> foundItems = new ArrayList<FoundItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_item_list);

        //List View
        foundListView = findViewById(R.id.myListView);
        PopulateList();
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

    private void openItemEntry(){
        Intent intent = new Intent(this, ItemEntry.class);
        startActivity(intent);

    }
    private void PopulateList(){
        Date date = new Date(0);
        LocationEnum location = snell;
        CategoryEnum category = CategoryEnum.phone;

        foundItems.add(new FoundItem((long) 1,R.drawable.macbooktestimage, "MacBookPro", "This is a macbook pro. I found this item in snell on the second floor near the " +
                "3d printing machines",category, location, date));
        foundItems.add(new FoundItem((long) 1,R.drawable.iphone, "Iphone 6", "I found an Iphone at Snell",category, location, date));
        foundItems.add(new FoundItem((long) 1,R.drawable.bottle, "Water Bottle", "Found this water bottle lying around in Curry",category, location, date));
        foundItems.add(new FoundItem((long) 1,R.drawable.mouse, "Mouse", "Did found a Dell Mouse in CCIS Lab",category, location, date));
        foundItems.add(new FoundItem((long) 1,R.drawable.keyboard, "Keyboard", "Apple wireless keyboard found in Snell First Floor", category, location, date));
        foundItems.add(new FoundItem((long) 1,R.drawable.umbrella, "Umbrella", "Umbrella found",category, location, date));
        foundItems.add(new FoundItem((long) 1,R.drawable.shoes, "Shoes", "Found a pair of shoes in changing room of marino",category, location, date));
        foundItems.add(new FoundItem((long) 1,R.drawable.watch, "I Watch", "Found an Iwatch series 5 near water fountain in curry.", category, location, date));
        foundItems.add(new FoundItem((long) 1,R.drawable.charger, "MacBookPro Charger", "I Found this charger in RY256",category, location, date));

    }
}
