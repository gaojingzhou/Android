package com.example.cheers.Adapter;

import java.util.List;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cheers.Activity.ChatActivity;
import com.example.cheers.Infos.ChatInfo;
import com.example.cheers.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends BaseAdapter{
    private Context context;
    private List<ChatInfo> lists;
    private ChatActivity mContext;

    public ChatAdapter(ChatActivity context, List<ChatInfo> lists) {
        super();
        this.mContext = context;
        this.lists = lists;
    }


    public static interface IMsgViewType {
        int IMVT_COM_MSG = 0;// 收到对方的消息
        int IMVT_TO_MSG = 1;// 自己发送出去的消息
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return lists.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return lists.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    /**
     * 得到Item的类型，是对方发过来的消息，还是自己发送出去的
     */
    public int getItemViewType(int position) {
        ChatInfo entity = lists.get(position);

        if (entity.isSend()) {// 收到的消息
            return IMsgViewType.IMVT_COM_MSG;
        } else {// 自己发送的消息
            return IMsgViewType.IMVT_TO_MSG;
        }
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        // TODO Auto-generated method stub
        HolderView holderView = null;
        ChatInfo entity = lists.get(arg0);
        boolean isMeSend = entity.isSend();
        if (holderView == null) {
            holderView = new HolderView();

            if (isMeSend) {
                arg1 = View.inflate(mContext, R.layout.right_chat_item, null);
                holderView.left_message = (TextView) arg1.findViewById(R.id.chat_me_message);
                holderView.left_message.setText(entity.getChatMessage());
            } else {
                arg1 = View.inflate(mContext, R.layout.left_chat_item, null);
                holderView.avatar_left = (CircleImageView) arg1.findViewById(R.id.chat_avatar_left);
                holderView.avatar_left.setImageResource(mContext.getAvatarData());
                holderView.right_message = (TextView) arg1.findViewById(R.id.chat_left_message);
                holderView.right_message.setText(entity.getChatMessage());

            }
            arg1.setTag(holderView);
        } else {
            holderView = (HolderView) arg1.getTag();
        }

        return arg1;
    }

    class HolderView {
        TextView left_message;
        CircleImageView avatar_left;
        TextView right_message;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

}
