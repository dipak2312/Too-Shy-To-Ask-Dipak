package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.Models.NotificationList;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{
    Context context;
    ArrayList<NotificationList>notificationLists;
    ClearNotification clearNotification;

    public NotificationAdapter(Context context, ArrayList<NotificationList> notificationLists, ClearNotification clearNotification) {
        this.context = context;
        this.notificationLists = notificationLists;
        this.clearNotification = clearNotification;
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {
        holder.notification_title.setText(notificationLists.get(position).getTitle());
        holder.notification_date.setText(notificationLists.get(position).getDatetime());
        holder.notification_desc.setText(notificationLists.get(position).getMessage());
        Glide.with(context).load(notificationLists.get(position).getImage()).into(holder.notification_img);

        holder.single_notification_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // clearNotification.ClearNotificationClick(position,notificationLists.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return notificationLists.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView notification_title,notification_date,notification_desc;
        ImageView single_notification_clear, notification_img;
        RelativeLayout notification_lay;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            notification_title = itemView.findViewById(R.id.notification_title);
            notification_date = itemView.findViewById(R.id.notification_date);
            notification_desc = itemView.findViewById(R.id.notification_desc);
            single_notification_clear = itemView.findViewById(R.id.single_notification_clear);
            notification_img = itemView.findViewById(R.id.notification_img);

            notification_lay = itemView.findViewById(R.id.notification_lay);


        }
    }

    public interface ClearNotification{
        public void ClearNotificationClick(int position, String single_notification_clear);

    }
}
