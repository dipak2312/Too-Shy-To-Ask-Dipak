package com.example.tooshytoask.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Interface.CategoryListener;
import com.example.tooshytoask.Interface.ClickListener;
import com.example.tooshytoask.Models.CategoryItem;
import com.example.tooshytoask.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<CategoryItem> categoryItems;
    CategoryListener categoryListener;

    public CategoryAdapter(ArrayList<CategoryItem> categoryItems){
        this.categoryItems = categoryItems;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.CategoryItem(categoryItems.get(position));

    }

    @Override
    public int getItemCount() {
        return categoryItems.size();
    }

    public List<CategoryItem> getSelectedCategoryItems(){
        List<CategoryItem> selectedCategoryItems = new ArrayList<>();
        for (CategoryItem categoryItem : categoryItems){
            if (categoryItem.isSelected){
                selectedCategoryItems.add(categoryItem);
            }
        }
        return selectedCategoryItems;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cate_img;
        TextView category_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cate_img = itemView.findViewById(R.id.cate_img);
            category_title = itemView.findViewById(R.id.category_title);



        }

        public void CategoryItem(final CategoryItem categoryItem){
            cate_img.setImageResource(categoryItem.getCate_img());

            /*if (categoryItem.isSelected){
                cate_img.setBackgroundResource(R.drawable.circle_active_background);
            }else {
                cate_img.setBackgroundResource(R.drawable.circle_inactive_background);
            }*/

            cate_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   if (categoryItem.isSelected) {
                       cate_img.setBackgroundResource(R.drawable.circle_active_background);
                       //categoryListener.onSelectedCategory(false);
                       //categoryItem.isSelected = true;


                       if (getSelectedCategoryItems().size() == 0) {

                       } else {
                           cate_img.setBackgroundResource(R.drawable.circle_inactive_background);
                           //cate_img.setImageResource(categoryItem.getCate_img());
                           //categoryItem.disSelected = true;
                           //categoryListener.onSelectedCategory(true);
                       }

                   }

                }
            });
        }
    }
}
