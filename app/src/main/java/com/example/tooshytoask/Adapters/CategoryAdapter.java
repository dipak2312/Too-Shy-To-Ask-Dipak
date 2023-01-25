package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Models.InformationStorehouseList;
import com.example.tooshytoask.Utils.ClickListener;
import com.example.tooshytoask.Models.CategoryItem;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.OnClickListner;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<InformationStorehouseList>informationStorehouseLists;
    OnClickListner onclicklistener;
    ClickListener clickListener;
    Context context;

    public CategoryAdapter(ArrayList<InformationStorehouseList>informationStorehouseLists,OnClickListner onclicklistener, ClickListener clickListener){
        this.informationStorehouseLists = informationStorehouseLists;
        this.onclicklistener=onclicklistener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
       holder.category_title.setText(informationStorehouseLists.get(position).getTitle());
       holder.cate_img.setImageDrawable(ContextCompat.getDrawable(context,informationStorehouseLists.get(position).getImg()));
    }

    @Override
    public int getItemCount() {
        return informationStorehouseLists.size();
    }

    /*public List<CategoryItem> getSelectedCategoryItems(){
        List<CategoryItem> selectedCategoryItems = new ArrayList<>();
        for (CategoryItem categoryItem : categoryItem){
            if (categoryItem.isSelected){
                selectedCategoryItems.add(categoryItem);
            }
        }
        return selectedCategoryItems;
    }*/


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cate_img;
        TextView category_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cate_img = itemView.findViewById(R.id.cate_img);
            category_title = itemView.findViewById(R.id.category_title);



        }

        /*public void InformationStorehouseList(final InformationStorehouseList informationStorehouseLists,int position){
            //cate_img.setImageDrawable(ContextCompat.getDrawable(informationStorehouseLists.get(position).getSlug()));
            category_title.setText(informationStorehouseLists.getTitle());


            if (informationStorehouseLists.isSelected){
                cate_img.setBackgroundResource(R.drawable.circle_active_background);
            }else {
                cate_img.setBackgroundResource(R.drawable.circle_inactive_background);
            }

            cate_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   if (informationStorehouseLists.isSelected) {
                       cate_img.setBackgroundResource(R.drawable.circle_inactive_background);
                       informationStorehouseLists.setSelected(false);
                       notifyDataSetChanged();
                       onclicklistener.onClickData(position,1);


                   }else {
                       cate_img.setBackgroundResource(R.drawable.circle_active_background);
                       informationStorehouseLists.setSelected(true);
                       notifyDataSetChanged();
                       onclicklistener.onClickData(position,1);

                   }

                }
            });
        }*/
    }
}
