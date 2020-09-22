package com.example.cheers.Fragment.ChildFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cheers.Activity.PersonActivity.PersonalPageActivity;
import com.example.cheers.Adapter.ActionAdapter;
import com.example.cheers.Infos.ActionInfo;
import com.example.cheers.MyView.ScrollListView;
import com.example.cheers.R;

import java.util.LinkedList;
import java.util.List;


public class PersonalActionFragment extends Fragment {

    private List<ActionInfo> list;
    private int[] actionAvatars;
    private String[] usernames;
    private String[] actionDates;
    private int[] actionImages;
    private  String[] actionDescriptions;
    private String[] actionPostions;
    private int sexs[];
    private int ages[];

    private String username;
    private int imgId;


    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        PersonalPageActivity personalPageActivity = (PersonalPageActivity)getActivity();
        username = personalPageActivity.getDataName();
        imgId = personalPageActivity.getDataImgId();

        Actioninit();

        View view = inflater.inflate(R.layout.fragment_personal_action, container, false);
        ScrollListView listView = (ScrollListView) view.findViewById(R.id.personal_action_list);
        listView.setAdapter(new ActionAdapter((LinkedList<ActionInfo>) list, getActivity()));




        return view;
    }


    private void Actioninit() {
        //actionAvatars = new int[]{R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4};
        //usernames = new String[]{"Jimmy","Joe","Mary","Bob"};
        actionDates = new String[]{"刚刚","12:00","14:15","15:40"};
        actionImages = new int[]{R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4};
        actionDescriptions = new String[]{"分享图片","什么适合开学啊","有东校区的朋友吗","好想上大专"};
        actionPostions = new String[]{"中山大学·东校区","中山大学·南校区","中山大学·北校区","中山大学·珠海校区"};
        sexs = new int[] {R.drawable.ic_male, R.drawable.ic_male, R.drawable.ic_female, R.drawable.ic_male};
        ages = new int[] {20, 21, 22, 20};

        list = new LinkedList<>();
        for (int i = 0; i < actionImages.length; i ++) {
            list.add(new ActionInfo(imgId, username, actionDates[i], actionImages[i], actionDescriptions[i], actionPostions[i], ages[i], sexs[i]));
        }

        Log.d("length", list.size()+"");

    }



}
