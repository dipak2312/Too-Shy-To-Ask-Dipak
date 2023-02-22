package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.Activity.Story.StoryActivity;
import com.example.tooshytoask.Activity.Story.StoryDisplayFragment;
import com.example.tooshytoask.Models.StatusItem;
import com.example.tooshytoask.Models.StoryCategory;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder>{
    ArrayList<StoryCategory> StoryCategory;
    Context context;

    public StatusAdapter(Context context, ArrayList<StoryCategory> StoryCategory) {
        this.StoryCategory = StoryCategory;
        this.context = context;
    }

    @NonNull
    @Override
    public StatusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.status_item_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.status_img.setImageResource(storyCategories.get(position).getCategoryImg());
        Glide.with(context).load(StoryCategory.get(position).getCategory_img()).into(holder.status_img);
        holder.status_title.setText(StoryCategory.get(position).getCategory_title());
    }

    @Override
    public int getItemCount() {
        return StoryCategory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView status_img;
        TextView status_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            status_img = itemView.findViewById(R.id.status_img);
            status_title = itemView.findViewById(R.id.status_title);

            status_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    /*Bundle bundle = new Bundle();

                    bundle.putString("story_id",StoryCategory.get(getAdapterPosition()).getCategory_id());
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    StoryDisplayFragment fragment = new StoryDisplayFragment();
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.add(R.id.rootLayout, fragment);
                    ft.addToBackStack(null);
                    ft.commit();*/

                    Bundle bundle = new Bundle();

                    bundle.putString("story_id",StoryCategory.get(getAdapterPosition()).getCategory_id());
                    Intent intent = new Intent(context, StoryActivity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
