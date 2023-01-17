package com.example.tooshytoask.Fragment.InfoCard;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tooshytoask.Adapters.CategoryAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Utils.ClickListener;
import com.example.tooshytoask.Models.CategoryItem;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.OnClickListner;

import java.util.ArrayList;

public class OneFragment extends Fragment implements View.OnClickListener, View.OnTouchListener, OnClickListner, ClickListener {
    Context context;
    SPManager spManager;
    RecyclerView recyclerView, category_recy;
    CategoryAdapter adapter;
    ArrayList<CategoryItem> categoryItem;
    TextView skip_btn;
    ImageButton next_btn;
    ClickListener clickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        context = getActivity();
        spManager = new SPManager(context);
        next_btn = view.findViewById(R.id.btnNext);
        next_btn.setOnClickListener(this);
        skip_btn = view.findViewById(R.id.skip_btn);
        skip_btn.setOnClickListener(this);
        clickListener=(ClickListener)context;
        clickListener.onClick(false);

        recyclerView = view.findViewById(R.id.category_recy);
        recyclerView.setOnTouchListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(context,3, GridLayoutManager.VERTICAL, false));

        categoryItem = new ArrayList<>();

        categoryItem.add(new CategoryItem(R.drawable.reproduction,"Relationships",false));
        categoryItem.add(new CategoryItem(R.drawable.mental_health,"Sex & Sexuality",false));
        categoryItem.add(new CategoryItem(R.drawable.reproduction,"Reproduction",false));
        categoryItem.add(new CategoryItem(R.drawable.mental_health,"Mental Health",false));
        categoryItem.add(new CategoryItem(R.drawable.reproduction,"Education",false));
        categoryItem.add(new CategoryItem(R.drawable.mental_health,"Sexual Assault",false));

        recyclerView.setAdapter(new CategoryAdapter(categoryItem,this, clickListener));

    return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == skip_btn.getId()){
            clickListener.onClick(true);
        }

        ArrayList<Boolean> myvalue=new ArrayList<Boolean>();

        for(int i=0;i<categoryItem.size();i++)
        {
            myvalue.add(categoryItem.get(i).getSelected());
        }
        boolean ans = myvalue.contains(true);

        if(ans)
        {
            clickListener.onClick(true);


        }else
        {
            clickListener.onClick(false);
        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        return true;
    }

    @Override
    public void onClickData(int position, int id) {


      ArrayList<Boolean> myvalue=new ArrayList<Boolean>();

        for(int i=0;i<categoryItem.size();i++)
        {
            myvalue.add(categoryItem.get(i).getSelected());
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

    @Override
    public void onClick(Boolean status) {

    }
}