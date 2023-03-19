package com.example.tooshytoask.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class BookmarkCoursesAdapter extends RecyclerView.Adapter<BookmarkCoursesAdapter.ViewHolder> {
    Context context;
    ArrayList<com.example.tooshytoask.Models.courses> courses;

    public BookmarkCoursesAdapter(Context context, ArrayList<com.example.tooshytoask.Models.courses> courses) {
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
        Glide.with(context).load(courses.get(position).getBookmark_imgvid()).into(holder.blog_img);
        holder.courses_title.setText(courses.get(position).getBookmark_posttitle());
        holder.course_time.setText(courses.get(position).getBookmark_timing());
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
        TextView courses_title, course_time, lessons;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            card_view_courses = itemView.findViewById(R.id.card_view_courses);
            blog_img = itemView.findViewById(R.id.blog_img);
            save_img = itemView.findViewById(R.id.save_img);
            save_img.setVisibility(View.GONE);
            courses_title = itemView.findViewById(R.id.courses_title);
            course_time = itemView.findViewById(R.id.course_time);
            lessons = itemView.findViewById(R.id.lessons);
        }
    }
}
