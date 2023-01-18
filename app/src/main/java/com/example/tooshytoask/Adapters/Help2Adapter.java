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
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class Help2Adapter extends RecyclerView.Adapter<Help2Adapter.ViewHolder> {
    ArrayList<Help2Item> help2Item;
    Context context;

    public Help2Adapter(ArrayList<Help2Item> help2Item, Context context) {
        this.help2Item = help2Item;
        this.context = context;
    }


    @NonNull
    @Override
    public Help2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.help2_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Help2Adapter.ViewHolder holder, int position) {
        holder.arrow_img.setImageDrawable(ContextCompat.getDrawable(context, help2Item.get(position).getArrow_img()));
        holder.question.setText(help2Item.get(position).getQuestion());

    }

    @Override
    public int getItemCount() {
        return help2Item.size();
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
