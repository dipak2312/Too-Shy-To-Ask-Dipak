package com.neuronimbus.metropolis.activity.InformationStoreHouse;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.Utils.LocaleHelper;
import com.neuronimbus.metropolis.activity.Bookmark.BookmarkActivity;
import com.neuronimbus.metropolis.adapters.StoreHouseListingAdapter;
import com.neuronimbus.metropolis.AuthModels.AllStoreHouseAuthModel;
import com.neuronimbus.metropolis.AuthModels.BookmarkBlogAuthModel;
import com.neuronimbus.metropolis.AuthModels.StoreHouseCategoryAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.BookmarkBlogResponse;
import com.neuronimbus.metropolis.Models.StoreHouse.AllStoreHouseResponse;
import com.neuronimbus.metropolis.Models.StoreHouse.CategoryData.StoreHouseCategoryResponse;
import com.neuronimbus.metropolis.Models.StoreHouse.InfoStoreCategory;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.Utils.OnBookmarkClicked;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class InformationStorehouseActivity extends AppCompatActivity implements View.OnClickListener, OnBookmarkClicked {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    TextView storehouse_title, tv_category_selection;
    ImageView bookmark_blog, selection_category;
    RelativeLayout rel_back;
    CardView categories;
    RecyclerView storehouse_recy;
    StoreHouseListingAdapter adapter;
    ArrayList<com.neuronimbus.metropolis.Models.StoreHouse.CategoryData.data>dataList;
    ArrayList<com.neuronimbus.metropolis.Models.StoreHouse.InfoStoreCategory>InfoStoreCategory;
    String title_id = "", type = "storehouse", actions = "";
    int selectedPosition=0;
    String[] listItems ;
    boolean[] checkedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_storehouse);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = InformationStorehouseActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        storehouse_title = findViewById(R.id.storehouse_title);
        bookmark_blog = findViewById(R.id.bookmark_blog);
        bookmark_blog.setOnClickListener(this);
        selection_category = findViewById(R.id.selection_category);
        categories = findViewById(R.id.categories);
        categories.setOnClickListener(this);
        tv_category_selection = findViewById(R.id.tv_category_selection);

        dataList = new ArrayList<>();
        InfoStoreCategory = new ArrayList<>();

        storehouse_recy = findViewById(R.id.storehouse_recy);
        storehouse_recy.setLayoutManager(new GridLayoutManager(context,2, GridLayoutManager.VERTICAL, false));

        title_id = getIntent().getStringExtra("title_id");

        //listItems = getResources().getStringArray(R.array.category_list);
        //checkedItems = new boolean[listItems.length];
        getStoreHouseCategory("");
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }
    public void getAllStoreHouse(){
        dialog.show("");

        AllStoreHouseAuthModel model = new AllStoreHouseAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setTitle_id(title_id);

        WebServiceModel.getRestApi().getAllStoreHouse(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<AllStoreHouseResponse>() {
                    @Override
                    public void onNext(AllStoreHouseResponse allStoreHouseResponse) {
                        String msg = allStoreHouseResponse.getMsg();
                        dialog.dismiss("");
                        if (msg.equals("success")){

                            InfoStoreCategory = allStoreHouseResponse.getInfoStoreCategory();

                            storehouse_title.setText(Html.fromHtml(allStoreHouseResponse.getTitlename()));

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

    public void getBookmarkBlogs(String action){
        dialog.show("");
        dialog.dismiss("");

        BookmarkBlogAuthModel model = new BookmarkBlogAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setPost_id(title_id);
        model.setType(type);
        model.setAction(action);

        WebServiceModel.getRestApi().getBookmarkBlogs(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<BookmarkBlogResponse>() {
                    @Override
                    public void onNext(BookmarkBlogResponse bookmarkBlogResponse) {
                        String msg = bookmarkBlogResponse.getMsg();
                        dialog.dismiss("");

                        if (msg.equals("Article Bookmarked")) {

                        }
                        else {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getStoreHouseCategory(String category){
        dialog.show("");

        StoreHouseCategoryAuthModel model = new StoreHouseCategoryAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setCategory_id(category);
        model.setTitle_id(title_id);

        WebServiceModel.getRestApi().getStoreHouseCategory(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<StoreHouseCategoryResponse>() {
                    @Override
                    public void onNext(StoreHouseCategoryResponse storeHouseCategoryResponse) {
                        String msg = storeHouseCategoryResponse.getMsg();
                        dialog.dismiss("");
                        if (msg.equals("success")){

                            dataList.clear();
                            dataList = storeHouseCategoryResponse.getData();

                            setUpViews(dataList,InfoStoreCategory);

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

    private void setUpViews(ArrayList<com.neuronimbus.metropolis.Models.StoreHouse.CategoryData.data>data, ArrayList<InfoStoreCategory>InfoStoreCategory) {
        adapter = new StoreHouseListingAdapter(context, data, this);
        storehouse_recy.setAdapter(adapter);
        getAllStoreHouse();

    }

    private void selectCategoryMethod() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(InformationStorehouseActivity.this);
        mBuilder.setTitle(R.string.all_categories);

        String [] categoryListForSingleItem = new String[InfoStoreCategory.size()+1];
        categoryListForSingleItem[0]= getResources().getString(R.string.all_categories);
        for (int i = 0; i<InfoStoreCategory.size(); i++){
            categoryListForSingleItem[i+1]=InfoStoreCategory.get(i).getCategory_name() ;
        }

        if (!InfoStoreCategory.isEmpty()){
            mBuilder.setSingleChoiceItems(categoryListForSingleItem, selectedPosition, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
//               checkedItem [0] = which;
                    selectedPosition=which;
                    // now also update the TextView which previews the selected item
                    getStoreHouseCategory(which==0?"":InfoStoreCategory.get(which-1).getCategory_id()+"");
                    if (which==0){
//                        getCategoriesData("all");
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

        if (id == categories.getId()){
            selectCategoryMethod();
        }
        else if (id == rel_back.getId()){
            finish();
        }
        else if (id == bookmark_blog.getId()){
            Intent intent = new Intent(context, BookmarkActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }

    @Override
    public void onBookmarkButtonClick(int position, String Blog_id, String action) {
        title_id = Blog_id;
        actions = action;
        getBookmarkBlogs(action);

    }
}