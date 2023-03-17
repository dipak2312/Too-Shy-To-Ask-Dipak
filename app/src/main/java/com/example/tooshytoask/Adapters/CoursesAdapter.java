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
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.CoursesItems;
import com.example.tooshytoask.Models.EventBlogItems;
import com.example.tooshytoask.Models.InsightScreen.courses;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.GuestLoginPopup;
import com.example.tooshytoask.Utils.OnBookmarkClicked;

import java.util.ArrayList;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder>{
    Context context;
    ArrayList<courses>courses;
    OnBookmarkClicked onBookmarkClicked;
    boolean like = true;
    String type = "courses";
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
        holder.course_time.setText(courses.get(position).getTiming());
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
        TextView courses_title, course_time, lessons;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            blog_img = itemView.findViewById(R.id.blog_img);
            save_img = itemView.findViewById(R.id.save_img);
            courses_title = itemView.findViewById(R.id.courses_title);
            course_time = itemView.findViewById(R.id.course_time);
            lessons = itemView.findViewById(R.id.lessons);
            lock_img = itemView.findViewById(R.id.lock_img);
        }

    }
}
