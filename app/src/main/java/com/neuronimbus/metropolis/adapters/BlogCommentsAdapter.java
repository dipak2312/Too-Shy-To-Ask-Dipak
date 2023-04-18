package com.neuronimbus.metropolis.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.Helper.DateUtil;
import com.neuronimbus.metropolis.Helper.IDateTimeFormat;
import com.neuronimbus.metropolis.Models.comments;
import com.neuronimbus.metropolis.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class BlogCommentsAdapter extends RecyclerView.Adapter<BlogCommentsAdapter.allcommentViewHolder> {

    Context context;
    ArrayList<com.neuronimbus.metropolis.Models.comments> comments;
    onReplyClicked onReplyClicked;

    public BlogCommentsAdapter(Context context, ArrayList<com.neuronimbus.metropolis.Models.comments> comments, BlogCommentsAdapter.onReplyClicked onReplyClicked) {
        this.context = context;
        this.comments = comments;
        this.onReplyClicked = onReplyClicked;
    }


    @NonNull
    @Override
    public allcommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_comments_view,parent,false);
        allcommentViewHolder holder=new allcommentViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull allcommentViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txt_comment.setText(Html.fromHtml(comments.get(position).getParent_comment().getComment_content()).toString());
        holder.txt_name.setText(comments.get(position).getParent_comment().getComment_author());
        holder.txt_date.setText(new DateUtil().getStringDateInDisplayFormat(comments.get(position).getParent_comment().getComment_date(), IDateTimeFormat.DATE_FORMAT_YYYY_MM_DD, IDateTimeFormat.DATE_FORMAT_MMM_DD_YYYY));
        Glide.with(context).load(comments.get(position).getParent_comment().getProfile_pic()).placeholder(R.drawable.demo).into(holder.avatar_pic);

        holder.txt_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReplyClicked.onReplyButtonClick(position,comments.get(position).getParent_comment().getComment_ID());
            }
        });

        LinearLayoutManager lm = new LinearLayoutManager(context);
        lm.setOrientation(RecyclerView.VERTICAL);
        holder.commentReply.setLayoutManager(lm);
        BlogCommentsReplyAdapter adapter = new BlogCommentsReplyAdapter(context,comments.get(position).getChild_comment());
        holder.commentReply.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return comments!=null?comments.size():0;
    }

    public class  allcommentViewHolder extends RecyclerView.ViewHolder
    {
        TextView txt_name,txt_date,txt_comment,txt_reply;
        RecyclerView commentReply;
        CircleImageView avatar_pic;
        public allcommentViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name=itemView.findViewById(R.id.txt_name);
            txt_reply=itemView.findViewById(R.id.txt_reply);
            txt_date=itemView.findViewById(R.id.txt_date);
            txt_comment=itemView.findViewById(R.id.txt_comment);
            commentReply= itemView.findViewById(R.id.commentReply);
            avatar_pic= itemView.findViewById(R.id.avatar_pic);

        }
    }

    public interface onReplyClicked{
        public void onReplyButtonClick(int position, String comment_id);
    }
}
