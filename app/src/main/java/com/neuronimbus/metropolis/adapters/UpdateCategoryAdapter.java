package com.neuronimbus.metropolis.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neuronimbus.metropolis.Models.CategoryItem;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.ClickListener;
import com.neuronimbus.metropolis.Utils.OnClickListner;

import java.util.ArrayList;

public class UpdateCategoryAdapter extends RecyclerView.Adapter<UpdateCategoryAdapter.ViewHolder> {
    ArrayList<CategoryItem> categoryItem;
    OnClickListner onclicklistener;
    ClickListener clickListener;

    public UpdateCategoryAdapter(ArrayList<CategoryItem> categoryItem, OnClickListner onclicklistener, ClickListener clickListener){
        this.categoryItem = categoryItem;
        this.onclicklistener=onclicklistener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public UpdateCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpdateCategoryAdapter.ViewHolder holder, int position) {
        holder.CategoryItem(categoryItem.get(position),position);

    }

    @Override
    public int getItemCount() {
        return categoryItem.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cate_img;
        TextView category_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cate_img = itemView.findViewById(R.id.cate_img);
            category_title = itemView.findViewById(R.id.category_title);



        }

        public void CategoryItem(final CategoryItem categoryItem,int position){
            cate_img.setImageResource(categoryItem.getCate_img());
            category_title.setText(categoryItem.getCategory_title());

            if (categoryItem.isSelected){
                cate_img.setBackgroundResource(R.drawable.circle_active_background);
            }else {
                cate_img.setBackgroundResource(R.drawable.circle_inactive_background);
            }

            cate_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   if (categoryItem.isSelected) {
                       cate_img.setBackgroundResource(R.drawable.circle_inactive_background);
                       categoryItem.setSelected(false);
                       notifyDataSetChanged();
                       //onclicklistener.onClickData(position,1);


                   }else {
                       cate_img.setBackgroundResource(R.drawable.circle_active_background);
                       categoryItem.setSelected(true);
                       notifyDataSetChanged();
                       //onclicklistener.onClickData(position,1);

                   }

                }
            });
        }
    }
}
