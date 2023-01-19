package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Models.ProfileItems;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.ClickListener;
import com.example.tooshytoask.Utils.OnClickListner;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    Context context;
    ArrayList<ProfileItems> profileItems;
    OnClickListner onclicklistener;

    public ProfileAdapter(ArrayList<ProfileItems> profileItems, OnClickListner onclicklistener,  Context context) {
        this.profileItems = profileItems;
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
        //holder.img.setImageDrawable(ContextCompat.getDrawable(context,profileItems.get(position).getImg()));
        holder.ProfileItems(profileItems.get(position),position);
    }

    @Override
    public int getItemCount() {
        return profileItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);

        }

            public void ProfileItems(final ProfileItems profileItems, int position){
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

            }
    }

}