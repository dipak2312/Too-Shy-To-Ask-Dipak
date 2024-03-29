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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.Models.course_search;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.OnBookmarkClicked;
import com.neuronimbus.metropolis.activity.LMS.CoursesDetailActivity;

import java.util.ArrayList;

public class CoursesSearchAdapter extends RecyclerView.Adapter<CoursesSearchAdapter.ViewHolder>{
    Context context;
    ArrayList<course_search>Allcourse_search;
    OnBookmarkClicked onBookmarkClicked;
    boolean like;
    String type, time, part1, part2;

    public CoursesSearchAdapter(Context context, ArrayList<course_search> allcourse_search, OnBookmarkClicked onBookmarkClicked, String type) {
        this.context = context;
        Allcourse_search = allcourse_search;
        this.onBookmarkClicked = onBookmarkClicked;
        this.type = type;
    }


    @NonNull
    @Override
    public CoursesSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_courses_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesSearchAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(Allcourse_search.get(position).getImage()).into(holder.blog_img);
        holder.courses_title.setText(Allcourse_search.get(position).getTitle());
        time = Allcourse_search.get(position).getTiming();
        String[] parts = time.split(":");
        part1 = parts[0];
        part2 = parts[1];
        holder.course_time_hr.setText(part1 + "h ");
        holder.course_time_min.setText(part2 + "m");
        holder.lessons.setText(Allcourse_search.get(position).getTotal_lesson());

        if (Allcourse_search.get(position).getBookmarked().equals("1")){
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
                    onBookmarkClicked.onBookmarkButtonClick(position,Allcourse_search.get(position).getId(), "save");
                    like = false;

                } else  {
                    holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.save));
                    onBookmarkClicked.onBookmarkButtonClick(position,Allcourse_search.get(position).getId(), "remove");
                    like = true;

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return Allcourse_search.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView blog_img, save_img;
        TextView courses_title, course_time_hr, course_time_min, lessons;
        CardView card_view_courses;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            blog_img = itemView.findViewById(R.id.blog_img);
            save_img = itemView.findViewById(R.id.save_img);
            courses_title = itemView.findViewById(R.id.courses_title);
            course_time_hr = itemView.findViewById(R.id.course_time_hr);
            course_time_min = itemView.findViewById(R.id.course_time_min);
            lessons = itemView.findViewById(R.id.lessons);
            card_view_courses = itemView.findViewById(R.id.card_view_courses);

            card_view_courses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();

                    bundle.putString("courses_id",Allcourse_search.get(getAdapterPosition()).getId());
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
