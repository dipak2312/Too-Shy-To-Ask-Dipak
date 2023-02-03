package com.example.tooshytoask.Activity.Help;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Adapters.Help2Adapter;
import com.example.tooshytoask.Adapters.Help3Adapter;
import com.example.tooshytoask.Adapters.HelpCategoryAdapter;
import com.example.tooshytoask.AuthModels.HelpSubCategoryAuthModel;
import com.example.tooshytoask.AuthModels.HomeScreenAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.Help.HelpSubCategoryResponse;
import com.example.tooshytoask.Models.Help.helpsubcategory;
import com.example.tooshytoask.Models.Help2Item;
import com.example.tooshytoask.Models.Help3Item;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class HelpActivity2 extends AppCompatActivity implements View.OnClickListener{
    RecyclerView using_tsta_recy, content_related_recy;
    SPManager spManager;
    Context context;
    Help2Adapter help2Adapter;
    Help3Adapter help3Adapter;
    ArrayList<Help2Item>help2Item;
    ArrayList<helpsubcategory>helpsubcategory;
    ArrayList<Help3Item>help3Item;
    RelativeLayout rel_back;
    Button contact;
    CustomProgressDialog dialog;
    TextView txt_title, help_title, content_title;
    String category_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help2);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = HelpActivity2.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        txt_title = findViewById(R.id.txt_title);
        content_title = findViewById(R.id.content_title);
        help_title = findViewById(R.id.help_title);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        using_tsta_recy = findViewById(R.id.using_tsta_recy);
        contact = findViewById(R.id.contact);
        contact.setOnClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        using_tsta_recy.setLayoutManager(linearLayoutManager);

        /*help2Item = new ArrayList<>();

        help2Item.add(new Help2Item(R.drawable.update_arrow,"Suspendisse condimentum nisl vitae tellus?"));
        help2Item.add(new Help2Item(R.drawable.update_arrow,"Morbi blandit elit et urna placerat?"));
        help2Item.add(new Help2Item(R.drawable.update_arrow,"Sed auctor justo id dictum sodales.?"));
        help2Item.add(new Help2Item(R.drawable.update_arrow,"Suspendisse ut leo at libero cursus iaculis.?"));
        help2Item.add(new Help2Item(R.drawable.update_arrow,"Cras vitae diam eu quam tincidunt semper.?"));
        help2Item.add(new Help2Item(R.drawable.update_arrow,"Suspendisse condimentum nisl vitae tellus?"));
        help2Item.add(new Help2Item(R.drawable.update_arrow,"Mauris fringilla justo in arcu facilisis?"));*/



        content_related_recy = findViewById(R.id.content_related_recy);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        content_related_recy.setLayoutManager(linearLayoutManager1);

       Intent intent = getIntent();
        if (intent != null) {

            category_id = intent.getStringExtra("category_id");

        }
        getHelpSubCategory();

        /*help3Item = new ArrayList<>();

        help3Item.add(new Help3Item(R.drawable.update_arrow,"Suspendisse condimentum nisl vitae tellus?"));
        help3Item.add(new Help3Item(R.drawable.update_arrow,"Morbi blandit elit et urna placerat?"));
        help3Item.add(new Help3Item(R.drawable.update_arrow,"Sed auctor justo id dictum sodales.?"));
        help3Item.add(new Help3Item(R.drawable.update_arrow,"Suspendisse ut leo at libero cursus iaculis.?"));
        help3Item.add(new Help3Item(R.drawable.update_arrow,"Cras vitae diam eu quam tincidunt semper.?"));
        help3Item.add(new Help3Item(R.drawable.update_arrow,"Suspendisse condimentum nisl vitae tellus?"));
        help3Item.add(new Help3Item(R.drawable.update_arrow,"Mauris fringilla justo in arcu facilisis?"));*/

        //content_related_recy.setAdapter(new Help2Adapter(help2Item, context));
    }

    public void getHelpSubCategory() {
        dialog.show("");
        dialog.dismiss("");

        HelpSubCategoryAuthModel model = new HelpSubCategoryAuthModel();
        model.setCategory_id(category_id);

        WebServiceModel.getRestApi().getHelpSubCategory(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<HelpSubCategoryResponse>() {
                    @Override
                    public void onNext(HelpSubCategoryResponse helpSubCategoryResponse) {
                        String msg = helpSubCategoryResponse.getMsg();
                        if (msg.equals("success")) {

                            helpsubcategory = helpSubCategoryResponse.getHelpsubcategory();
                            //helpsubcategory = helpSubCategoryResponse.getContent();

                            if (helpsubcategory != null) {

                                help2Adapter = new Help2Adapter(helpsubcategory, context);
                                using_tsta_recy.setAdapter(help2Adapter);
                            }
                            txt_title.setText(helpSubCategoryResponse.getHelpcategory());
                            //help_title.setText(helpsubcategory.get(0).getTitle());
                            //content_title.setText(helpsubcategory.get(1).getTitle());


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

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == rel_back.getId()){
            Intent intent = new Intent(context, HelpActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        else  if (id == contact.getId()){
            Intent intent = new Intent(context, ContactUsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }
}