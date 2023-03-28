package com.example.tooshytoask.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Models.Courses.Lesson.data;
import com.example.tooshytoask.Models.Courses.Lesson.upcominglesson;
import com.example.tooshytoask.R;
import com.example.tooshytoask.activity.LMS.LessonDetailActivity;

import java.util.ArrayList;

public class UpcomingLessonAdapter extends RecyclerView.Adapter<UpcomingLessonAdapter.ViewHolder> {
    Context context;
    ArrayList<upcominglesson>upcominglesson;
    ArrayList<data> data;

    public UpcomingLessonAdapter(Context context, ArrayList<upcominglesson> upcominglesson, ArrayList<data> data) {
        this.context = context;
        this.upcominglesson = upcominglesson;
        this.data = data;
    }


    @NonNull
    @Override
    public UpcomingLessonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lessons_item_views,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingLessonAdapter.ViewHolder holder, int position) {
        holder.lesson_title.setText(Html.fromHtml(upcominglesson.get(position).getTitle()));
        holder.sr_no.setText(Html.fromHtml(upcominglesson.get(position).getSno()));
        holder.lesson_time.setText(Html.fromHtml(upcominglesson.get(position).getTiming()));
        holder.quiz_count.setText(Html.fromHtml(upcominglesson.get(position).getQuiz()));

        if (upcominglesson.get(position).getLesson_status().equals("completed")){
            holder.lesson_status.setText(R.string.completed);
            holder.lesson_status_img.setImageResource(R.drawable.lesson_complete);
            holder.lesson_status.setVisibility(View.VISIBLE);
            holder.time_lin_lay.setVisibility(View.GONE);
        }
        else if (upcominglesson.get(position).getLesson_status().equals("pending")){
            holder.lesson_status.setText(R.string.now_playing);
            holder.lesson_status_img.setImageResource(R.drawable.lesson_inprogress);
            holder.lesson_status.setVisibility(View.VISIBLE);
            holder.time_lin_lay.setVisibility(View.GONE);
        }

        else if (upcominglesson.get(position).getLesson_status().equals("")){
            holder.time_lin_lay.setVisibility(View.VISIBLE);
            holder.lesson_status.setVisibility(View.GONE);
            holder.lesson_status_img.setImageResource(R.drawable.lesson_lock);
        }

    }

    @Override
    public int getItemCount() {
        return upcominglesson.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView lesson_status_img;
        TextView lesson_title, sr_no, lesson_status, lesson_time, quiz_count;
        CardView card_view_courses;
        LinearLayout time_lin_lay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lesson_status_img = itemView.findViewById(R.id.lesson_status_img);
            lesson_status = itemView.findViewById(R.id.lesson_status);
            lesson_title = itemView.findViewById(R.id.lesson_title);
            quiz_count = itemView.findViewById(R.id.quiz_count);
            lesson_time = itemView.findViewById(R.id.lesson_time);
            sr_no = itemView.findViewById(R.id.sr_no);
            lesson_status = itemView.findViewById(R.id.lesson_status);
            time_lin_lay = itemView.findViewById(R.id.time_lin_lay);
            card_view_courses = itemView.findViewById(R.id.card_view_courses);

            card_view_courses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();

                    bundle.putString("lesson_id",upcominglesson.get(getAdapterPosition()).getId());
                    bundle.putString("courses_id",data.get(getAdapterPosition()).getCourseid());
                    Intent intent = new Intent(context, LessonDetailActivity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}