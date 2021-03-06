package com.example.cheers.Adapter;



import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.moxun.tagcloudlib.view.TagsAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;



public class TextTagAdapter extends TagsAdapter {

    private List<String> dataSet = new ArrayList<>();
    public TextTagAdapter(@NonNull String... data) {
        dataSet.clear();
        Collections.addAll(dataSet, data);
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public View getView(final Context context, final int position, ViewGroup parent) {
        String[] name = {"电影", "小说", "摇滚", "抽象", "cosplay", "篮球", "动漫", "海贼王", "发呆", "美食", "旅行"};
        Random rand = new Random();
        int randNum = rand.nextInt(9);
        TextView tv = new TextView(context);
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(100, 100);
        tv.setLayoutParams(lp);
        tv.setText(name[randNum]);
        tv.setGravity(Gravity.CENTER);
        tv.setTypeface(Typeface.defaultFromStyle((Typeface.BOLD)));
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Click", "Tag " + position + " clicked.");
            }
        });
        return tv;
    }



    @Override
    public Object getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public int getPopularity(int position) {
        return position % 7;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor) {
        ((TextView) view).setTextColor(themeColor);
    }

}
