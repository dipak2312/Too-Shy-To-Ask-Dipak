package com.example.tooshytoask.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Models.CategoryItem;
import com.example.tooshytoask.Models.HealthIssues;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class HealthAdapter extends RecyclerView.Adapter<HealthAdapter.ViewHolder> {
    ArrayList<HealthIssues> healthIssues;

    public HealthAdapter(ArrayList<HealthIssues> healthIssues){
        this.healthIssues = healthIssues;
    }
    @NonNull
    @Override
    public HealthAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.health_issues,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HealthAdapter.ViewHolder holder, int position) {
        holder.health_btn.setText(healthIssues.get(position).getHealth_btn());
        holder.health_btn2.setText(healthIssues.get(position).getHealth_btn());

    }

    @Override
    public int getItemCount() {
        return healthIssues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView health_btn, health_btn2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            health_btn = itemView.findViewById(R.id.health_btn);
            health_btn2 = itemView.findViewById(R.id.health_btn2);
        }
    }
}
