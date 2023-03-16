package com.example.tooshytoask.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.Activity.Landing.SignInActivity;
import com.example.tooshytoask.Activity.Story.StoryActivity;
import com.example.tooshytoask.Activity.Story.StoryDisplayFragment;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.StatusItem;
import com.example.tooshytoask.Models.StoryCategory;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.GuestLoginPopup;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder>{
    ArrayList<StoryCategory> StoryCategory;
    Context context;
    SPManager spManager;

    public StatusAdapter(ArrayList<com.example.tooshytoask.Models.StoryCategory> storyCategory, Context context, SPManager spManager) {
        StoryCategory = storyCategory;
        this.context = context;
        this.spManager = spManager;
    }


    @NonNull
    @Override
    public StatusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.status_item_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (spManager.getTstaguestLoginStatus().equals("false")) {
            Glide.with(context).load(StoryCategory.get(position).getCategory_img()).into(holder.status_img);
            holder.status_title.setText(StoryCategory.get(position).getCategory_title());
        }
        else {
            Glide.with(context).load(StoryCategory.get(position).getCategory_img()).into(holder.status_img);
            holder.status_img.setRenderEffect(RenderEffect.createBlurEffect(20,20, Shader.TileMode.MIRROR));
            holder.lock_fent_img.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return StoryCategory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView lock_fent_img;
        ShapeableImageView status_img;
        TextView status_title;
        RelativeLayout status_lay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            status_img = itemView.findViewById(R.id.status_img);
            status_title = itemView.findViewById(R.id.status_title);
            lock_fent_img = itemView.findViewById(R.id.lock_fent_img);
            status_lay = itemView.findViewById(R.id.status_lay);


            status_lay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (spManager.getTstaguestLoginStatus().equals("false")) {
                        Bundle bundle = new Bundle();

                        bundle.putString("story_id", StoryCategory.get(getAdapterPosition()).getCategory_id());
                        Intent intent = new Intent(context, StoryActivity.class);
                        intent.putExtras(bundle);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(intent);
                        }
                        else {
                            GuestLoginPopup.LogOut(context, spManager);
                        }
                    }
                });

        }
    }
}
