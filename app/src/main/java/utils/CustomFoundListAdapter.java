package utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findex.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomFoundListAdapter extends BaseAdapter {

    Context context;
    ArrayList<FoundItem> foundItems;

    public CustomFoundListAdapter(Context context, ArrayList<FoundItem> foundItems){
        this.context = context;
        this.foundItems = foundItems;
    }


    @Override
    public int getCount() {
        return foundItems.size();
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
        ViewHolder viewHolder;

        if(view == null){
            view = View.inflate(context, R.layout.found_item_adapter_item, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = view.findViewById(R.id.foundItemImageAdapterItem);
            viewHolder.titleView = view.findViewById(R.id.foundItemTitleAdapterItem);
            viewHolder.descriptionView = view.findViewById(R.id.foundItemDescriptionAdapterItem);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Picasso.get().load(foundItems.get(i).getimageUrl()).into(viewHolder.imageView);
        viewHolder.titleView.setText(foundItems.get(i).getTitle());
        viewHolder.descriptionView.setText(foundItems.get(i).getDescription());
        return view;
    }
    static class ViewHolder {
        ImageView imageView;
        TextView titleView;
        TextView descriptionView;
    }
}
