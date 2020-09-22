package com.example.cheers.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cheers.Infos.Message;
import com.example.cheers.R;

import java.util.List;

public class MessageAdapter extends ArrayAdapter {
    private final int resourceId;

    public MessageAdapter(Context context, int textViewResourceId, List<Message> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Message message = (Message) getItem(position);
        View view;
        ViewHolder viewHolder;


        if (convertView == null) {
            view= LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) view.findViewById(R.id.chat_img);
            viewHolder.name = (TextView) view.findViewById(R.id.chat_name);
            viewHolder.info = (TextView) view.findViewById(R.id.chat_info);
            viewHolder.time = (TextView) view.findViewById(R.id.chat_time);

            view.setTag(viewHolder);
        }
        else {
            view = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.img.setImageResource(message.getImg_id());
        viewHolder.name.setText(message.getName());
        viewHolder.info.setText(message.getInfo());
        viewHolder.time.setText(message.getTime());

        return view;

    }

    class ViewHolder {
        ImageView img;
        TextView name;
        TextView info;
        TextView time;
    }
}
