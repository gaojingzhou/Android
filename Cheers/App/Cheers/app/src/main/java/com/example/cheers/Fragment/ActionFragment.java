package com.example.cheers.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cheers.Activity.MainActivity;
import com.example.cheers.Adapter.ActionAdapter;
import com.example.cheers.Infos.ActionInfo;
import com.example.cheers.R;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

import static android.app.Activity.RESULT_OK;


public class ActionFragment extends Fragment {
    private List<ActionInfo> list;
    private int[] actionAvatars;
    private String[] usernames;
    private String[] actionDates;
    private int[] actionImages;
    private  String[] actionDescriptions;
    private String[] actionPostions;
    private int sexs[];
    private int ages[];


    private File cameraSavePath;
    private Uri uri;
    private String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Actioninit();


        View view = inflater.inflate(R.layout.fragment_action, container, false);


        ListView listView = (ListView) view.findViewById(R.id.action_list);
        listView.setAdapter(new ActionAdapter((LinkedList<ActionInfo>) list, getActivity()));

        ImageView add = (ImageView) view.findViewById(R.id.add);
        cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //jump to add acticity
            }
        });

        return view;
    }


    private void Actioninit() {
        actionAvatars = new int[]{R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4};
        Log.d("image", R.drawable.img1+"");
        Log.d("image", R.drawable.img2+"");
        Log.d("image", R.drawable.img3+"");
        Log.d("image", R.drawable.img4+"");
        Log.d("image", R.drawable.img5+"");
        usernames = new String[]{"Jimmy","Joe","Mary","Bob"};
        actionDates = new String[]{"刚刚","12:00","14:15","15:40"};
        actionImages = new int[]{R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4};
        actionDescriptions = new String[]{"分享图片","什么适合开学啊","有东校区的朋友吗","好想上大专"};
        actionPostions = new String[]{"中山大学·东校区","中山大学·南校区","中山大学·北校区","中山大学·珠海校区"};
        sexs = new int[] {R.drawable.ic_male, R.drawable.ic_male, R.drawable.ic_female, R.drawable.ic_male};
        ages = new int[] {20, 21, 22, 20};


        list = new LinkedList<>();
        for (int i = 0; i < actionAvatars.length; i ++) {
            list.add(new ActionInfo(actionAvatars[i], usernames[i], actionDates[i], actionImages[i], actionDescriptions[i], actionPostions[i], ages[i], sexs[i]));
        }

    }



}
