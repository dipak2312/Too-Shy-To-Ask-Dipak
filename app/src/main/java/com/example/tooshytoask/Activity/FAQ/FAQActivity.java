package com.example.tooshytoask.Activity.FAQ;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Activity.Blogs.AllBlogsActivity;
import com.example.tooshytoask.AuthModels.FAQContentAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.FAQCategoryResponse;
import com.example.tooshytoask.Models.FAQContentResponse;
import com.example.tooshytoask.Models.OnboardingList;
import com.example.tooshytoask.Models.faqcategory;
import com.example.tooshytoask.Models.faqcontent;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;

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
    ArrayList<com.example.tooshytoask.Models.faqcontent>faqcontent;
    String[] listItems ;

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
        faq_category_rv = findViewById(R.id.faq_category_rv);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);

        getFAQCategory();
    }

    public void getFAQContent(String selectedCategory){
        dialog.show("");
        dialog.dismiss("");

        FAQContentAuthModel model = new FAQContentAuthModel();
        model.setCategory_id(selectedCategory);

        WebServiceModel.getRestApi().getFAQContent(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<FAQContentResponse>() {
                    @Override
                    public void onNext(FAQContentResponse faqContentResponse) {
                        String msg = faqContentResponse.getMsg();

                        if (msg.equals("success")){
                            faqcontent = faqContentResponse.getFaqcontent();
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
        dialog.show("");
        dialog.dismiss("");

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
        //AlertDialog
//


        AlertDialog.Builder mBuilder = new AlertDialog.Builder(FAQActivity.this);
        mBuilder.setTitle("All Category");

        String [] categoryListForSingleItem = new String[faqcategory.size()];
        for (int i = 0; i<faqcategory.size(); i++){
//            categoryListForSingleItem[i]= categoryList.get(i).toString();

            //categoryListForSingleItem[i]=responseAllCategory.getCategories().get(i).getName() ;

        }

        if (!faqcategory.isEmpty()){
            mBuilder.setSingleChoiceItems(categoryListForSingleItem, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
//               checkedItem [0] = which;

                    // now also update the TextView which previews the selected item

                    getFAQContent(categoryListForSingleItem[which]);
                    if (which==0){
//                        getCategoriesData("all");
                        tv_category_selection.setText(categoryListForSingleItem[which]+"Categories");
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