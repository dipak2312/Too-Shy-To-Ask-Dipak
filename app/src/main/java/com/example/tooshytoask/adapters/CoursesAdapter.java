package com.example.tooshytoask.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.InsightScreen.courses;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.GuestLoginPopup;
import com.example.tooshytoask.Utils.OnBookmarkClicked;
import com.example.tooshytoask.activity.LMS.CoursesDetailActivity;

import java.util.ArrayList;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder>{
    Context context;
    ArrayList<courses>courses;
    OnBookmarkClicked onBookmarkClicked;
    boolean like = true;
    String type,time, part1, part2;
    SPManager spManager;

    public CoursesAdapter(Context context, ArrayList<com.example.tooshytoask.Models.InsightScreen.courses> courses,
                          OnBookmarkClicked onBookmarkClicked, String type, SPManager spManager) {
        this.context = context;
        this.courses = courses;
        this.onBookmarkClicked = onBookmarkClicked;
        this.type = type;
        this.spManager = spManager;
    }


    @NonNull
    @Override
    public CoursesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.courses_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(courses.get(position).getImage()).into(holder.blog_img);
        holder.courses_title.setText(courses.get(position).getTitle());
        time = courses.get(position).getTiming();
        String[] parts = time.split(":");
        part1 = parts[0];
        part2 = parts[1];
        holder.course_time_hr.setText(part1 + "h ");
        holder.course_time_min.setText(part2 + "m");
        holder.lessons.setText(courses.get(position).getTotal_lesson());

        if (spManager.getTstaguestLoginStatus().equals("false")) {
        if (courses.get(position).getBookmarked().equals("1")){
            holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.saved_bookmark));
            like = false;
        }
        else  {
            holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.save));
            like = true;

        }
    }
        else {
        holder.lock_img.setVisibility(View.VISIBLE);

    }
        holder.save_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (like) {
                    holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.saved_bookmark));
                    onBookmarkClicked.onBookmarkButtonClick(position,courses.get(position).getId(), "save");
                    like = false;

                } else  {
                    holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.save));
                    onBookmarkClicked.onBookmarkButtonClick(position,courses.get(position).getId(), "remove");
                    like = true;

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView blog_img, save_img, lock_img;
        TextView courses_title, course_time_min,course_time_hr, lessons;
        CardView card_view_courses;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            blog_img = itemView.findViewById(R.id.blog_img);
            save_img = itemView.findViewById(R.id.save_img);
            courses_title = itemView.findViewById(R.id.courses_title);
            course_time_hr = itemView.findViewById(R.id.course_time_hr);
            course_time_min = itemView.findViewById(R.id.course_time_min);
            lessons = itemView.findViewById(R.id.lessons);
            lock_img = itemView.findViewById(R.id.lock_img);
            card_view_courses = itemView.findViewById(R.id.card_view_courses);

            card_view_courses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (spManager.getTstaguestLoginStatus().equals("false")) {
                        Bundle bundle = new Bundle();

                        bundle.putString("courses_id", courses.get(getAdapterPosition()).getId());
                        Intent intent = new Intent(context, CoursesDetailActivity.class);
                        intent.putExtras(bundle);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    } else {
                        GuestLoginPopup.LogOut(context, spManager);
                    }
                }
            });

        }
    }
}
