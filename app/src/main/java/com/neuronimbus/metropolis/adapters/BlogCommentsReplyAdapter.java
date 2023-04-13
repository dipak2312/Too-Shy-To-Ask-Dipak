package com.neuronimbus.metropolis.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neuronimbus.metropolis.Helper.DateUtil;
import com.neuronimbus.metropolis.Helper.IDateTimeFormat;
import com.neuronimbus.metropolis.R;
import java.util.ArrayList;

public class BlogCommentsReplyAdapter extends RecyclerView.Adapter<BlogCommentsReplyAdapter.allcommentViewHolder> {

    Context context;
    ArrayList<com.neuronimbus.metropolis.Models.child_comment>child_comment;

    public BlogCommentsReplyAdapter(Context context, ArrayList<com.neuronimbus.metropolis.Models.child_comment> child_comment) {
        this.context = context;
        this.child_comment = child_comment;
    }


    @NonNull
    @Override
    public allcommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        allcommentViewHolder holder=new allcommentViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull allcommentViewHolder holder, int position) {

        holder.txt_comment.setText(Html.fromHtml(child_comment.get(position).getComment_content()).toString());
        holder.txt_name.setText(child_comment.get(position).getComment_author());
        holder.txt_date.setText(new DateUtil().getStringDateInDisplayFormat(child_comment.get(position).getComment_date(), IDateTimeFormat.DATE_FORMAT_YYYY_MM_DD, IDateTimeFormat.DATE_FORMAT_MMM_DD_YYYY));

    }

    @Override
    public int getItemCount() {
        return child_comment.size();
    }

    public void setData(ArrayList<com.neuronimbus.metropolis.Models.child_comment>child_comment){
        this.child_comment=child_comment;
        notifyDataSetChanged();
    }

    public class  allcommentViewHolder extends RecyclerView.ViewHolder
    {
        TextView txt_name,txt_date,txt_comment;
        public allcommentViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name=(TextView)itemView.findViewById(R.id.txt_name);
            txt_date=(TextView)itemView.findViewById(R.id.txt_date);
            txt_comment=(TextView)itemView.findViewById(R.id.txt_comment);

        }
    }
}
