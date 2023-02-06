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
        String img=avatarList.get(position).getEncimg();
        byte[] imageByteArray = Base64.decode(avatarList.get(position).getEncimg(), Base64.DEFAULT);
       // Bitmap decodedByte = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
        InputStream is = new ByteArrayInputStream(imageByteArray);
        Bitmap bmp = BitmapFactory.decodeStream(is);
        holder.img.setImageBitmap(bmp);
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
