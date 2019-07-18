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

import utils.CustomLostItemListAdapter;
import utils.LostItem;

public class LostItemList extends AppCompatActivity {
    Button itemEntry;
    ListView lostItemView;
    ArrayList<LostItem> lostItems = new ArrayList<LostItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_item_list);

        lostItemView = findViewById(R.id.myListView2);

        PopulateList();

        CustomLostItemListAdapter myAdapter = new CustomLostItemListAdapter(this, lostItems);

        lostItemView.setAdapter(myAdapter);
        //Spinner creation
        final Spinner mySpinner = (Spinner) findViewById(R.id.spinner2);



        ArrayAdapter<String> mySpinnerAdapter = new ArrayAdapter<String>(LostItemList.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.lists));
        mySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(mySpinnerAdapter);
        mySpinner.setSelection(1);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String test = ((TextView) view).getText().toString();
                if (test.equals("Found Items")) {
                    mySpinner.setSelection(1);
                    Intent intent = new Intent(view.getContext(), FoundItemList.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        itemEntry = findViewById(R.id.addButton2);
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

        lostItems.add(new LostItem((long) 1, "MacBook Pro 2017","", date,R.drawable.computericon));
        lostItems.add(new LostItem((long) 1, "Dell Surface Notebook","", date,R.drawable.computericon));
        lostItems.add(new LostItem((long) 1, "Lenovo X1 Carbon","", date,R.drawable.computericon));
        lostItems.add(new LostItem((long) 1, "Microsoft Surface Laptop 2","", date,R.drawable.computericon));
        lostItems.add(new LostItem((long) 1, "Dell Inspiron 15in 2018","", date,R.drawable.computericon));
        lostItems.add(new LostItem((long) 1, "Lenovo Yoga","", date,R.drawable.computericon));
        lostItems.add(new LostItem((long) 1, "MacBook Pro 15in 2018","", date,R.drawable.computericon));
        lostItems.add(new LostItem((long) 1, "MSI 15.6in Laptop","", date,R.drawable.computericon));

    }
}


