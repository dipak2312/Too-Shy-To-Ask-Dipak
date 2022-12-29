package com.example.tooshytoask.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tooshytoask.Adapters.CategoryAdapter;
import com.example.tooshytoask.Adapters.StatusAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.StatusItem;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView, recy_status;
    ArrayList<StatusItem>statusItems;
    StatusAdapter adapter;
    Context context;
    SPManager spManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        context = getActivity();
        spManager = new SPManager(context);
        recy_status = view.findViewById(R.id.recy_status);

        statusItems = new ArrayList<>();

        statusItems.add(new StatusItem(R.drawable.status1));
        statusItems.add(new StatusItem(R.drawable.status2));
        statusItems.add(new StatusItem(R.drawable.status3));
        statusItems.add(new StatusItem(R.drawable.status3));
        statusItems.add(new StatusItem(R.drawable.status3));
        statusItems.add(new StatusItem(R.drawable.status3));
        statusItems.add(new StatusItem(R.drawable.status3));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_status.setLayoutManager(linearLayoutManager);

        adapter = new StatusAdapter(context ,statusItems);
        recy_status.setAdapter(adapter);

        //recy_status.setAdapter(new StatusAdapter(context, statusItems));

        return view;
    }
}