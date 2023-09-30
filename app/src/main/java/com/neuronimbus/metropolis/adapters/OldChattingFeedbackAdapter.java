package com.neuronimbus.metropolis.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.Models.FeedbackChating;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.BookmarkClicked;
import com.neuronimbus.metropolis.activity.Blogs.DetailBlogActivity;

import java.util.ArrayList;

public class OldChattingFeedbackAdapter extends RecyclerView.Adapter<OldChattingFeedbackAdapter.ViewHolder>{
    Context context;
    ArrayList<com.neuronimbus.metropolis.Models.FeedbackChating>feedbackChating;

    public OldChattingFeedbackAdapter(Context context, ArrayList<FeedbackChating> feedbackChating) {
        this.context = context;
        this.feedbackChating = feedbackChating;
    }

    @NonNull
    @Override
    public OldChattingFeedbackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_old_feedback_chatting_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OldChattingFeedbackAdapter.ViewHolder holder, int position) {
        holder.messageFrom.setText(Html.fromHtml(feedbackChating.get(position).getExpertFeedbackLabel()));
        holder.date.setText(Html.fromHtml(feedbackChating.get(position).getFeedbackReplyDateTime()));
        holder.message.setText(Html.fromHtml(feedbackChating.get(position).getFeedbackExpertReply()));

    }

    @Override
    public int getItemCount() {
        return feedbackChating.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView messageFrom,date,message;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            messageFrom = itemView.findViewById(R.id.messageFrom);
            date = itemView.findViewById(R.id.date);
            message = itemView.findViewById(R.id.message);

        }
    }
}
