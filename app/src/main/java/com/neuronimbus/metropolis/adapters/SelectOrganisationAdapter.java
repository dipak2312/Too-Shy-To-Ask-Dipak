package com.neuronimbus.metropolis.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.neuronimbus.metropolis.Models.HealthIssuseList;
import com.neuronimbus.metropolis.Models.ProjectList;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.OnClickListner;

import java.util.ArrayList;

public class SelectOrganisationAdapter extends RecyclerView.Adapter<SelectOrganisationAdapter.ViewHolder> {
    Context context;
    OnClickListner onclicklistener;
    ArrayList<com.neuronimbus.metropolis.Models.ProjectList> projectList;

    public SelectOrganisationAdapter(Context context, OnClickListner onclicklistener, ArrayList<ProjectList> projectList) {
        this.context = context;
        this.onclicklistener = onclicklistener;
        this.projectList = projectList;
    }

    @NonNull
    @Override
    public SelectOrganisationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.health_issues,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectOrganisationAdapter.ViewHolder holder, int position) {
        //holder.health_btn.setText(healthIssues.get(position).getHealth_btn());
        holder.HealthIssues(projectList.get(position),position);
        //holder.health_btn.setText(healthIssuseList.get(position).getHealth_title());
        //holder.health_btn.setText(projectList.get(position).getProjectName());

    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView health_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            health_btn = itemView.findViewById(R.id.health_btn);
        }

        public void HealthIssues(final ProjectList projectList, int position){
            health_btn.setText(projectList.getProjectName());

            if (projectList.isSelected){
                health_btn.setBackgroundResource(R.drawable.health_active);
                health_btn.setTextColor(ContextCompat.getColor(context, R.color.white));
            }else {
                health_btn.setBackgroundResource(R.drawable.health_inactive);
                health_btn.setTextColor(ContextCompat.getColor(context, R.color.black));
            }

            health_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (projectList.isSelected) {
                        health_btn.setBackgroundResource(R.drawable.health_inactive);
                        projectList.setSelected(false);
                        notifyDataSetChanged();
                        onclicklistener.onClickData(position,projectList.getProjectId());


                    }else {
                        health_btn.setBackgroundResource(R.drawable.health_active);
                        projectList.setSelected(true);
                        notifyDataSetChanged();
                        onclicklistener.onClickData(position,projectList.getProjectId());

                    }

                }
            });
        }
    }
}
