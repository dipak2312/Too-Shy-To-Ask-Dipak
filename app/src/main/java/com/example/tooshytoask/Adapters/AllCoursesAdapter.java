package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.Models.insightcourses;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.OnBookmarkClicked;

import java.util.ArrayList;

public class AllCoursesAdapter extends RecyclerView.Adapter<AllCoursesAdapter.ViewHolder> {
    Context context;
    ArrayList<insightcourses> insightcourses;
    OnBookmarkClicked onBookmarkClicked;
    boolean like;

    public AllCoursesAdapter(Context context, ArrayList<com.example.tooshytoask.Models.insightcourses> insightcourses, OnBookmarkClicked onBookmarkClicked) {
        this.context = context;
        this.insightcourses = insightcourses;
        this.onBookmarkClicked = onBookmarkClicked;
    }


    @NonNull
    @Override
    public AllCoursesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_courses_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCoursesAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(insightcourses.get(position).getImage()).into(holder.blog_img);
        holder.courses_title.setText(insightcourses.get(position).getTitle());
        holder.course_time.setText(insightcourses.get(position).getTiming());
        holder.lessons.setText(insightcourses.get(position).getTotal_lesson());

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
        ImageView blog_img, save_img;
        TextView courses_title, course_time, lessons;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            blog_img = itemView.findViewById(R.id.blog_img);
            save_img = itemView.findViewById(R.id.save_img);
            courses_title = itemView.findViewById(R.id.courses_title);
            course_time = itemView.findViewById(R.id.course_time);
            lessons = itemView.findViewById(R.id.lessons);
        }
    }
}
