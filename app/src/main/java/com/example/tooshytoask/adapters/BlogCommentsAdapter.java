package com.example.tooshytoask.adapters;

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

import com.example.tooshytoask.Helper.DateUtil;
import com.example.tooshytoask.Helper.IDateTimeFormat;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class BlogCommentsAdapter extends RecyclerView.Adapter<BlogCommentsAdapter.allcommentViewHolder> {

    Context context;
    ArrayList<com.example.tooshytoask.Models.comments> comments;
    onReplyClicked onReplyClicked;

    public BlogCommentsAdapter(Context context, ArrayList<com.example.tooshytoask.Models.comments> comments, BlogCommentsAdapter.onReplyClicked onReplyClicked) {
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

   /* public void setData(List<CommentsItem> commentlist){
        this.commentData=commentlist;
        notifyDataSetChanged();
    }*/

    public class  allcommentViewHolder extends RecyclerView.ViewHolder
    {
        TextView txt_name,txt_date,txt_comment,txt_reply;
        RecyclerView commentReply;
        public allcommentViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name=itemView.findViewById(R.id.txt_name);
            txt_reply=itemView.findViewById(R.id.txt_reply);
            txt_date=itemView.findViewById(R.id.txt_date);
            txt_comment=itemView.findViewById(R.id.txt_comment);
            commentReply= itemView.findViewById(R.id.commentReply);

        }
    }

    public interface onReplyClicked{
        public void onReplyButtonClick(int position, String comment_id);
    }
}
