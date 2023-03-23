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
import com.example.tooshytoask.Models.course_search;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.OnBookmarkClicked;
import com.example.tooshytoask.activity.Courses.CoursesDetailActivity;

import java.util.ArrayList;

public class CoursesSearchAdapter extends RecyclerView.Adapter<CoursesSearchAdapter.ViewHolder>{
    Context context;
    ArrayList<course_search>Allcourse_search;
    OnBookmarkClicked onBookmarkClicked;
    boolean like;
    String type ;

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
        holder.course_time.setText(Allcourse_search.get(position).getTiming());
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
        TextView courses_title, course_time, lessons;
        CardView card_view_courses;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            blog_img = itemView.findViewById(R.id.blog_img);
            save_img = itemView.findViewById(R.id.save_img);
            courses_title = itemView.findViewById(R.id.courses_title);
            course_time = itemView.findViewById(R.id.course_time);
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
