package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Activity.Landing.LanguageActivity;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.InformationStorehouseList;
import com.example.tooshytoask.Models.Language.data;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.OnClickListner;

import java.util.ArrayList;
import java.util.Locale;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder>{
    ArrayList<data> data;
    Context context;
    SPManager spManager;

    OnClickListner onclicklistener;
    public int SelectedPosition = -1;

    private RadioButton lastCheckedRB = null;

    public LanguageAdapter(ArrayList<data> data, Context context, SPManager spManager, OnClickListner onclicklistener) {
        this.data = data;
        this.context = context;
        this.spManager = spManager;
        this.onclicklistener = onclicklistener;
    }

    @NonNull
    @Override
    public LanguageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.language_item_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageAdapter.ViewHolder holder, int position) {
        holder.eng_lang.setText(data.get(position).getLanguage());
        holder.eng_lang.setChecked(position == SelectedPosition);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton eng_lang;
        Button btn_next;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eng_lang = itemView.findViewById(R.id.eng_lang);
            btn_next = itemView.findViewById(R.id.btn_next);

        }

        public void LanguageChange(final data data, int position){
            eng_lang.setText(data.getLanguage());

            eng_lang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        eng_lang.setTextColor(ContextCompat.getColor(context, R.color.purple));
                        selectLanguage();
                        SelectedPosition = getAdapterPosition();

                        onclicklistener.onClickData(position, data.getLangId());
                    }
                }
            });

        }



    public void selectLanguage(){

        String selectValue=spManager.getLanguage();

        if(selectValue.equals("en"))
        {
            eng_lang.setChecked(true);
            eng_lang.setTextColor(ContextCompat.getColor(context, R.color.purple));
        }
    }

    private void setLocale(String lang) {

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        spManager.setLanguage(lang);

    }
    }
}
