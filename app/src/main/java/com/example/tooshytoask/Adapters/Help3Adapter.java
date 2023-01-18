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

import com.example.tooshytoask.Models.Help2Item;
import com.example.tooshytoask.Models.Help3Item;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class Help3Adapter extends RecyclerView.Adapter<Help3Adapter.ViewHolder> {
    ArrayList<Help3Item> help3Item;
    Context context;

    public Help3Adapter(ArrayList<Help3Item> help3Item, Context context) {
        this.help3Item = help3Item;
        this.context = context;
    }


    @NonNull
    @Override
    public Help3Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.help2_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Help3Adapter.ViewHolder holder, int position) {
        holder.arrow_img.setImageDrawable(ContextCompat.getDrawable(context, help3Item.get(position).getArrow_img()));
        holder.question.setText(help3Item.get(position).getQuestion());

    }

    @Override
    public int getItemCount() {
        return help3Item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView arrow_img;
        TextView question;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            arrow_img = itemView.findViewById(R.id.arrow_img);
            question = itemView.findViewById(R.id.question);
        }
    }
}
