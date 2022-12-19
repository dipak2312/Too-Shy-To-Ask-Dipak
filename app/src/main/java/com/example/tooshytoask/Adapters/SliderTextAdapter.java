package com.example.tooshytoask.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tooshytoask.Models.SliderItem;
import com.example.tooshytoask.Models.SliderText;
import com.example.tooshytoask.R;

import java.util.List;

public class SliderTextAdapter extends RecyclerView.Adapter<SliderTextAdapter.ViewHolder>{
    List<SliderText> sliderText;
    ViewPager2 viewPager3;

    public SliderTextAdapter (List<SliderText> sliderText, ViewPager2 viewPager3){
        this.sliderText = sliderText;
        this.viewPager3 = viewPager3;
    }

    @NonNull
    @Override
    public SliderTextAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_item_content,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderTextAdapter.ViewHolder holder, int position) {
        holder.setView_pager_title(sliderText.get(position));
        holder.setView_pager_dec(sliderText.get(position));
    }

    @Override
    public int getItemCount() {
        return sliderText.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView view_pager_title, view_pager_dec;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            view_pager_title = itemView.findViewById(R.id.view_pager_title);
            view_pager_dec = itemView.findViewById(R.id.view_pager_dec);
        }

        void setView_pager_title(SliderText sliderText){
            view_pager_title.setText(sliderText.getView_pager_title());

        }
        void setView_pager_dec(SliderText sliderText){
            view_pager_dec.setText(sliderText.getView_pager_dec());

        }
    }
}
