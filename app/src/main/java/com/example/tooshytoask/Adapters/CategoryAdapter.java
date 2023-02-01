package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.Models.InformationStorehouseList;
import com.example.tooshytoask.Utils.ClickListener;
import com.example.tooshytoask.Models.CategoryItem;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.OnClickListner;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<InformationStorehouseList>InformationStorehouseList;
    OnClickListner onclicklistener;
    Context context;

    public CategoryAdapter(ArrayList<InformationStorehouseList>InformationStorehouseList, Context context, OnClickListner onclicklistener){
        this.InformationStorehouseList = InformationStorehouseList;
        this.context = context;
        this.onclicklistener = onclicklistener;

    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
       holder.category_title.setText(InformationStorehouseList.get(position).getTitle());
       Glide.with(context).load(InformationStorehouseList.get(position).getImg()).into(holder.cate_img);
       //holder.InformationStorehouseList(InformationStorehouseList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return InformationStorehouseList.size();
        /*informationStorehouseLists == null ? 0 :*/
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cate_img;
        TextView category_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cate_img = itemView.findViewById(R.id.cate_img);
            category_title = itemView.findViewById(R.id.category_title);

        }

        /*public void InformationStorehouseList(final InformationStorehouseList InformationStorehouseList,int position){
            category_title.setText(InformationStorehouseList.getTitle());
            Glide.with(context).load(InformationStorehouseList.getImg()).into(cate_img);


            if (InformationStorehouseList.isSelected){
                cate_img.setBackgroundResource(R.drawable.circle_active_background);
            }else {
                cate_img.setBackgroundResource(R.drawable.circle_inactive_background);
            }

            cate_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   if (InformationStorehouseList.isSelected) {
                       cate_img.setBackgroundResource(R.drawable.circle_inactive_background);
                       InformationStorehouseList.setSelected(false);
                       notifyDataSetChanged();
                       onclicklistener.onClickData(position,1);


                   }else {
                       cate_img.setBackgroundResource(R.drawable.circle_active_background);
                       InformationStorehouseList.setSelected(true);
                       notifyDataSetChanged();
                       onclicklistener.onClickData(position,1);

                   }

                }
            });
        }*/
    }

}