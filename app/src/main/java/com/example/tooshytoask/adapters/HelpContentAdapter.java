package com.example.tooshytoask.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.activity.Help.Help3Activity;
import com.example.tooshytoask.Models.Help.content;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class HelpContentAdapter extends RecyclerView.Adapter<HelpContentAdapter.ViewHolder> {
    ArrayList<content> content;
    Context context;

    public HelpContentAdapter(Context context, ArrayList<com.example.tooshytoask.Models.Help.content> content) {
        this.content = content;
        this.context = context;
    }


    @NonNull
    @Override
    public HelpContentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.help_content_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HelpContentAdapter.ViewHolder holder, int position) {
        //holder.arrow_img.setImageDrawable(ContextCompat.getDrawable(context, help3Item.get(position).getArrow_img()));
        holder.question.setText(content.get(position).getHelpcontent_title());

    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView arrow_img;
        TextView question;
        RelativeLayout lin_lay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            arrow_img = itemView.findViewById(R.id.arrow_img);
            question = itemView.findViewById(R.id.question);
            lin_lay = itemView.findViewById(R.id.lin_lay);

            lin_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intent = new Intent(context, Help3Activity.class);
                    intent.putExtra("helpcontent_id",content.get(getAdapterPosition()).getHelpcontent_id());
                    intent.putExtra("helpcontent_catid",content.get(getAdapterPosition()).getHelpcontent_catid());
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            });


        }
    }
}
