package com.example.ashsrivast.pappu.listItem;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ashsrivast on 29/04/16.
 */
public class ImageAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<Integer> mPlayers;

    public ImageAdapter(Context c, List<Integer> players) {
        mContext = c;
        mPlayers = players;
    }

    public int getCount() {
        return mPlayers.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            textView = new TextView(mContext);
            textView.setLayoutParams(new GridView.LayoutParams(85, 85));
//            textView.setScaleType(TextView..CENTER_CROP);
            textView.setPadding(8, 8, 8, 8);
        } else {
            textView = (TextView) convertView;
        }

        textView.setText(String.valueOf(mPlayers.get(position)));
        return textView;
    }
}
