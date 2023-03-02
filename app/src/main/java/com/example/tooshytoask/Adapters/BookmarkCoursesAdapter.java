package com.example.tooshytoask.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.R;
import com.google.android.material.imageview.ShapeableImageView;

public class BookmarkCoursesAdapter extends RecyclerView.Adapter<BookmarkCoursesAdapter.ViewHolder> {
    @NonNull
    @Override
    public BookmarkCoursesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_courses_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkCoursesAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
