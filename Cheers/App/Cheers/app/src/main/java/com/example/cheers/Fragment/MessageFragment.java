package com.example.cheers.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Handler;
import android.widget.Toast;

import com.example.cheers.Activity.ChatActivity;
import com.example.cheers.Activity.PersonActivity.PersonalPageActivity;
import com.example.cheers.Adapter.MessageAdapter;
import com.example.cheers.Adapter.StudentInfoAdapter;
import com.example.cheers.Infos.Message;
import com.example.cheers.Infos.StudentInfo;
import com.example.cheers.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MessageFragment extends Fragment {
    private List<Message> list = new ArrayList<Message>();

    ListView listView;
    File cache;

    private String[] chatNames;
    private int[] avatars;
    private String[] chatInfos;
    private String[] times;

    private String url = "http://192.168.1.8:8081/chat_list?user_name=jimmy";

    Handler handler = new Handler(){

        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    Log.d("list", list.toString());
                    MessageAdapter adapter = new MessageAdapter(getActivity(), R.layout.message_item, list);
                    listView.setAdapter(adapter);
                    break;
            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_message, container, false);

        TextView textView = (TextView) view.findViewById(R.id.msg_title);
        listView = (ListView) view.findViewById(R.id.msg_list);
        getDataFromHttp();

        cache = new File(Environment.getExternalStorageDirectory(), "cache");
        if (!cache.exists()) cache.mkdirs();



        //on item click event (jump to chat page)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Message message = list.get(position);
                Bundle bundle = new Bundle();

                bundle.putString("name", message.getName());
                bundle.putInt("avatar", message.getImg_id());

                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return view;
    }





    private void getDataFromHttp() {

        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).get().build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //Toast.makeText(getActivity(), "GET failed", Toast.LENGTH_SHORT).show();
                Log.d("Http GET", "GET FALIED");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject json = new JSONObject(response.body().string());
                    JSONArray res = json.getJSONArray("chat_list");
                    //JSONArray len = json.getJSONArray("chat_num");
                    for (int i = 0; i < res.length(); i ++) {
                        JSONObject val = res.getJSONObject(i);
                        String name = val.getString("ChatName");
                        int avatar = val.getInt("ImgUrl");
                        String info = val.getString("ChatInfo");
                        String time = val.getString("Time");

                        Log.d("name", name);
                        Log.d("avatar", avatar+"");
                        Log.d("info", info);
                        Log.d("time", time);

                        Message m = new Message(name, avatar, info, time);
                        list.add(m);


                    }

                    android.os.Message message = new android.os.Message();
                    message.what = 1;
                    handler.sendMessage(message);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        for (File file : cache.listFiles()) {
            file.delete();
        }
        cache.delete();
        super.onDestroy();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            list.clear();
            getDataFromHttp();
        }
    }

}
