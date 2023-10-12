package com.neuronimbus.metropolis.adapters;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.Models.ProcessingFeedback;
import com.neuronimbus.metropolis.Models.chats;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.OnClickListner;
import com.neuronimbus.metropolis.Utils.RecyclerViewOnClickListner;

import java.util.ArrayList;

public class FeedbackChattingAdapter extends RecyclerView.Adapter<FeedbackChattingAdapter.ViewHolder> {
    Context context;
    ArrayList<com.neuronimbus.metropolis.Models.ProcessingFeedback>processingFeedback;
    private static final int VIEW_TYPE_MESSAGE_ADMIN = 1;
    private static final int VIEW_TYPE_MESSAGE_USER = 2;
    RecyclerViewOnClickListner onclicklistener;

    public FeedbackChattingAdapter(Context context, ArrayList<ProcessingFeedback> processingFeedback, RecyclerViewOnClickListner onclicklistener) {
        this.context = context;
        this.processingFeedback = processingFeedback;
        this.onclicklistener = onclicklistener;
    }


    @Override
    public int getItemViewType(int position) {
        if(processingFeedback.get(position).getType().equals("reply"))
        {

            return VIEW_TYPE_MESSAGE_ADMIN;
        }
        else if(processingFeedback.get(position).getType().equals("question"))
        {

            return  VIEW_TYPE_MESSAGE_USER;
        }

        return position;
    }

    @NonNull
    @Override
    public FeedbackChattingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==VIEW_TYPE_MESSAGE_ADMIN)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_expert_chatting,parent,false);
            return new ViewHolder(view);

        }
        else if(viewType==VIEW_TYPE_MESSAGE_USER)
        {
            View view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_chatting_item,parent,false);
            return new ViewHolder(view1);
        }

        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackChattingAdapter.ViewHolder holder, int position) {
            if(processingFeedback.get(position).getType().equals("reply"))
            {
                holder.replyButtonHide(processingFeedback.get(position),position);
                holder.reply_que.setText(Html.fromHtml(processingFeedback.get(position).getAssistance_type()));
                holder.reply_msg.setText(Html.fromHtml(processingFeedback.get(position).getFeedback_desc()));
                holder.reply_msg.setMovementMethod(LinkMovementMethod.getInstance());
                holder.txt_expert_chat_date.setText(Html.fromHtml(processingFeedback.get(position).getFeedback_datetime()));

            }
            else if(processingFeedback.get(position).getType().equals("question"))
            {

                holder.assistantType.setText(processingFeedback.get(position).getAssistance_type());
                holder.UserMsg.setText(processingFeedback.get(position).getFeedback_desc());
                holder.UserMsg.setMovementMethod(LinkMovementMethod.getInstance());
                holder.chat_date.setText(processingFeedback.get(position).getFeedback_datetime());
                if (processingFeedback.get(position).getFeedback_img() != null && !processingFeedback.get(position).getFeedback_img().isEmpty()){
                    holder.feedback_img.setVisibility(View.VISIBLE);
                    Glide.with(context).load(processingFeedback.get(position).getFeedback_img()).into(holder.feedback_img);
                }
                else {
                    holder.feedback_img.setVisibility(View.GONE);
                }


            }

        if (holder.btnReply != null){
            holder.btnReply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclicklistener.onClickData(position, processingFeedback.get(position).getMainFeedbackId()
                            , processingFeedback.get(position).getAssistance_type(), processingFeedback.get(position).getFeedbackReplyIdMain()
                            , processingFeedback.get(position).getFeedbackIdType());
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return processingFeedback.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView assistantType, UserMsg, chat_date;
        ImageView feedback_img;
        TextView reply_que, reply_msg, txt_expert_chat_date, btnReply;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            assistantType = itemView.findViewById(R.id.assistantType);
            UserMsg = itemView.findViewById(R.id.UserMsg);
            chat_date = itemView.findViewById(R.id.chat_date);
            feedback_img = itemView.findViewById(R.id.feedback_img);
            reply_que = itemView.findViewById(R.id.reply_que);
            reply_msg = itemView.findViewById(R.id.reply_msg);
            txt_expert_chat_date = itemView.findViewById(R.id.txt_expert_chat_date);
            btnReply = itemView.findViewById(R.id.btn_Reply);


        }

        public void replyButtonHide(final ProcessingFeedback processingFeedback, int position){
            if (processingFeedback.isReply != null) {

                if (processingFeedback.isReply) {
                    btnReply.setVisibility(View.GONE);
                } else {
                    btnReply.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}
