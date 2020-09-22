package com.example.cheers.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cheers.Infos.StudentInfo;
import com.example.cheers.R;

import java.util.List;

public class StudentInfoAdapter extends ArrayAdapter {
    private final int resourceId;

    public StudentInfoAdapter(Context context, int textViewResourceId, List<StudentInfo> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final StudentInfo studentInfo = (StudentInfo) getItem(position);
        View view;
        ViewHolder viewHolder;


        if (convertView == null) {
            view= LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) view.findViewById(R.id.img);
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.age = (TextView) view.findViewById(R.id.age);
            viewHolder.sex = (ImageView) view.findViewById(R.id.sex);
            viewHolder.distance = (TextView) view.findViewById(R.id.distance);
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.img.setImageResource(studentInfo.getImg_id());
        viewHolder.name.setText(studentInfo.getName());
        viewHolder.age.setText(studentInfo.getAge() + " years old");
        viewHolder.sex.setImageResource(studentInfo.getSex());
        viewHolder.distance.setText(studentInfo.getDistance() + "m");

        return view;

    }

    class ViewHolder {
        ImageView img;
        TextView name;
        TextView age;
        ImageView sex;
        TextView distance;
    }
}
