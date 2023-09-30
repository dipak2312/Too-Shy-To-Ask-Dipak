package com.neuronimbus.metropolis.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neuronimbus.metropolis.Models.RecentFeedback;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.activity.Blogs.DetailBlogActivity;
import com.neuronimbus.metropolis.activity.Feedback.OldFeedbackChattingActivity;
import com.neuronimbus.metropolis.activity.Feedback.RecentFeedbackChattingActivity;

import java.util.ArrayList;

public class RecentFeedbackListAdapter extends RecyclerView.Adapter<RecentFeedbackListAdapter.ViewHolder>{
    ArrayList<RecentFeedback> recentFeedback;
    Context context;

    public RecentFeedbackListAdapter(ArrayList<RecentFeedback> recentFeedback, Context context) {
        this.recentFeedback = recentFeedback;
        this.context = context;
    }


    @NonNull
    @Override
    public RecentFeedbackListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_recent_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentFeedbackListAdapter.ViewHolder holder, int position) {
        holder.title.setText(recentFeedback.get(position).getAssistance_type());
        holder.question.setText(recentFeedback.get(position).getFeedback_desc());

        holder.recentRelLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putString("feedback_id",recentFeedback.get(position).getFeedback_id());
                Intent intent = new Intent(context, RecentFeedbackChattingActivity.class);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recentFeedback.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, question, status, date;
        RelativeLayout recentRelLay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            question = itemView.findViewById(R.id.question);
            status = itemView.findViewById(R.id.status);
            recentRelLay = itemView.findViewById(R.id.recentRelLay);
            date = itemView.findViewById(R.id.date);
            date.setVisibility(View.GONE);
        }
    }
}
