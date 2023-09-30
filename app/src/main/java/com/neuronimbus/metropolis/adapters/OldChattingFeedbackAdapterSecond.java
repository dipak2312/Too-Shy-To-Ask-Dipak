package com.neuronimbus.metropolis.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neuronimbus.metropolis.Models.FeedbackChating;
import com.neuronimbus.metropolis.Models.HelpArticleFeedbackChating;
import com.neuronimbus.metropolis.R;

import java.util.ArrayList;

public class OldChattingFeedbackAdapterSecond extends RecyclerView.Adapter<OldChattingFeedbackAdapterSecond.ViewHolder>{
    Context context;
    ArrayList<com.neuronimbus.metropolis.Models.HelpArticleFeedbackChating>helpArticleFeedbackChating;

    public OldChattingFeedbackAdapterSecond(Context context, ArrayList<HelpArticleFeedbackChating> helpArticleFeedbackChating) {
        this.context = context;
        this.helpArticleFeedbackChating = helpArticleFeedbackChating;
    }

    @NonNull
    @Override
    public OldChattingFeedbackAdapterSecond.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_old_feedback_chatting_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OldChattingFeedbackAdapterSecond.ViewHolder holder, int position) {
        holder.messageFrom.setText(Html.fromHtml(helpArticleFeedbackChating.get(position).getHelpArticleLabel()));
        holder.date.setText(Html.fromHtml(helpArticleFeedbackChating.get(position).getReplyDateTime()));
        holder.message.setText(Html.fromHtml(helpArticleFeedbackChating.get(position).getHelpArticleFeedbackReply()));

    }

    @Override
    public int getItemCount() {
        return helpArticleFeedbackChating.size();
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
