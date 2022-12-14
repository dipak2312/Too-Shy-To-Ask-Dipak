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
import com.example.tooshytoask.Adapters.RecommendedBlogAdapter;
import com.example.tooshytoask.Adapters.StatusAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.BlogItems;
import com.example.tooshytoask.Models.RecommendedBlogItems;
import com.example.tooshytoask.Models.StatusItem;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView recy_recommended_blogs, recy_status;
    ArrayList<StatusItem>statusItems;
    ArrayList<RecommendedBlogItems>recommendedBlogItems;
    RecommendedBlogAdapter adapter2;
    StatusAdapter adapter;
    Context context;
    SPManager spManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
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

        recy_recommended_blogs = view.findViewById(R.id.recy_recommended_blogs);
        recommendedBlogItems = new ArrayList<>();

        recommendedBlogItems.add(new RecommendedBlogItems(R.drawable.blog2, R.drawable.save, "A Teenagers Guide To Building Self Confidence"));
        recommendedBlogItems.add(new RecommendedBlogItems(R.drawable.blog1, R.drawable.save, "Dominance Of Partner ??? Controlling..."));
        recommendedBlogItems.add(new RecommendedBlogItems(R.drawable.blog3, R.drawable.save, "A Teenagers Guide To Building Self Confidence"));
        recommendedBlogItems.add(new RecommendedBlogItems(R.drawable.blog2, R.drawable.save, "Dominance Of Partner ??? Controlling..."));

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_recommended_blogs.setLayoutManager(linearLayoutManager1);

        adapter2 = new RecommendedBlogAdapter(context ,recommendedBlogItems);
        recy_recommended_blogs.setAdapter(adapter2);

        return view;
    }
}