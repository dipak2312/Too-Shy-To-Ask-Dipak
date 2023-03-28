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
import com.example.tooshytoask.Models.insightcourses;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.OnBookmarkClicked;
import com.example.tooshytoask.activity.LMS.CoursesDetailActivity;

import java.util.ArrayList;

public class AllCoursesAdapter extends RecyclerView.Adapter<AllCoursesAdapter.ViewHolder> {
    Context context;
    ArrayList<insightcourses> insightcourses;
    OnBookmarkClicked onBookmarkClicked;
    boolean like;
    SPManager spManager;
    String time, part1, part2;
    int position =0;

    public AllCoursesAdapter(Context context, ArrayList<com.example.tooshytoask.Models.insightcourses> insightcourses,
                             OnBookmarkClicked onBookmarkClicked, SPManager spManager) {
        this.context = context;
        this.insightcourses = insightcourses;
        this.onBookmarkClicked = onBookmarkClicked;
        this.spManager = spManager;
    }

    @NonNull
    @Override
    public AllCoursesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.courses_listing_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(insightcourses.get(position).getImage()).into(holder.blog_img);
        holder.courses_title.setText(insightcourses.get(position).getTitle());
        time = insightcourses.get(position).getTiming();
        String[] parts = time.split(":");
        part1 = parts[0];
        part2 = parts[1];
        holder.course_time_hr.setText(part1 + "h ");
        holder.course_time_min.setText(part2 + "m");
        holder.lessons.setText(insightcourses.get(position).getTotal_lesson());



        if (insightcourses.get(position).getStatus().equals("completed")){
            holder.courses_status.setImageResource(R.drawable.lesson_complete);
        }
        else if (insightcourses.get(position).getStatus().equals("pending")){
            holder.courses_status.setImageResource(R.drawable.lesson_inprogress);
        }

        else if (insightcourses.get(position).getStatus().equals("")){
            holder.courses_status.setImageResource(R.drawable.lesson_lock);
        }

        if (insightcourses.get(position).getBookmarked().equals("1")){
            holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.saved_bookmark));
            like = false;
        }
        else  {
            holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.save));
            like = true;

        }

        holder.save_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (like) {
                    holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.saved_bookmark));
                    onBookmarkClicked.onBookmarkButtonClick(position,insightcourses.get(position).getId(), "save");
                    like = false;

                } else  {
                    holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.save));
                    onBookmarkClicked.onBookmarkButtonClick(position,insightcourses.get(position).getId(), "remove");
                    like = true;

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return insightcourses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView blog_img, save_img, lock_img, courses_status;
        TextView courses_title, course_time_hr, lessons, course_time_min;
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
            courses_status = itemView.findViewById(R.id.courses_status);
            card_view_courses = itemView.findViewById(R.id.card_view_courses);

            card_view_courses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();

                    bundle.putString("courses_id",insightcourses.get(getAdapterPosition()).getId());
                    Intent intent = new Intent(context, CoursesDetailActivity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }

        public void main(String[] args){


        }
    }


}
