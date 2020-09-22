package com.example.cheers.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.cheers.Activity.PersonActivity.MyLabelActivity;
import com.example.cheers.Activity.PersonActivity.MySchoolActivity;
import com.example.cheers.Activity.PersonActivity.MySettingActivity;
import com.example.cheers.Activity.PersonActivity.PersonalPageActivity;
import com.example.cheers.R;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class PersonFragment extends Fragment {
    private String name = "Jimmy";
    private int age = 20;
    private int sex = R.drawable.ic_male;
    private int imgId = R.drawable.head;




    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person, container, false);

        ImageView avatarImageView = (ImageView) view.findViewById(R.id.h_head);
        ImageView blurImageView = (ImageView) view.findViewById(R.id.h_back);
        Glide.with(getActivity())
                .load(R.drawable.head)
                .bitmapTransform(new BlurTransformation(getActivity(), 10), new CenterCrop(getActivity()))
                .into(blurImageView);
        Glide.with(getActivity())
                .load(R.drawable.head)
                .bitmapTransform(new CropCircleTransformation(getActivity()))
                .into(avatarImageView);

        ImageView mySchool = (ImageView) view.findViewById(R.id.item_school_arrow);
        ImageView myPage = (ImageView) view.findViewById(R.id.item_mainpage_arrow);
        ImageView myLabel = (ImageView) view.findViewById(R.id.item_label_arrow);
        ImageView mySetting = (ImageView) view.findViewById(R.id.item_setting_arrow);

        mySchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MySchoolActivity.class);
                startActivity(intent);
            }
        });

        myPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putString("name", name);
                bundle.putInt("age", age);
                bundle.putInt("avatar", imgId);
                bundle.putInt("sex", sex);

                Intent intent = new Intent(getActivity(), PersonalPageActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        myLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyLabelActivity.class);
                startActivity(intent);
            }
        });

        mySetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MySettingActivity.class);
                startActivity(intent);
            }
        });




        return view;
    }

    @Override

    public void onPause() {

        super.onPause();

    }
}
