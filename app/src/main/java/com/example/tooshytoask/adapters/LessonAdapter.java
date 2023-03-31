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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Models.Courses.lesson;
import com.example.tooshytoask.R;
import com.example.tooshytoask.activity.LMS.LessonDetailActivity;

import java.util.ArrayList;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder> {
    Context context;
    ArrayList<lesson>lesson;

    public LessonAdapter(Context context, ArrayList<lesson> lesson) {
        this.context = context;
        this.lesson = lesson;
    }

    @NonNull
    @Override
    public LessonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lessons_item_views,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonAdapter.ViewHolder holder, int position) {
        holder.lesson_title.setText(Html.fromHtml(lesson.get(position).getTitle()));
        holder.sr_no.setText(Html.fromHtml(lesson.get(position).getSno()));
        holder.lesson_time.setText(Html.fromHtml(lesson.get(position).getTiming()));
        holder.quiz_count.setText(Html.fromHtml(lesson.get(position).getQuiz()));

        if (lesson.get(position).getLesson_status().equals("completed")){
            holder.lesson_status.setText(R.string.completed);
            holder.lesson_title.setTextColor(ContextCompat.getColor(context, R.color.black));
            holder.lesson_status_img.setImageResource(R.drawable.lesson_complete);
            holder.lesson_status.setVisibility(View.VISIBLE);
            holder.time_lin_lay.setVisibility(View.GONE);
        }
        else if (lesson.get(position).getLesson_status().equals("pending")){
            holder.lesson_status.setText(R.string.now_playing);
            holder.lesson_status_img.setImageResource(R.drawable.lesson_inprogress);
            holder.lesson_title.setTextColor(ContextCompat.getColor(context, R.color.purple));
            holder.lesson_status.setVisibility(View.VISIBLE);
            holder.time_lin_lay.setVisibility(View.GONE);
        }

        else if (lesson.get(position).getLesson_status().equals("")){
            holder.time_lin_lay.setVisibility(View.VISIBLE);
            holder.lesson_status.setVisibility(View.GONE);
            holder.lesson_title.setTextColor(ContextCompat.getColor(context, R.color.black));
            holder.lession_rel_layout.setAlpha(0.5f);
            holder.lesson_status_img.setImageResource(R.drawable.lesson_lock);
        }

    }

    @Override
    public int getItemCount() {
        return lesson.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView lesson_status_img;
        TextView lesson_title, sr_no, lesson_status, lesson_time, quiz_count;
        CardView card_view_courses;
        LinearLayout time_lin_lay;
        RelativeLayout lession_rel_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lession_rel_layout = itemView.findViewById(R.id.lession_rel_layout);
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

                    bundle.putString("lesson_id",lesson.get(getAdapterPosition()).getId());
                    bundle.putString("courses_id",lesson.get(getAdapterPosition()).getCourse_id());
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
