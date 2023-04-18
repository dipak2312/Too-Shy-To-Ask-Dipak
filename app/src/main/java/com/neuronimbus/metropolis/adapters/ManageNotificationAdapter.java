package com.neuronimbus.metropolis.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.neuronimbus.metropolis.Interface.onManageNotification;
import com.neuronimbus.metropolis.Models.dataNotification;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.OnClickListner;

import java.util.ArrayList;

public class ManageNotificationAdapter extends RecyclerView.Adapter<ManageNotificationAdapter.ViewHolder> {
    Context context;
    ArrayList<dataNotification> dataNotification;
    OnClickListner onclicklistener;

    public ManageNotificationAdapter(Context context, ArrayList<dataNotification> dataNotification,OnClickListner onclicklistener) {
        this.context = context;
        this.dataNotification = dataNotification;
        this.onclicklistener = onclicklistener;
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
                notification_on_off.setChecked(true);
                on_off_status.setText(R.string.on);

            }else {

                notification_on_off.setChecked(false);
                on_off_status.setText(R.string.off);
            }

            notification_on_off.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                    if (dataNotification.status){
                        on_off_status.setText(R.string.on);
                        dataNotification.setStatus(true);
                        //notifyDataSetChanged();
                        onclicklistener.onClickData(position, dataNotification.getManage_id());
                    }
                     else {
                        on_off_status.setText(R.string.off);
                        dataNotification.setStatus(false);
                       // notifyDataSetChanged();
                        onclicklistener.onClickData(position, dataNotification.getManage_id());
                    }

                }
            });
        }
    }
}
