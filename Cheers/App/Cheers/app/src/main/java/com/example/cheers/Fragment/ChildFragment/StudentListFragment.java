package com.example.cheers.Fragment.ChildFragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.cheers.Activity.PersonActivity.PersonalPageActivity;
import com.example.cheers.Infos.StudentInfo;
import com.example.cheers.MyView.ScrollListView;
import com.example.cheers.R;
import com.example.cheers.Adapter.StudentInfoAdapter;
import com.q42.android.scrollingimageview.ScrollingImageView;

import java.util.ArrayList;
import java.util.List;


public class StudentListFragment extends Fragment {

    private List<StudentInfo> list = new ArrayList<StudentInfo>();
    private ImageView img;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             final Bundle savedInstanceState) {
        Studentinit();

        View view = inflater.inflate(R.layout.fragment_student, container, false);

        ScrollListView listView = (ScrollListView) view.findViewById(R.id.stu_list);
        StudentInfoAdapter adapter = new StudentInfoAdapter(getActivity(), R.layout.student_info_item, list);
        listView.setAdapter(adapter);

        //on item click event (jump to personal page)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudentInfo studentInfo = list.get(position);
                Bundle bundle = new Bundle();

                bundle.putString("name", studentInfo.getName());
                bundle.putInt("age", studentInfo.getAge());
                bundle.putInt("avatar", studentInfo.getImg_id());
                bundle.putInt("sex", studentInfo.getSex());

                Intent intent = new Intent(getActivity(), PersonalPageActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        ScrollingImageView scrollingImageView = (ScrollingImageView) view.findViewById(R.id.siv);
        scrollingImageView.stop();
        scrollingImageView.start();
        img = (ImageView) view.findViewById(R.id.siv_image);


        return view;
    }



    private void Studentinit() {

        StudentInfo s1 = new StudentInfo("小明", R.drawable.img1, 20, R.drawable.ic_male, 100);
        list.add(s1);
        StudentInfo s2 = new StudentInfo("小王", R.drawable.img2, 21, R.drawable.ic_male, 200);
        list.add(s2);
        StudentInfo s3 = new StudentInfo("小李", R.drawable.img3, 20, R.drawable.ic_female, 300);
        list.add(s3);
        StudentInfo s4 = new StudentInfo("小刚", R.drawable.img4, 20, R.drawable.ic_female, 100);
        list.add(s4);
        StudentInfo s5 = new StudentInfo("小宋", R.drawable.img5, 22, R.drawable.ic_male, 100);
        list.add(s5);
        StudentInfo s6 = new StudentInfo("小蒋", R.drawable.img1, 20, R.drawable.ic_male, 100);
        list.add(s6);
        StudentInfo s7 = new StudentInfo("小华", R.drawable.img2, 21, R.drawable.ic_male, 200);
        list.add(s7);
        StudentInfo s8 = new StudentInfo("小高", R.drawable.img3, 20, R.drawable.ic_female, 300);
        list.add(s8);
        StudentInfo s9 = new StudentInfo("小钱", R.drawable.img4, 20, R.drawable.ic_female, 100);
        list.add(s9);
        StudentInfo s10 = new StudentInfo("小张", R.drawable.img5, 22, R.drawable.ic_male, 100);
        list.add(s10);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
