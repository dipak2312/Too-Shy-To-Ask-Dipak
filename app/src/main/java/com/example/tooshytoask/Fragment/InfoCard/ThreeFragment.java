package com.example.tooshytoask.Fragment.InfoCard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tooshytoask.Activity.Setting.UpdateProfileActivity;
import com.example.tooshytoask.Adapters.CategoryAdapter;
import com.example.tooshytoask.Adapters.HealthAdapter;
import com.example.tooshytoask.Adapters.RecommendedBlogAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.HealthIssues;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.ClickListener;
import com.example.tooshytoask.Utils.OnClickListner;

import java.util.ArrayList;

public class ThreeFragment extends Fragment implements View.OnClickListener, OnClickListner{
    Context context;
    SPManager spManager;
    RecyclerView health_recy, recyclerView;
    ArrayList<HealthIssues>healthIssues;
    HealthAdapter adapter;
    ClickListener clickListener;
    LinearLayout health_issues_title;
    Button yes_btn, no_btn;
    ImageButton next_btn;
    TextView skip_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        context = getActivity();
        spManager = new SPManager(context);
        clickListener=(ClickListener)context;
        clickListener.onClick(false);

        yes_btn = view.findViewById(R.id.yes_btn);
        yes_btn.setOnClickListener(this);
        no_btn = view.findViewById(R.id.no_btn);
        no_btn.setOnClickListener(this);
        next_btn = view.findViewById(R.id.next_btn);
        next_btn.setOnClickListener(this);
        skip_btn = view.findViewById(R.id.skip_btn);
        skip_btn.setOnClickListener(this);
        health_issues_title = view.findViewById(R.id.health_issues_title);
        health_issues_title.setOnClickListener(this);

        health_recy = view.findViewById(R.id.health_recy);
        health_recy.setOnClickListener(this);

        //health_recy.setLayoutManager(new GridLayoutManager(context,2, GridLayoutManager.VERTICAL, true));

        healthIssues = new ArrayList<>();

        healthIssues.add(new HealthIssues("Throid", false));
        healthIssues.add(new HealthIssues("Painful Periods", false));
        healthIssues.add(new HealthIssues("Mental Health", false));
        healthIssues.add(new HealthIssues("Irregular Periods", false));
        healthIssues.add(new HealthIssues("Fibroids", false));
        healthIssues.add(new HealthIssues("PCOS/PCOD", false));

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        health_recy.setLayoutManager(linearLayoutManager1);

        health_recy.setAdapter(new HealthAdapter(healthIssues, this, context));

        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();



        if (id == skip_btn.getId()){
            clickListener.onClick(true);
        }

        if (id == yes_btn.getId()){
            health_recy.setVisibility(View.VISIBLE);
            next_btn.setBackgroundResource(R.drawable.circle_button_inactive);
            yes_btn.setBackgroundResource(R.drawable.gender_border_active);
            yes_btn.setTextColor(ContextCompat.getColor(context, R.color.white));
            no_btn.setBackgroundResource(R.drawable.gender_border_inactive);
            no_btn.setTextColor(ContextCompat.getColor(context, R.color.black));

        }

        else if (id == no_btn.getId()){
            health_recy.setVisibility(View.GONE);
            next_btn.setBackgroundResource(R.drawable.circle_button_active);
            no_btn.setBackgroundResource(R.drawable.gender_border_active);
            no_btn.setTextColor(ContextCompat.getColor(context, R.color.white));
            yes_btn.setBackgroundResource(R.drawable.gender_border_inactive);
            yes_btn.setTextColor(ContextCompat.getColor(context, R.color.black));

        }

        else {
            ArrayList<Boolean> myvalue = new ArrayList<Boolean>();
            for (int i = 0; i < healthIssues.size(); i++) {
                myvalue.add(healthIssues.get(i).getSelected());
            }
            boolean ans = myvalue.contains(true);

            if (ans) {
                clickListener.onClick(true);


            } {
                clickListener.onClick(false);
            }
        }

    }

    @Override
    public void onClickData(int position, int id) {

        ArrayList<Boolean> myvalue=new ArrayList<Boolean>();

        for(int i=0;i<healthIssues.size();i++)
        {
            myvalue.add(healthIssues.get(i).getSelected());
        }

        boolean ans = myvalue.contains(true);

        if(ans)
        {
            next_btn.setBackgroundResource(R.drawable.circle_button_active);

        }else
        {
            next_btn.setBackgroundResource(R.drawable.circle_button_inactive);
        }

    }

}