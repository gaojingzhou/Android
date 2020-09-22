package com.example.cheers.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.cheers.R;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c)
    {
        mContext=c;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mThumbIds.length;
    }
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ImageView imageview;
        if(convertView == null)
        {
            imageview = new ImageView(mContext);
            imageview.setLayoutParams(new GridView.LayoutParams(350, 350));
            imageview.setPadding(1,1,1,1);
        }
        else
        {
            imageview = (ImageView) convertView;
        }
        imageview.setImageResource(mThumbIds[position]);
        return imageview;
    }

    //image resource
    private Integer[] mThumbIds = {

            R.drawable.img1, R.drawable.img2,
            R.drawable.img3,R.drawable.img4,
            R.drawable.img5,R.drawable.img1,
    };
}
