package com.example.tooshytoask.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Models.Help.helpsubcategory;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class Help2Adapter extends RecyclerView.Adapter<Help2Adapter.ViewHolder> {
    ArrayList<helpsubcategory>helpsubcategory;
    HelpContentAdapter adapter;
    Context context;

    public Help2Adapter(ArrayList<helpsubcategory> helpsubcategory, Context context) {
        this.helpsubcategory = helpsubcategory;
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
        holder.title.setText(helpsubcategory.get(position).getTitle());
        adapter=new HelpContentAdapter(context,helpsubcategory.get(position).getContent());
        holder.recy_que.setAdapter(adapter);


    }

    @Override
    public int getItemCount() {
        return helpsubcategory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        RecyclerView recy_que;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            recy_que = itemView.findViewById(R.id.recy_que);

            if(recy_que !=null) {
                LinearLayoutManager lm = new LinearLayoutManager(context);
                lm.setOrientation(RecyclerView.VERTICAL);
                recy_que.setLayoutManager(lm);
            }

        }
    }
}
