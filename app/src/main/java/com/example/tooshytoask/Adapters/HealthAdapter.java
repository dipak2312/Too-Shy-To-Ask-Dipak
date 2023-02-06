package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.Models.HealthIssuseList;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.OnClickListner;

import java.util.ArrayList;

public class HealthAdapter extends RecyclerView.Adapter<HealthAdapter.ViewHolder> {
    Context context;
    OnClickListner onclicklistener;
    ArrayList<HealthIssuseList>healthIssuseList;

    public HealthAdapter(ArrayList<HealthIssuseList> healthIssuseList, OnClickListner onclicklistener, Context context){
        this.healthIssuseList = healthIssuseList;
        this.onclicklistener = onclicklistener;
        this.context = context;
    }
    @NonNull
    @Override
    public HealthAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.health_issues,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HealthAdapter.ViewHolder holder, int position) {
        //holder.health_btn.setText(healthIssues.get(position).getHealth_btn());
        holder.HealthIssues(healthIssuseList.get(position),position);
        //holder.health_btn.setText(healthIssuseList.get(position).getHealth_title());

    }

    @Override
    public int getItemCount() {
        return healthIssuseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView health_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            health_btn = itemView.findViewById(R.id.health_btn);
        }

        public void HealthIssues(final HealthIssuseList healthIssuseList, int position){
            health_btn.setText(healthIssuseList.getHealth_title());

            if (healthIssuseList.isSelected){
                health_btn.setBackgroundResource(R.drawable.health_active);
                health_btn.setTextColor(ContextCompat.getColor(context, R.color.white));
            }else {
                health_btn.setBackgroundResource(R.drawable.health_inactive);
                health_btn.setTextColor(ContextCompat.getColor(context, R.color.black));
            }

            health_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (healthIssuseList.isSelected) {
                        health_btn.setBackgroundResource(R.drawable.health_inactive);
                        //health_btn.setTextColor(ContextCompat.getColor(context, R.color.black));
                        healthIssuseList.setSelected(false);
                        notifyDataSetChanged();
                        onclicklistener.onClickData(position,"1");


                    }else {
                        health_btn.setBackgroundResource(R.drawable.health_active);
                        //health_btn.setTextColor(ContextCompat.getColor(context, R.color.purple));
                        healthIssuseList.setSelected(true);
                        notifyDataSetChanged();
                        onclicklistener.onClickData(position,"1");

                    }

                }
            });
        }
    }
}
