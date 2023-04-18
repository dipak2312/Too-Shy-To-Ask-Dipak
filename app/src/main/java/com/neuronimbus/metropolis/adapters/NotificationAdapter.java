package com.neuronimbus.metropolis.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.Models.NotificationList;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.activity.Blogs.DetailBlogActivity;
import com.neuronimbus.metropolis.activity.LMS.CoursesDetailActivity;
import com.neuronimbus.metropolis.activity.LMS.LessonDetailActivity;
import com.neuronimbus.metropolis.activity.FAQ.FAQActivity;
import com.neuronimbus.metropolis.activity.Game.GameMainPageActivity;
import com.neuronimbus.metropolis.activity.Home.HomeActivity;
import com.neuronimbus.metropolis.activity.InformationStoreHouse.InformationStoreHouseDetailActivity;
import com.neuronimbus.metropolis.activity.Quiz.QuizActivity;
import com.neuronimbus.metropolis.activity.Setting.Setting.UpdateProfileActivity;
import com.neuronimbus.metropolis.activity.VideoGallery.AllVideoActivity;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{
    Context context;
    ArrayList<NotificationList>notificationLists;
    ClearNotification ClearNotification;

    public NotificationAdapter(Context context, ArrayList<NotificationList> notificationLists, ClearNotification ClearNotification) {
        this.context = context;
        this.notificationLists = notificationLists;
        this.ClearNotification = ClearNotification;
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

        holder.notification_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notificationLists.get(position).getType().equals("blog")){
                    Bundle bundle = new Bundle();

                    bundle.putString("blog_id",notificationLists.get(position).getTypeid());
                    Intent intent = new Intent(context, DetailBlogActivity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                } else  if (notificationLists.get(position).getType().equals("storehouse")){
                    Bundle bundle = new Bundle();

                    bundle.putString("article_id", notificationLists.get(position).getTypeid());
                    Intent intent = new Intent(context, InformationStoreHouseDetailActivity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
                else  if (notificationLists.get(position).getType().equals("game")){
                    Intent intent = new Intent(context, GameMainPageActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
                else  if (notificationLists.get(position).getType().equals("quiz")){
                    Intent intent = new Intent(context, QuizActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
                else  if (notificationLists.get(position).getType().equals("event")){
                    Bundle bundle = new Bundle();

                    bundle.putString("blog_id",notificationLists.get(position).getTypeid());
                    Intent intent = new Intent(context, DetailBlogActivity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
                else  if (notificationLists.get(position).getType().equals("courses")){
                    Bundle bundle = new Bundle();

                    bundle.putString("blog_id",notificationLists.get(position).getTypeid());
                    Intent intent = new Intent(context, CoursesDetailActivity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
                else  if (notificationLists.get(position).getType().equals("faq")){
                    Intent intent = new Intent(context, FAQActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
                else  if (notificationLists.get(position).getType().equals("story")){

                    Intent intent = new Intent(context, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
                else  if (notificationLists.get(position).getType().equals("video")){

                    Intent intent = new Intent(context, AllVideoActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
                else  if (notificationLists.get(position).getType().equals("update_profile")){
                    Intent intent = new Intent(context, UpdateProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
                else  if (notificationLists.get(position).getType().equals("courses_lession")){
                    Bundle bundle = new Bundle();

                    bundle.putString("blog_id",notificationLists.get(position).getTypeid());
                    Intent intent = new Intent(context, LessonDetailActivity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
                else {

                }
            }
        });

        holder.single_notification_clear_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearNotification.ClearNotificationClick(position,notificationLists.get(position).getId(),
                        notificationLists.get(position).getRead_status());
            }
        });

        if (notificationLists.get(position).getRead_status().equals("read")){
            holder.notification_lay.setBackgroundColor(ContextCompat.getColor(context,R.color.white));

        } else  if (notificationLists.get(position).getRead_status().equals("unread")){

            holder.notification_lay.setBackgroundColor(ContextCompat.getColor(context,R.color.notification_unread));
        }

    }

    @Override
    public int getItemCount() {
        return notificationLists.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView notification_title,notification_date,notification_desc;
        ImageView single_notification_clear, notification_img;
        RelativeLayout notification_lay, single_notification_clear_lay;
        CardView notification_card_view;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            notification_title = itemView.findViewById(R.id.notification_title);
            notification_date = itemView.findViewById(R.id.notification_date);
            notification_desc = itemView.findViewById(R.id.notification_desc);
            single_notification_clear = itemView.findViewById(R.id.single_notification_clear);
            notification_img = itemView.findViewById(R.id.notification_img);
            notification_card_view = itemView.findViewById(R.id.notification_card_view);
            single_notification_clear_lay = itemView.findViewById(R.id.single_notification_clear_lay);

            notification_lay = itemView.findViewById(R.id.notification_lay);


        }
    }

    public interface ClearNotification{
        public void ClearNotificationClick(int position, String id, String status);

    }
}
