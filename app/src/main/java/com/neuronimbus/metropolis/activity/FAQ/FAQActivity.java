package com.neuronimbus.metropolis.activity.FAQ;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.Utils.LocaleHelper;
import com.neuronimbus.metropolis.adapters.FAQAdapter;
import com.neuronimbus.metropolis.AuthModels.FAQContentAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.FAQCategoryResponse;
import com.neuronimbus.metropolis.Models.FAQContentResponse;
import com.neuronimbus.metropolis.Models.faqcategory;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class FAQActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    RelativeLayout rel_back, rel_category_selection, selection_button_rl;
    ImageView selection_category;
    TextView tv_category_selection;
    RecyclerView faq_category_rv;
    ArrayList<faqcategory>faqcategory;
    ArrayList<com.neuronimbus.metropolis.Models.faqcontent>faqcontent;
    FAQAdapter faqAdapter;
    int selectedPosition=0;
    String[] listItems ;
    boolean[] checkedItems;
    Spinner spinner_blogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = FAQActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        selection_button_rl = findViewById(R.id.selection_button_rl);
        selection_button_rl.setOnClickListener(this);
        tv_category_selection = findViewById(R.id.tv_category_selection);
        selection_category= findViewById(R.id.selection_category);
        tv_category_selection = findViewById(R.id.tv_category_selection);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);

        faqcontent = new ArrayList<>();

        faq_category_rv = findViewById(R.id.faq_category_rv);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        faq_category_rv.setLayoutManager(linearLayoutManager1);
        getFAQContent("");
        getFAQCategory();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }
    public void getFAQContent(String selectedCategory){
        dialog.show("");

        FAQContentAuthModel model = new FAQContentAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setCategory_id(selectedCategory);

        WebServiceModel.getRestApi().getFAQContent(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<FAQContentResponse>() {
                    @Override
                    public void onNext(FAQContentResponse faqContentResponse) {
                        String msg = faqContentResponse.getMsg();
                        dialog.dismiss("");
                        if (msg.equals("success")){
                            faqcontent = faqContentResponse.getFaqcontent();

                            faqAdapter = new FAQAdapter(context, faqcontent);
                            faq_category_rv.setAdapter(faqAdapter);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getFAQCategory(){

        WebServiceModel.getRestApi().getFAQCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<FAQCategoryResponse>() {
                    @Override
                    public void onNext(FAQCategoryResponse faqCategoryResponse) {
                        String msg = faqCategoryResponse.getMsg();

                        if (msg.equals("success")){
                            faqcategory = faqCategoryResponse.getFaqcategory();

                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void selectCategoryMethod() {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(FAQActivity.this);
        mBuilder.setTitle(R.string.all);
        //mBuilder.setCustomTitle(tv_category_selection);

        String [] categoryListForSingleItem = new String[faqcategory.size()];
        categoryListForSingleItem[0]="All";
        for (int i = 1; i<faqcategory.size(); i++){

            categoryListForSingleItem[i]=faqcategory.get(i).getTitle() ;

        }

        if (!faqcategory.isEmpty()){
            mBuilder.setSingleChoiceItems(categoryListForSingleItem, selectedPosition, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    selectedPosition=which;

                    getFAQContent(which==0?"":faqcategory.get(which-1).getId()+"");
                    if (which==0){
                        tv_category_selection.setText(categoryListForSingleItem[which]);
                    }else{
                        tv_category_selection.setText(categoryListForSingleItem[which]);
                    }

                    // when selected an item the dialog should be closed with the dismiss method
                    dialogInterface.dismiss();
                }
            });
            mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    //set the recyclerview according to check box list update
                    // first find the data and then set recycler view
                    dialogInterface.dismiss();

                }
            });

            mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
        }else{
            mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
//               checkedItem [0] = which;

                    // now also update the TextView which previews the selected item
                    tv_category_selection.setText(listItems[which]);

                    // when selected an item the dialog should be closed with the dismiss method
                    dialogInterface.dismiss();
                }
            });
            mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    //set the recyclerview according to check box list update
                    // first find the data and then set recycler view
                    dialogInterface.dismiss();

                }
            });

            mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

        }

        mBuilder.setCancelable(false);

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == rel_back.getId()){
            finish();
        }
        else if (id == selection_button_rl.getId()){
            selectCategoryMethod();
        }
    }
}