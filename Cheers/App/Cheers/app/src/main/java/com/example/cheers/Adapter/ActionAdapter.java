package com.example.cheers.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.cheers.Activity.PersonActivity.PersonalPageActivity;
import com.example.cheers.Infos.ActionInfo;
import com.example.cheers.Infos.StudentInfo;
import com.example.cheers.R;

import java.util.LinkedList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActionAdapter extends BaseAdapter {
    private LinkedList<ActionInfo> aData;
    private Context mContext;
    private boolean isLike = false;
    private int commentIndex = 4;

    private TextView thumpUpView;

    public ActionAdapter(LinkedList<ActionInfo> aData, Context mContext) {
        this.aData = aData;
        this.mContext = mContext;
    }
    @Override
    public int getCount(){
        return aData.size();
    }
    @Override
    public  Object getItem(int position){
        return null;
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.action_item,parent,false);
            holder = new ViewHolder();
            holder.actionAvatar = convertView.findViewById(R.id.action_avatar);
            holder.username = convertView.findViewById(R.id.action_username);
            holder.actionDate = convertView.findViewById(R.id.action_date);
            holder.actionDescripation = convertView.findViewById(R.id.action_descripation);
            holder.image_view = convertView.findViewById(R.id.image_view);
            holder.actionPosition = convertView.findViewById(R.id.action_position);
            holder.action_iv_like = convertView.findViewById(R.id.action_iv_like);
            holder.action_iv_comment = convertView.findViewById(R.id.action_iv_comment);
            holder.action_iv_share = convertView.findViewById(R.id.action_iv_share);
            holder.action_et_comment = convertView.findViewById(R.id.action_et_comment);

            holder.actionSex = convertView.findViewById(R.id.action_sex);
            holder.actionAge = convertView.findViewById(R.id.action_age);

            holder.mContainer = convertView.findViewById(R.id.mContainer); //get layout
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.actionAvatar.setImageResource(aData.get(position).getAvatarId());
        holder.username.setText(aData.get(position).getUsername());
        holder.actionDate.setText(aData.get(position).getDate());
        holder.actionDescripation.setText(aData.get(position).getActionDescripation());
        holder.image_view.setImageResource(aData.get(position).getActionImageId());
        holder.actionPosition.setText(aData.get(position).getPosition());

        holder.actionSex.setImageResource(aData.get(position).getSex());
        holder.actionAge.setText(aData.get(position).getAge() + " years old");


        //click events

        //click like
        holder.action_iv_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLike) { //not liked yet
                    holder.action_iv_like.setImageResource(R.drawable.ic_like_red);
                    addThumpUpView(holder);
                    isLike = true;
                } else {
                    holder.action_iv_like.setImageResource(R.drawable.ic_like);
                    removeThumpUpView(holder);
                    isLike = false;
                }
            }
        });

        //click comment
        holder.action_iv_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.action_et_comment.requestFocus();
            }
        });

        //click send
        holder.action_et_comment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Drawable drawable = holder.action_et_comment.getCompoundDrawables()[2]; //len=4, four pics
                if (drawable == null) return false;
                if (event.getAction() != MotionEvent.ACTION_UP) return false;
                if (event.getX() > holder.action_et_comment.getWidth() - holder.action_et_comment.getPaddingRight() - drawable.getIntrinsicWidth()) {
                    String str = holder.action_et_comment.getText().toString().trim();
                    if (TextUtils.isEmpty(str)){

                    }else {
                        addView(holder, str);
                        holder.action_et_comment.setText(""); //clear
                        Toast.makeText(mContext,"发表成功", Toast.LENGTH_SHORT);
                    }

                }
                return false;
            }
        });

        //click avatar
        holder.actionAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionInfo actionInfo = aData.get(position);
                Bundle bundle = new Bundle();

                bundle.putString("name", actionInfo.getUsername());
                bundle.putInt("age", actionInfo.getAge());
                bundle.putInt("avatar", actionInfo.getAvatarId());
                bundle.putInt("sex", actionInfo.getSex());

                Intent intent = new Intent(mContext, PersonalPageActivity.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });



        
        return convertView;
    }

    //@SuppressLint("ResourceAsColor")
    private void addView(ViewHolder holder, String string) {
        TextView textView = new TextView(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 60);
        layoutParams.setMargins(0,15,0,15);
        textView.setTextSize(14);
        //textView.setTextColor(R.color.gray);
        textView.setText("Jimmy:"+string);
        int index = holder.mContainer.getChildCount();
        holder.mContainer.addView(textView,index - 1,layoutParams);

    }

    private void addThumpUpView(ViewHolder holder){
        thumpUpView = new TextView(mContext);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 60);
        lp.setMargins(0,15,0,15);
        thumpUpView.setTextSize(14);
        //thumpUpView.setTextColor(mContext.getColor(R.color.record_comment_text));
        thumpUpView.setText("Jimmy 点赞了");
        holder.mContainer.addView(thumpUpView,3,lp);
    }

    private void removeThumpUpView(ViewHolder holder){
        holder.mContainer.removeViewAt(3);
    }


    static class ViewHolder{
        CircleImageView actionAvatar;
        TextView username;
        TextView actionDate;
        TextView actionDescripation;
        ImageView image_view;
        TextView actionPosition;
        ImageView action_iv_like;
        ImageView action_iv_comment;
        ImageView action_iv_share;
        EditText action_et_comment;
        LinearLayout mContainer;

        ImageView actionSex;
        TextView actionAge;
    }


}
