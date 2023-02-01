package com.example.tooshytoask.Fragment.InfoCard;

import android.content.Context;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Adapters.CategoryAdapter;
import com.example.tooshytoask.Adapters.HealthAdapter;
import com.example.tooshytoask.AuthModels.HealthCateModel;
import com.example.tooshytoask.AuthModels.HealthIssueModel;
import com.example.tooshytoask.AuthModels.SaveHealthCateAuthModel;
import com.example.tooshytoask.AuthModels.SaveHealthIssueAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.HealthCateResponse;
import com.example.tooshytoask.Models.HealthIssueResponse;
import com.example.tooshytoask.Models.HealthIssuseList;
import com.example.tooshytoask.Models.SaveHealthCategoryResponse;
import com.example.tooshytoask.Models.SaveHealthIssueResponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.ClickListener;
import com.example.tooshytoask.Utils.OnClickListner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ThreeFragment extends Fragment implements View.OnClickListener, OnClickListner{
    Context context;
    SPManager spManager;
    RecyclerView health_recy;
    ArrayList<HealthIssuseList>HealthIssuseList;
    HealthAdapter adapter;
    ClickListener clickListener;
    LinearLayout health_issues_title;
    Button yes_btn, no_btn;
    ImageButton next_btn;
    TextView skip_btn;
    OnClickListner onclicklistener;

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

        /*healthIssuseList = new ArrayList<>();

        healthIssuseList.add(new HealthIssuseList("Throid", false));
        healthIssuseList.add(new HealthIssuseList("Painful Periods", false));
        healthIssuseList.add(new HealthIssuseList("Mental Health", false));
        healthIssuseList.add(new HealthIssuseList("Irregular Periods", false));
        healthIssuseList.add(new HealthIssuseList("Fibroids", false));
        healthIssuseList.add(new HealthIssuseList("PCOS/PCOD", false));*/

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        health_recy.setLayoutManager(linearLayoutManager1);

        healthIssues();
        return view;
    }

    public void healthIssues() {

        HealthIssueModel model = new HealthIssueModel();
        model.setUser_id(spManager.getUserId());
        WebServiceModel.getRestApi().healthIssues(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<HealthIssueResponse>() {
                    @Override
                    public void onNext(HealthIssueResponse healthIssueResponse) {

                        String msg = healthIssueResponse.getMsg();

                        if (msg.equals("success")) {

                            HealthIssuseList = healthIssueResponse.getHealthIssuseList();

                            adapter = new HealthAdapter(HealthIssuseList,onclicklistener, context);
                            health_recy.setAdapter(adapter);

                        } else {

                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        Toast.makeText(context, "Please Check Your Network..Unable to Connect Server!!", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void saveHealthIssue() {

        SaveHealthIssueAuthModel model = new SaveHealthIssueAuthModel();
        model.setUserId(spManager.getUserId());
        model.setHealthissueId(model.getHealthissueId());
        WebServiceModel.getRestApi().saveHealthIssue(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SaveHealthIssueResponse>() {
                    @Override
                    public void onNext(SaveHealthIssueResponse saveHealthIssueResponse) {

                        String msg = saveHealthIssueResponse.getMsg();

                        if (msg.equals("Health Issues updated to profile.")) {




                        } else {

                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        Toast.makeText(context, "Please Check Your Network..Unable to Connect Server!!", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();



        if (id == skip_btn.getId()){
            clickListener.onClick(false);
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
            for (int i = 0; i < HealthIssuseList.size(); i++) {
                myvalue.add(HealthIssuseList.get(i).getSelected());
            }
            boolean ans = myvalue.contains(true);

            if (ans) {
                saveHealthIssue();
                clickListener.onClick(true);


            } {
                clickListener.onClick(false);
            }
        }

    }

    @Override
    public void onClickData(int position, int id) {

        ArrayList<Boolean> myvalue=new ArrayList<Boolean>();

        for(int i=0;i<HealthIssuseList.size();i++)
        {
            myvalue.add(HealthIssuseList.get(i).getSelected());
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