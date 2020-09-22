package com.example.cheers.Fragment.ChildFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.cheers.Adapter.NewsAdapter;
import com.example.cheers.Infos.Message;
import com.example.cheers.Infos.News;
import com.example.cheers.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CampusFragment extends Fragment {
    private VideoView videoView;
    private List<News> list = new ArrayList<News>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_campus, container, false);

        Newsinit();

        ImageView schoolImg = (ImageView) view.findViewById(R.id.school_img);
        TextView schoolName = (TextView) view.findViewById(R.id.school_name);
        TextView schoolTitle1 = (TextView) view.findViewById(R.id.school_title1);
        TextView schoolTitle2 = (TextView) view.findViewById(R.id.school_title2);
        final ImageView videoPlay = (ImageView) view.findViewById(R.id.video_play);
        ListView listView = (ListView) view.findViewById(R.id.news_list);
        videoView = (VideoView) view.findViewById(R.id.school_video);

        NewsAdapter adapter = new NewsAdapter(getActivity(), R.layout.news_item, list);
        listView.setAdapter(adapter);

        initVideo();

        videoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();
                videoPlay.setVisibility(View.INVISIBLE);
            }
        });


        return view;
    }

    private void Newsinit() {
        News n1 = new News("教务部关于即将通知关于即将通知关于即将通知关于即将通知最新通知的通知的通知的通知的通知", "6-17", "教务部");
        list.add(n1);
        News n2 = new News("教务部关于即将通知关于即将通知关于即将通知最新通知的通知的通知的通知", "6-18", "教务部");
        list.add(n2);
        News n3 = new News("教务部关于即将通知关于即将通知最新通知的通知的通知", "6-19", "教务部");
        list.add(n3);
        News n4 = new News("教务部关于即将通知最新通知的通知", "6-20", "教务部");
        list.add(n4);
        News n5 = new News("教务部最新通知", "6-21", "教务部");
        list.add(n5);
    }


    private void initVideo() {
        MediaController mediaController = new MediaController(getActivity());
        videoView.setMediaController(mediaController);
        String url = "https://vdept.bdstatic.com/6b6941464373554a3751637739575632/50697077634b5136/e8371a455159758e77690cd54e584e190ea3a4aa67a21e055dedbd6d11dcf49ff71a666e4af43b66ad703a214197f5e0ceebda0b4fa667b543c8994940eef1bd.mp4?auth_key=1593598444-0-0-98f5e2d735397966fc55d6147b9e8210";
        videoView.setVideoPath(url);

        //MediaMetadataRetriever media = new MediaMetadataRetriever();
        //media.setDataSource(url, new HashMap());
        //Bitmap bitmap = media.getFrameAtTime();

    }
}
