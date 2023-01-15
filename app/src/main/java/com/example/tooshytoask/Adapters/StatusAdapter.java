package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Activity.Story.StoryActivity;
import com.example.tooshytoask.Models.StatusItem;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder>{
    ArrayList<StatusItem> statusItems;
    Context context;

    public StatusAdapter(Context context, ArrayList<StatusItem> statusItems) {
        this.statusItems = statusItems;
        this.context = context;
    }

    public StatusAdapter() {

    }

    @NonNull
    @Override
    public StatusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.status_item_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatusAdapter.ViewHolder holder, int position) {
        holder.status_img.setImageDrawable(ContextCompat.getDrawable(context,statusItems.get(position).getStatus_img()));
        holder.status_title.setText(statusItems.get(position).getStatus_title());
    }

    @Override
    public int getItemCount() {
        return statusItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView status_img;
        TextView status_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            status_img = itemView.findViewById(R.id.status_img);
            status_title = itemView.findViewById(R.id.status_title);

            status_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, StoryActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
