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

import java.util.ArrayList;
import java.util.Date;

import utils.CustomFoundListAdapter;
import utils.FoundItem;

public class FoundItemList extends AppCompatActivity {

    Button itemEntry;
    ListView foundListView;
    ArrayList<FoundItem> foundItems = new ArrayList<FoundItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_item_list);

        //Spinner creation
        final Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> mySpinnerAdapter = new ArrayAdapter<String>(FoundItemList.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.lists));
        mySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(mySpinnerAdapter);


        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String test = ((TextView) view).getText().toString();
                if(test.equals("Lost Items")){
                    mySpinner.setSelection(0);
                    Intent intent = new Intent(view.getContext(), LostItemList.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




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
                intent.putExtra("charName", foundItems.get(i).getTitle());
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
    }

    private void openItemEntry(){
        Intent intent = new Intent(this, ItemEntry.class);
        startActivity(intent);

    }
    private void PopulateList(){
        Date date = new Date(0);

        foundItems.add(new FoundItem((long) 1,R.drawable.macbooktestimage, "MacBookPro", "This is a macbook pro. I found this item in snell on the second floor near the " +
                "3d printing machines", date));
        foundItems.add(new FoundItem((long) 1,R.drawable.iphone, "Iphone 6", "I found an Iphone at Snell", date));
        foundItems.add(new FoundItem((long) 1,R.drawable.bottle, "Water Bottle", "Found this water bottle lying around in Curry", date));
        foundItems.add(new FoundItem((long) 1,R.drawable.mouse, "Mouse", "Did found a Dell Mouse in CCIS Lab", date));
        foundItems.add(new FoundItem((long) 1,R.drawable.keyboard, "Keyboard", "Apple wireless keyboard found in Snell First Floor", date));
        foundItems.add(new FoundItem((long) 1,R.drawable.umbrella, "Umbrella", "Umbrella found", date));
        foundItems.add(new FoundItem((long) 1,R.drawable.shoes, "Shoes", "Found a pair of shoes in changing room of marino", date));
        foundItems.add(new FoundItem((long) 1,R.drawable.watch, "I Watch", "Found an Iwatch series 5 near water fountain in curry.", date));
        foundItems.add(new FoundItem((long) 1,R.drawable.charger, "MacBookPro Charger", "I Found this charger in RY256", date));

    }
}
