package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Models.AvatarResponse;
import com.example.tooshytoask.Models.avatarList;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.OnClickListner;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    Context context;
    ArrayList<avatarList>avatarLists;
    OnClickListner onclicklistener;

    public ProfileAdapter(ArrayList<avatarList>avatarLists, OnClickListner onclicklistener, Context context) {
        this.avatarLists = avatarLists;
        this.onclicklistener = onclicklistener;
        this.context = context;
    }

    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_item_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
        holder.img.setImageDrawable(ContextCompat.getDrawable(context,avatarLists.get(position).getEncimg()));
    }

    @Override
    public int getItemCount() {
        return avatarLists.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);

        }

            /*public void ProfileItems(final AvatarResponse profileItems, int position){
                img.setImageResource(profileItems.getImg());

                if (profileItems.isSelected){
                    img.setBackgroundResource(R.drawable.circle_active_background);
                }else {
                    img.setBackgroundResource(R.drawable.circle_inactive_background);
                }

                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (profileItems.isSelected) {
                            img.setBackgroundResource(R.drawable.circle_inactive_background);
                            profileItems.setSelected(false);
                            notifyDataSetChanged();
                            onclicklistener.onClickData(position,1);


                        }else {
                            img.setBackgroundResource(R.drawable.circle_active_background);
                            profileItems.setSelected(true);
                            notifyDataSetChanged();
                            onclicklistener.onClickData(position,1);

                        }

                    }
                });

            }*/
    }

}
