package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.Models.TopTenList;
import com.example.tooshytoask.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class LeaderboardSecondAdapter extends RecyclerView.Adapter<LeaderboardSecondAdapter.leaderboardviewholder> {

    Context context;
    ArrayList<TopTenList>secondList;

    public LeaderboardSecondAdapter(Context context, ArrayList<TopTenList> secondList) {
        this.context = context;
        this.secondList = secondList;
    }

    @NonNull
    @Override
    public leaderboardviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboard_second_view,parent,false);
        leaderboardviewholder holder=new leaderboardviewholder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull leaderboardviewholder holder, int position) {


        holder.txt_no.setText(secondList.get(position).getId());
        holder.txt_time.setText(secondList.get(position).getGame_time());
        holder.txt_user_name.setText(secondList.get(position).getName());
        Glide.with(context).load(secondList.get(position).getImage()).placeholder(R.drawable.demo).into(holder.img_user);


        if(position==secondList.size()-1)
        {
            holder.view1.setVisibility(View.GONE);
        }
        else
        {
            holder.view1.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return secondList.size();
    }

    public class leaderboardviewholder extends RecyclerView.ViewHolder
    {
        View view1;
        TextView txt_no,txt_user_name,txt_time;
        CircleImageView img_user;
        public leaderboardviewholder(@NonNull View itemView) {
            super(itemView);

            img_user=(CircleImageView)itemView.findViewById(R.id.img_user);
            txt_no=(TextView)itemView.findViewById(R.id.txt_no);
            txt_user_name=(TextView)itemView.findViewById(R.id.txt_user_name);
            txt_time=(TextView)itemView.findViewById(R.id.txt_time);
            view1=(View)itemView.findViewById(R.id.view1);
        }
    }
}
