package com.example.javacalenderproject.uilayer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class CustomGridAdapter extends BaseAdapter {
    private final Context mContext;
    private final Integer[] mThumbIds;

    public CustomGridAdapter(Context context, Integer[] images) {
        this.mContext = context;
        this.mThumbIds = images;
    }

    @Override
    public int getCount() {
        return mThumbIds.length; // Return the number of items in the array
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position]; // Return the item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position; // Return the position as the item ID
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new LayoutParams(100, 100));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);

        return imageView;
    }
}