package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.Models.avatarList;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.OnClickListner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    Context context;
    ArrayList<avatarList>avatarList;
    OnClickListner onclicklistener;

    int singleitem_selection_position = -1;

    public ProfileAdapter(ArrayList<avatarList>avatarList, OnClickListner onclicklistener, Context context) {
        this.avatarList = avatarList;
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
//        String img=avatarList.get(position).getEncimg();
//        byte[] imageByteArray = Base64.decode(avatarList.get(position).getEncimg(), Base64.DEFAULT);
//       // Bitmap decodedByte = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
//        InputStream is = new ByteArrayInputStream(imageByteArray);
//        Bitmap bmp = BitmapFactory.decodeStream(is);
//        holder.img.setImageBitmap(bmp);
       // holder.ProfileItems(avatarList.get(position),position);
        Glide.with(context).load(avatarList.get(position).getUrl()).into(holder.img);
        if (singleitem_selection_position == position){
            holder.img.setBackgroundResource(R.drawable.circle_active_background);
        }
        else {
            holder.img.setBackgroundResource(R.drawable.circle_inactive_background);
        }
    }

    @Override
    public int getItemCount() {
        return avatarList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setSingleSelection(getAdapterPosition());
                }
            });

        }

        /*public void ProfileItems(final avatarList avatarList, int position){
            //img.setImageResource(avatarList.getEncimg());
            Glide.with(context).load(avatarList.getUrl()).into(img);

            if (avatarList.isSelected == -1){
                img.setBackgroundResource(R.drawable.circle_active_background);
            }
            else {
                img.setBackgroundResource(R.drawable.circle_inactive_background);
            }



            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (avatarList.isSelected) {
                        img.setBackgroundResource(R.drawable.circle_inactive_background);
                        avatarList.setSelected(-1);
                        notifyDataSetChanged();
                        onclicklistener.onClickData(position, avatarList.getUrl());


                    }else {
                        img.setBackgroundResource(R.drawable.circle_active_background);
                        avatarList.setSelected(-1);
                        notifyDataSetChanged();
                        onclicklistener.onClickData(position,avatarList.getUrl());

                    }

                }
            });*/

        }

            /*public void ProfileItems(final avatarList avatarList, int position){
                //img.setImageResource(avatarList.getEncimg());
                Glide.with(context).load(avatarList.getUrl()).into(img);

                if (avatarList.isSelected){
                    img.setBackgroundResource(R.drawable.circle_active_background);
                }else {
                    img.setBackgroundResource(R.drawable.circle_inactive_background);
                }



                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (avatarList.isSelected) {
                            img.setBackgroundResource(R.drawable.circle_inactive_background);
                            avatarList.setSelected(false);
                            notifyDataSetChanged();
                            onclicklistener.onClickData(position, avatarList.getUrl());


                        }else {
                            img.setBackgroundResource(R.drawable.circle_active_background);
                            avatarList.setSelected(true);
                            notifyDataSetChanged();
                            onclicklistener.onClickData(position,avatarList.getUrl());

                        }

                    }
                });

            }*/

    public void setSingleSelection(int adapterPosition){
        if (adapterPosition == RecyclerView.NO_POSITION) return;

        singleitem_selection_position = adapterPosition;
        notifyDataSetChanged();
    }

}
