package com.example.findex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;

import utils.CustomFoundListAdapter;
import utils.FoundItem;

public class FoundItemList extends AppCompatActivity {

    ListView foundListView;
    ArrayList<FoundItem> foundItems = new ArrayList<FoundItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_item_list);

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
    }

    private void PopulateList(){
        Date date = new Date(0);
        foundItems.add(new FoundItem((long) 1,R.drawable.macbooktestimage, "MacBookPro", "This is a macbook pro. I found this item in snell on the second floor near the " +
                "3d printing machines", date));
        foundItems.add(new FoundItem((long) 1,R.drawable.macbooktestimage, "MacBookPro", "This is a macbook pro. I found this item in snell on the second floor near the " +
                "3d printing machines", date));
        foundItems.add(new FoundItem((long) 1,R.drawable.macbooktestimage, "MacBookPro", "This is a macbook pro. I found this item in snell on the second floor near the " +
                "3d printing machines", date));
        foundItems.add(new FoundItem((long) 1,R.drawable.macbooktestimage, "MacBookPro", "This is a macbook pro. I found this item in snell on the second floor near the " +
                "3d printing machines", date));
        foundItems.add(new FoundItem((long) 1,R.drawable.macbooktestimage, "MacBookPro", "This is a macbook pro. I found this item in snell on the second floor near the " +
                "3d printing machines", date));
        foundItems.add(new FoundItem((long) 1,R.drawable.macbooktestimage, "MacBookPro", "This is a macbook pro. I found this item in snell on the second floor near the " +
                "3d printing machines", date));
        foundItems.add(new FoundItem((long) 1,R.drawable.macbooktestimage, "MacBookPro", "This is a macbook pro. I found this item in snell on the second floor near the " +
                "3d printing machines", date));
        foundItems.add(new FoundItem((long) 1,R.drawable.macbooktestimage, "MacBookPro", "This is a macbook pro. I found this item in snell on the second floor near the " +
                "3d printing machines", date));
        foundItems.add(new FoundItem((long) 1,R.drawable.macbooktestimage, "MacBookPro", "This is a macbook pro. I found this item in snell on the second floor near the " +
                "3d printing machines", date));
    }
}
