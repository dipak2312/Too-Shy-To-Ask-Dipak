package com.example.tooshytoask.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Models.Option;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.optionviewholder> {


    Context context;
    ArrayList<Option> optionList;
    private int selectedPos = -1;
    private IOnClick iOnClick;

    public OptionAdapter(Context context, ArrayList<Option> optionList, IOnClick iOnClick) {
        this.context = context;
        this.optionList = optionList;
        this.iOnClick = iOnClick;
    }

    @NonNull
    @Override
    public optionviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.options_view,parent,false);
        optionviewholder holder=new optionviewholder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull optionviewholder holder, @SuppressLint("RecyclerView") int position) {

        holder.option.setText(optionList.get(position).getOption_serial());

        holder.option_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    selectedPos = position;
                    if (iOnClick != null) {
                        iOnClick.optionSelected(optionList.get(position), optionList);
                    }
                    notifyDataSetChanged();

            }

        });

            if (selectedPos==position) {
                holder.rel_option_bag.setBackground(ContextCompat.getDrawable(context, R.drawable.options_dark));
                holder.option.setTextColor(ContextCompat.getColor(context, R.color.purple));
                //holder.radio_btn.setBackgroundResource(R.drawable.radio_button_checked);
                holder.radio_btn.setVisibility(View.GONE);
                holder.radio_btn_active.setVisibility(View.VISIBLE);
            }
            else {
                holder.rel_option_bag.setBackground(ContextCompat.getDrawable(context, R.drawable.options_fent));
                holder.option.setTextColor(ContextCompat.getColor(context, R.color.black));
                holder.radio_btn_active.setVisibility(View.GONE);
                holder.radio_btn.setVisibility(View.VISIBLE);
            }


    }

    @Override
    public int getItemCount() {
        return optionList.size();
    }

    public class optionviewholder extends RecyclerView.ViewHolder
    {
        RelativeLayout rel_option_bag;
        TextView option;
        ImageView radio_btn, radio_btn_active;
        RelativeLayout option_lay;

        public optionviewholder(@NonNull View itemView) {
            super(itemView);

            rel_option_bag= itemView.findViewById(R.id.rel_option_bag);
           option= itemView.findViewById(R.id.option);
            radio_btn= itemView.findViewById(R.id.radio_btn);
            radio_btn_active= itemView.findViewById(R.id.radio_btn_active);
            option_lay= itemView.findViewById(R.id.option_lay);

        }
    }

    public interface IOnClick {
        void optionSelected(Option option,ArrayList<Option>optionList);
    }
}

