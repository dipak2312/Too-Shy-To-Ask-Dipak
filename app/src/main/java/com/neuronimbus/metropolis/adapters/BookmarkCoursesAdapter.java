package com.neuronimbus.metropolis.adapters;

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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.activity.LMS.CoursesDetailActivity;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class BookmarkCoursesAdapter extends RecyclerView.Adapter<BookmarkCoursesAdapter.ViewHolder> {
    Context context;
    ArrayList<com.neuronimbus.metropolis.Models.courses> courses;

    String time, part1, part2;

    public BookmarkCoursesAdapter(Context context, ArrayList<com.neuronimbus.metropolis.Models.courses> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public BookmarkCoursesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_courses_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkCoursesAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(courses.get(position).getBookmark_img()).into(holder.blog_img);
        holder.courses_title.setText(courses.get(position).getBookmark_posttitle());
        time = courses.get(position).getBookmark_timing();
        String[] parts = time.split(":");
        part1 = parts[0];
        part2 = parts[1];
        holder.course_time_hr.setText(part1 + "h ");
        holder.course_time_min.setText(part2 + "m");
        holder.lessons.setText(courses.get(position).getBookmark_total_lesson());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView card_view_courses;
        ShapeableImageView blog_img;
        ImageView save_img;
        TextView courses_title, course_time_hr, course_time_min, lessons;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            card_view_courses = itemView.findViewById(R.id.card_view_courses);
            blog_img = itemView.findViewById(R.id.blog_img);
            save_img = itemView.findViewById(R.id.save_img);
            save_img.setVisibility(View.GONE);
            courses_title = itemView.findViewById(R.id.courses_title);
            course_time_hr = itemView.findViewById(R.id.course_time_hr);
            course_time_min = itemView.findViewById(R.id.course_time_min);
            lessons = itemView.findViewById(R.id.lessons);
            card_view_courses = itemView.findViewById(R.id.card_view_courses);

            card_view_courses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();

                    bundle.putString("courses_id",courses.get(getAdapterPosition()).getBookmark_postid());
                    Intent intent = new Intent(context, CoursesDetailActivity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
