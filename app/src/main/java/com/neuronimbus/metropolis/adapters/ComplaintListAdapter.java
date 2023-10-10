package com.neuronimbus.metropolis.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.Models.ComplaintHistoryChat;
import com.neuronimbus.metropolis.Models.ComplaintListResponse;
import com.neuronimbus.metropolis.Models.faqcontent;
import com.neuronimbus.metropolis.R;

import java.util.ArrayList;

public class ComplaintListAdapter extends RecyclerView.Adapter<ComplaintListAdapter.ViewHolder> {
    Context context;
    ArrayList<com.neuronimbus.metropolis.Models.ComplaintHistoryChat>complaintHistoryChat;

    public ComplaintListAdapter(Context context, ArrayList<ComplaintHistoryChat> complaintHistoryChat) {
        this.context = context;
        this.complaintHistoryChat = complaintHistoryChat;
    }

    @NonNull
    @Override
    public ComplaintListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_complaint_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintListAdapter.ViewHolder holder, int position) {

        if (complaintHistoryChat != null && complaintHistoryChat.size() >0 ){
            if (complaintHistoryChat.get(position).getSubject() != null && !complaintHistoryChat.get(position).getSubject().isEmpty()){
                holder.qsnView.setText(Html.fromHtml(complaintHistoryChat.get(position).getSubject()));
            }
            if (complaintHistoryChat.get(position).getTopic() != null && !complaintHistoryChat.get(position).getTopic().isEmpty()){
                holder.ansView.setText(Html.fromHtml(complaintHistoryChat.get(position).getTopic()));
            }
            if (complaintHistoryChat.get(position).getDescription() != null && !complaintHistoryChat.get(position).getDescription().isEmpty()){
                holder.fullDescription.setText(Html.fromHtml(complaintHistoryChat.get(position).getDescription()));
            }
            if (complaintHistoryChat.get(position).getReply() != null && !complaintHistoryChat.get(position).getReply().isEmpty()){
                holder.adminReply.setText(Html.fromHtml(complaintHistoryChat.get(position).getReply()));
            }
            if (complaintHistoryChat.get(position).getReply() == null || complaintHistoryChat.get(position).getReply().isEmpty()){
                holder.replyTitle.setVisibility(View.GONE);
                holder.adminReply.setVisibility(View.GONE);
            }
            if (complaintHistoryChat.get(position).getComplaintform_img() != null && !complaintHistoryChat.get(position).getComplaintform_img().isEmpty()){
                holder.complaintImage.setVisibility(View.VISIBLE);
                Glide.with(context).load(complaintHistoryChat.get(position).getComplaintform_img()).into(holder.complaintImage);
            }
            if (complaintHistoryChat.get(position).getComplaintform_img() == null && complaintHistoryChat.get(position).getComplaintform_img().isEmpty()){
                holder.complaintImage.setVisibility(View.GONE);
            }

        }


        boolean isExpandable = complaintHistoryChat.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable? View.VISIBLE : View.GONE);

        holder.expandableLayout.setVisibility(isExpandable? View.VISIBLE : View.GONE);

        Typeface tf_regular = Typeface.createFromAsset(context.getAssets(), "font/worksansmedium.ttf");
        if (complaintHistoryChat.get(position).isExpandable()){
            holder.qsnView.setTypeface(tf_regular,Typeface.NORMAL);
            holder.ansView.setTypeface(tf_regular);
            holder.fullDescription.setTypeface(tf_regular);
            holder.adminReply.setTypeface(tf_regular);
            holder.showAnswer.setImageResource(R.drawable.down_arrow);

            holder.qsnView.setTextColor(context.getResources().getColor(R.color.black));
            holder.qsnView.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        }else {
            holder.qsnView.setTypeface(tf_regular,Typeface.NORMAL);
            holder.ansView.setTypeface(tf_regular);
            holder.fullDescription.setTypeface(tf_regular);
            holder.adminReply.setTypeface(tf_regular);
            holder.showAnswer.setImageResource(R.drawable.update_arrow);

            holder.qsnView.setTextColor(context.getResources().getColor(R.color.black));

            holder.qsnView.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        }
    }

    @Override
    public int getItemCount() {
        return complaintHistoryChat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView qsnView, ansView, adminReply, fullDescription, replyTitle;
        ImageView showAnswer, complaintImage;
        OnQuestionClickListener onQuestionClickListener;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;
        CardView cardView, cardViewMain;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.onQuestionClickListener = onQuestionClickListener;
            qsnView = itemView.findViewById(R.id.question_tv);
            ansView = itemView.findViewById(R.id.topicTitleText);
            fullDescription = itemView.findViewById(R.id.fullDescription);
            replyTitle = itemView.findViewById(R.id.replyTitle);
            adminReply = itemView.findViewById(R.id.adminReply);
            showAnswer =  itemView.findViewById(R.id.show_answer);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            expandableLayout =itemView.findViewById(R.id.expandable);
            cardViewMain = itemView.findViewById(R.id.main_card_at_faq_item);
            complaintImage = itemView.findViewById(R.id.complaintImage);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   ComplaintHistoryChat model = complaintHistoryChat.get(getAdapterPosition());
                    model.setExpandable(!model.isExpandable());



                    notifyItemChanged(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {
            onQuestionClickListener.OnAskedQuestionClick(getAdapterPosition());
        }
    }

    public interface OnQuestionClickListener {

        void OnAskedQuestionClick(int position);


    }
}
