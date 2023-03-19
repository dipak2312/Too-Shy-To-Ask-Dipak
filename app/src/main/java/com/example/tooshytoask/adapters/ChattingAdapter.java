package com.example.tooshytoask.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Models.chats;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class ChattingAdapter extends RecyclerView.Adapter<ChattingAdapter.ViewHolder> {
    Context context;
    ArrayList<chats> chats;
    private static final int VIEW_TYPE_MESSAGE_ADMIN = 1;
    private static final int VIEW_TYPE_MESSAGE_USER = 2;

    public ChattingAdapter(Context context, ArrayList<com.example.tooshytoask.Models.chats> chats) {
        this.context = context;
        this.chats = chats;
    }

    @Override
    public int getItemViewType(int position) {
        if(chats.get(position).getType().equals("reply"))
        {

            return VIEW_TYPE_MESSAGE_ADMIN;
        }
        else if(chats.get(position).getType().equals("question"))
        {

            return  VIEW_TYPE_MESSAGE_USER;
        }

        return position;
    }

    @NonNull
    @Override
    public ChattingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==VIEW_TYPE_MESSAGE_ADMIN)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ask_expert_chatting_reply_item,parent,false);
            return new ViewHolder(view);

        }
        else if(viewType==VIEW_TYPE_MESSAGE_USER)
        {
            View view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.ask_expert_chatting_item,parent,false);
            return new ViewHolder(view1);
        }

        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull ChattingAdapter.ViewHolder holder, int position) {
        if(chats.get(position).getType().equals("reply"))
        {
            holder.reply_que.setText(Html.fromHtml(chats.get(position).getReply()));
            holder.reply_msg.setText(Html.fromHtml(chats.get(position).getQuestion()));
            holder.txt_expert_chat_date.setText(Html.fromHtml(chats.get(position).getCreated_at()));
        }
        else if(chats.get(position).getType().equals("question"))
        {
            holder.user_msg.setText(Html.fromHtml(chats.get(position).getQuestion()));
            holder.txt_my_chat_date.setText(Html.fromHtml(chats.get(position).getCreated_at()));
        }
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView user_msg, txt_my_chat_date;
        TextView reply_que, reply_msg, txt_expert_chat_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            user_msg = itemView.findViewById(R.id.user_msg);
            txt_my_chat_date = itemView.findViewById(R.id.txt_my_chat_date);
            reply_que = itemView.findViewById(R.id.reply_que);
            reply_msg = itemView.findViewById(R.id.reply_msg);
            txt_expert_chat_date = itemView.findViewById(R.id.txt_expert_chat_date);
        }
    }
}
