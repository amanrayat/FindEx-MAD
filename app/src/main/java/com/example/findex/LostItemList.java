package com.example.findex;


import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;

import utils.CustomLostItemListAdapter;
import utils.LostItem;

public class LostItemList extends AppCompatActivity {
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

        // Will add item description page based on item id.
//        foundListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                // trigger the second activity - character info
//                Intent intent = new Intent();
//
//                // Pass in the character name to second activity
//                intent.putExtra("FoundItemId",foundItems.get(i).getId());
//                startActivity(intent);
//            }
//        });

    }

    private void PopulateList(){
        Date date = new Date(0);

        lostItems.add(new LostItem((long) 1, "MacBook Pro 2017","", date,R.drawable.computericon));
        lostItems.add(new LostItem((long) 1, "MacBook Pro 2017","", date,R.drawable.computericon));
        lostItems.add(new LostItem((long) 1, "MacBook Pro 2017","", date,R.drawable.computericon));
        lostItems.add(new LostItem((long) 1, "MacBook Pro 2017","", date,R.drawable.computericon));
        lostItems.add(new LostItem((long) 1, "MacBook Pro 2017","", date,R.drawable.computericon));
        lostItems.add(new LostItem((long) 1, "MacBook Pro 2017","", date,R.drawable.computericon));
        lostItems.add(new LostItem((long) 1, "MacBook Pro 2017","", date,R.drawable.computericon));
        lostItems.add(new LostItem((long) 1, "MacBook Pro 2017","", date,R.drawable.computericon));

    }
}


