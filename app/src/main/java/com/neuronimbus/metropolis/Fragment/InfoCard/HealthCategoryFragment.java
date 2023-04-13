package com.neuronimbus.metropolis.Fragment.InfoCard;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.adapters.CategoryAdapter;
import com.neuronimbus.metropolis.AuthModels.HealthCateModel;
import com.neuronimbus.metropolis.AuthModels.SaveHealthCateAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.HealthCateResponse;
import com.neuronimbus.metropolis.Models.InformationStorehouseList;
import com.neuronimbus.metropolis.Models.SaveHealthCategoryResponse;
import com.neuronimbus.metropolis.Utils.ClickListener;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.Utils.OnClickListner;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class HealthCategoryFragment extends Fragment implements View.OnClickListener, View.OnTouchListener, OnClickListner, ClickListener {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    RecyclerView category_recy;
    CategoryAdapter adapter;
    ArrayList<InformationStorehouseList>informationStorehouseList;
    TextView skip_btn;
    ImageButton next_btn;
    ClickListener clickListener;
    String healthId="";
    ArrayList<String>helthIds=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        context = getActivity();
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);
        next_btn = view.findViewById(R.id.btnNext);
        next_btn.setOnClickListener(this);
        skip_btn = view.findViewById(R.id.skip_btn);
        skip_btn.setOnClickListener(this);

        clickListener=(ClickListener)context;
        clickListener.onClick(false);

        category_recy = view.findViewById(R.id.category_recy);
        category_recy.setOnTouchListener(this);

        category_recy.setLayoutManager(new GridLayoutManager(context,3, GridLayoutManager.VERTICAL, false));

        healthcategory();

    return view;
    }

    public void healthcategory() {
        dialog.show("");

        HealthCateModel model = new HealthCateModel();
        model.setUser_id(spManager.getUserId());
        WebServiceModel.getRestApi().healthcategory(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<HealthCateResponse>() {
                    @Override
                    public void onNext(HealthCateResponse healthCateResponse) {

                        String msg = healthCateResponse.getMsg();

                        if (msg.equals("success")) {

                                informationStorehouseList = healthCateResponse.getInformationStorehouseList();

                                for(int i=0;i<informationStorehouseList.size();i++)
                                {
                                    informationStorehouseList.get(i).isSelected=false;
                                }


                            if (informationStorehouseList != null) {
                                CallAdapter();
                            }

                        } else {

                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                        }
                        dialog.dismiss("");
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

    public void CallAdapter()

    {
        adapter = new CategoryAdapter(informationStorehouseList, context,this);
        category_recy.setAdapter(adapter);
    }

    public void main(String[] args){

    }

    public void saveHealthCategory() {
        dialog.show("");

        SaveHealthCateAuthModel model = new SaveHealthCateAuthModel();
        model.setUserId(spManager.getUserId());
        model.setHealth_id(helthIds);

        WebServiceModel.getRestApi().saveHealthCategory(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SaveHealthCategoryResponse>() {
                    @Override
                    public void onNext(SaveHealthCategoryResponse saveHealthCategoryResponse) {

                        String msg = saveHealthCategoryResponse.getMsg();

                        if (msg.equals("Health Categories updated to profile.")) {


                        } else {

                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                        }
                        dialog.dismiss("");

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
            clickListener.onClick(true);
        }
        else if (id == next_btn.getId()){

        }

        ArrayList<Boolean> myvalue=new ArrayList<Boolean>();

        for(int i=0;i<informationStorehouseList.size();i++)
        {
            myvalue.add(informationStorehouseList.get(i).getSelected());
        }
        boolean ans = myvalue.contains(true);

        if(ans)
        {

            saveHealthCategory();
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
    public void onClickData(int position, String id) {
        healthId = id;
        if(informationStorehouseList.get(position).isSelected)
        {
            helthIds.add(healthId);
        }else
        {
            helthIds.remove(healthId);
        }
        Log.d("saggi",helthIds.toString());

      ArrayList<Boolean> myvalue=new ArrayList<Boolean>();

        for(int i=0;i<informationStorehouseList.size();i++)
        {
            myvalue.add(informationStorehouseList.get(i).getSelected());
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