package com.example.tooshytoask.Fragment.InfoCard;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tooshytoask.Adapters.CategoryAdapter;
import com.example.tooshytoask.Adapters.HealthAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.HealthIssues;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class ThreeFragment extends Fragment {
    Context context;
    SPManager spManager;
    RecyclerView health_recy, recyclerView;
    ArrayList<HealthIssues>healthIssues;
    HealthAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        context = getActivity();
        spManager = new SPManager(context);

        /*recyclerView = view.findViewById(R.id.health_recy);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2, GridLayoutManager.VERTICAL, true));

        healthIssues = new ArrayList<>();

        healthIssues.add(new HealthIssues("Throid","Mental Health"));
        healthIssues.add(new HealthIssues("Painful Periods","Irregular Periods"));
        healthIssues.add(new HealthIssues("PCOS/PCOD", "Fibroids"));

        recyclerView.setAdapter(new HealthAdapter(healthIssues));*/

        return view;
    }
}