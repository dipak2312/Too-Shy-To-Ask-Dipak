package com.neuronimbus.metropolis.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.Models.avatarList;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.OnClickListner;

import java.util.ArrayList;

public class NGOProfileAdapter extends RecyclerView.Adapter<NGOProfileAdapter.ViewHolder> {
    Context context;
    ArrayList<String>avatarList;
    OnClickListner onclicklistener;

   public int singleitem_selection_position = -1;

    public NGOProfileAdapter(ArrayList<String>avatarList, OnClickListner onclicklistener, Context context) {
        this.avatarList = avatarList;
        this.onclicklistener = onclicklistener;
        this.context = context;
    }

    @NonNull
    @Override
    public NGOProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_item_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NGOProfileAdapter.ViewHolder holder, int position) {
        /*String img=avatarList.get(position).getEncimg();
        byte[] imageByteArray = Base64.decode(avatarList.get(position).getEncimg(), Base64.DEFAULT);
        InputStream is = new ByteArrayInputStream(imageByteArray);
        Bitmap bmp = BitmapFactory.decodeStream(is);
        holder.img.setImageBitmap(bmp);*/
        //Glide.with(context).load(avatarList.get(position).getUrl()).into(holder.img);


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

                    //onclicklistener.onClickData(getAdapterPosition(),avatarList.get(getAbsoluteAdapterPosition()).getEncimg());


                    setSingleSelection(getAbsoluteAdapterPosition());

                }
            });

        }

        }
    public void setSingleSelection(int adapterPosition){
        if (adapterPosition == RecyclerView.NO_POSITION) return;

        singleitem_selection_position = adapterPosition;
        notifyDataSetChanged();
    }
}
