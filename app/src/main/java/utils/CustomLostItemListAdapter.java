package utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findex.R;

import java.util.ArrayList;

public class CustomLostItemListAdapter extends BaseAdapter {
    Context context;
    ArrayList<LostItem> lostItems;

    public CustomLostItemListAdapter(Context context, ArrayList<LostItem> lostItems){
        this.context = context;
        this.lostItems = lostItems;
    }

    @Override
    public int getCount() {
        return lostItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
        }
        ImageView imageView = view.findViewById(R.id.lostItemIcon);
        TextView text = view.findViewById(R.id.lostItemTitle);

        imageView.setImageResource(lostItems.get(i).getCategory());
        text.setText(lostItems.get(i).getTitle());

        return view;
    }
}
