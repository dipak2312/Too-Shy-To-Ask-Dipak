package com.neuronimbus.metropolis.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.neuronimbus.metropolis.Models.AddChar;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.GameValueClick;

import java.util.ArrayList;

public class SelectTextAdapter extends RecyclerView.Adapter<SelectTextAdapter.selecttextViewHolder> {

    Context context;
    Animation aniRotate;
    ArrayList<AddChar> value;
    GameValueClick onclick;

    public SelectTextAdapter(Context context, ArrayList<AddChar> value, GameValueClick onclick) {
        this.context = context;
        this.value = value;
        this.onclick = onclick;
    }

    @NonNull
    @Override
    public selecttextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.select_text_view,parent,false);
        selecttextViewHolder holder=new selecttextViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull selecttextViewHolder holder, int position) {

        holder.txt_char.setText(value.get(position).getCharValue());

        if(value.get(position).isStatus())
        {

            holder.rel_bag.setBackground(ContextCompat.getDrawable(context, R.drawable.select_text_dark));
            holder.txt_char.setTextColor(ContextCompat.getColor(context, R.color.white));
            holder.txt_char.setTextColor(context.getResources().getColor(R.color.white));
        }
        else
        {

            holder.rel_bag.setBackground(ContextCompat.getDrawable(context, R.drawable.select_text_fent));
            holder.txt_char.setTextColor(ContextCompat.getColor(context, R.color.black));
            holder.txt_char.setTextColor(context.getResources().getColor(R.color.black));
        }


    }

    @Override
    public int getItemCount() {
        return value.size();
    }

    public class selecttextViewHolder extends RecyclerView.ViewHolder
    {
        TextView txt_char;
        RelativeLayout rel_bag;
        public selecttextViewHolder(@NonNull View itemView) {
            super(itemView);

            aniRotate = AnimationUtils.loadAnimation(context,R.anim.rotate_clockwise);

            rel_bag=(RelativeLayout)itemView.findViewById(R.id.rel_bag);
            rel_bag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(value.get(getAdapterPosition()).isStatus())
                    {
                        value.get(getAdapterPosition()).setStatus(false);
                        onclick.gameData(value.get(getAdapterPosition()).getCharValue(),getAdapterPosition(),"remove");
                        notifyDataSetChanged();

                    }
                    else
                    {
                        value.get(getAdapterPosition()).setStatus(true);
                        onclick.gameData(value.get(getAdapterPosition()).getCharValue(),getAdapterPosition(),"add");
                        notifyDataSetChanged();

                    }

                }
            });

            txt_char=(TextView)itemView.findViewById(R.id.txt_char);

        }
    }
}
