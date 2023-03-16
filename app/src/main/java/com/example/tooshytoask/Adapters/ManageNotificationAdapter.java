package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Interface.onManageNotification;
import com.example.tooshytoask.Models.dataNotification;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.OnClickListner;

import java.util.ArrayList;

public class ManageNotificationAdapter extends RecyclerView.Adapter<ManageNotificationAdapter.ViewHolder> {
    Context context;
    ArrayList<dataNotification> dataNotification;
    onManageNotification onManageNotification;

    public ManageNotificationAdapter(Context context, ArrayList<com.example.tooshytoask.Models.dataNotification> dataNotification, com.example.tooshytoask.Interface.onManageNotification onManageNotification) {
        this.context = context;
        this.dataNotification = dataNotification;
        this.onManageNotification = onManageNotification;
    }


    @NonNull
    @Override
    public ManageNotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manage_notification_item_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManageNotificationAdapter.ViewHolder holder, int position) {
        holder.DataNotification(dataNotification.get(position),position);

        if (dataNotification.get(0).status){
            holder.notification_on_off.setChecked(true);
            holder.on_off_status.setText(R.string.on);
        }
        else {
            holder.notification_on_off.setChecked(false);
            holder.on_off_status.setText(R.string.off);
        }

    }

    @Override
    public int getItemCount() {
        return dataNotification.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView push_notification,on_off_status;
        SwitchCompat notification_on_off;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            notification_on_off = itemView.findViewById(R.id.notification_on_off);
            push_notification = itemView.findViewById(R.id.push_notification);
            on_off_status = itemView.findViewById(R.id.on_off_status);
        }

        public void DataNotification(final dataNotification dataNotification, int position){
            push_notification.setText(dataNotification.getModule_name());

            if (dataNotification.status){
                on_off_status.setText(R.string.on);

            }else {

                on_off_status.setText(R.string.off);
            }

            notification_on_off.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                    if (dataNotification.status){
                        dataNotification.setStatus(true);
                        on_off_status.setText(R.string.off);
                        onManageNotification.onManageNotificationClick(position, dataNotification.getManage_id());
                    }
                     else {
                        dataNotification.setStatus(false);
                        on_off_status.setText(R.string.on);
                        onManageNotification.onManageNotificationClick(position, dataNotification.getManage_id());

                    }

                }
            });
        }
    }
}
