package com.example.cheers.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cheers.Adapter.ChatAdapter;
import com.example.cheers.Fragment.MessageFragment;
import com.example.cheers.Infos.ChatInfo;
import com.example.cheers.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {
    private String name;
    private int avatar;

    private ChatAdapter chatAdapter;
    private ListView listView;

    private List<ChatInfo> personChats = new ArrayList<ChatInfo>();
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int what = msg.what;
            switch (what) {
                case 1:
                    listView.setSelection(personChats.size());
                    break;

                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportActionBar().hide();



        // receive parameters
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        avatar = bundle.getInt("avatar");




        ChatInfo personChat = new ChatInfo();
        personChat.setSend(false);
        personChat.setChatMessage("Cheers bro!");
        personChats.add(personChat);

        listView = (ListView) findViewById(R.id.chat_dialog);
        Button send = (Button) findViewById(R.id.chat_message_send);
        final EditText chat_message = (EditText) findViewById(R.id.chat_message);
        ImageView back = (ImageView) findViewById(R.id.chat_back);
        TextView title = (TextView) findViewById(R.id.chat_title);

        //chat_avatar.setImageResource(avatar);
        title.setText(name);

        chatAdapter = new ChatAdapter(this, personChats);
        listView.setAdapter(chatAdapter);

        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (TextUtils.isEmpty(chat_message.getText().toString())) {
                    Toast.makeText(ChatActivity.this, "发送内容不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                ChatInfo personChat = new ChatInfo();
                //sent by user
                personChat.setSend(true);
                personChat.setChatMessage(chat_message.getText().toString());
                //add to list
                personChats.add(personChat);
                //clear edit text
                chat_message.setText("");

                //update listview
                chatAdapter.notifyDataSetChanged();
                handler.sendEmptyMessage(1);


                //auto respond
                ChatInfo personChat2 = new ChatInfo();
                personChat2.setSend(false);
                personChat2.setChatMessage("Cheers bro!");
                personChats.add(personChat2);
                //update listview
                chatAdapter.notifyDataSetChanged();
                handler.sendEmptyMessage(1);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public int getAvatarData() { return avatar;}
}
