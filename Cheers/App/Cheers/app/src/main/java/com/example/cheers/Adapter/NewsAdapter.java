package com.example.cheers.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.cheers.Infos.News;
import com.example.cheers.R;

import java.util.List;

public class NewsAdapter extends ArrayAdapter{

    private final int resourceId;

    public NewsAdapter(Context context, int textViewResourceId, List<News> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final News news = (News) getItem(position);
        View view;
        ViewHolder viewHolder;


        if (convertView == null) {
            view= LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.news_title);
            viewHolder.time= (TextView) view.findViewById(R.id.news_time);
            viewHolder.department = (TextView) view.findViewById(R.id.news_department);

            view.setTag(viewHolder);
        }
        else {
            view = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.title.setText(news.getTitle());
        viewHolder.time.setText(news.getTime());
        viewHolder.department.setText(news.getDepartment());

        return view;

    }

    class ViewHolder {
        TextView title;
        TextView time;
        TextView department;
    }
}

