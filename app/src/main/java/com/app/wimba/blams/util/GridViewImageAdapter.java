package com.app.wimba.blams.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by IT02107 on 5/13/2018.
 */

public class GridViewImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<Integer> imageIDs;

    public GridViewImageAdapter(Context context, List<Integer> imageIDs) {
        this.mContext = context;
        this.imageIDs = imageIDs;
    }

    public int getCount() {
        return imageIDs.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView mImageView;

        if (convertView == null) {
            mImageView = new ImageView(mContext);
            mImageView.setLayoutParams(new GridView.LayoutParams(500, 500));
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageView.setPadding(5, 5, 5, 5);
        } else {
            mImageView = (ImageView) convertView;
        }
        mImageView.setImageResource(imageIDs.get(position));
        return mImageView;
    }
}