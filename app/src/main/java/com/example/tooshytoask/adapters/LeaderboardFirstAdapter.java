package com.example.tooshytoask.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.Models.TopThreeList;
import com.example.tooshytoask.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class LeaderboardFirstAdapter extends RecyclerView.Adapter<LeaderboardFirstAdapter.leaderboardViewHolder> {

    Context context;
    ArrayList<TopThreeList> firsrList;

    public LeaderboardFirstAdapter(Context context, ArrayList<TopThreeList> firsrList) {
        this.context = context;
        this.firsrList = firsrList;
    }

    @NonNull
    @Override
    public leaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboard_first_view,parent,false);
        leaderboardViewHolder holder=new leaderboardViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull leaderboardViewHolder holder, int position) {

        holder.txt_name.setText(firsrList.get(position).getName());
        holder.txt_sec.setText(firsrList.get(position).getGame_time());
        Glide.with(context).load(firsrList.get(position).getImage()).placeholder(R.drawable.demo).into(holder.img_user_profile);

        if(firsrList.get(position).getId().equals("1"))
        {
            holder.img_rank.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.rank1));
        }
        if(firsrList.get(position).getId().equals("2"))
        {
            holder.img_rank.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.rank2));
        }
        if(firsrList.get(position).getId().equals("3"))
        {
            holder.img_rank.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.rank3));
        }

        if(position==firsrList.size()-1)
        {
            holder.view.setVisibility(View.GONE);
        }
        else
        {
            holder.view.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return firsrList.size();
    }

    public class leaderboardViewHolder extends RecyclerView.ViewHolder
    {
        View view;
        CircleImageView img_user_profile;
        TextView txt_name,txt_sec;
        ImageView img_rank;
        public leaderboardViewHolder(@NonNull View itemView)
        {
            super(itemView);

            view=(View)itemView.findViewById(R.id.view);
            img_user_profile=(CircleImageView)itemView.findViewById(R.id.img_user_profile);
            txt_name=(TextView)itemView.findViewById(R.id.txt_name);
            txt_sec=(TextView)itemView.findViewById(R.id.txt_sec);
            img_rank=(ImageView)itemView.findViewById(R.id.img_rank);
        }
    }
}
