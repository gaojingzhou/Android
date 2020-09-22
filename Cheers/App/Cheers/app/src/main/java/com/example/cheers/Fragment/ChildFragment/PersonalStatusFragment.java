package com.example.cheers.Fragment.ChildFragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.cheers.Adapter.ImageAdapter;
import com.example.cheers.Adapter.TextTagAdapter;
import com.example.cheers.R;
import com.moxun.tagcloudlib.view.TagCloudView;


public class PersonalStatusFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_status, container, false);

        GridView gridView = (GridView) view.findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(getActivity()));

        TagCloudView tagCloudView = (TagCloudView) view.findViewById(R.id.tag_cloud);
        tagCloudView.setBackgroundColor(Color.WHITE);
        tagCloudView.setAdapter(new TextTagAdapter(new String[20]));


        return view;
    }


}
