package com.example.tooshytoask.Activity.InformationStoreHouse;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Activity.Blogs.AllBlogsActivity;
import com.example.tooshytoask.Activity.Blogs.AllEventActivity;
import com.example.tooshytoask.Adapters.AllBlogAdapter;
import com.example.tooshytoask.Adapters.CategoryAdapter;
import com.example.tooshytoask.Adapters.StoreHouseListingAdapter;
import com.example.tooshytoask.AuthModels.AllStoreHouseAuthModel;
import com.example.tooshytoask.AuthModels.BookmarkBlogAuthModel;
import com.example.tooshytoask.AuthModels.StoreHouseCategoryAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.BookmarkBlogResponse;
import com.example.tooshytoask.Models.StoreHouse.AllStoreHouseResponse;
import com.example.tooshytoask.Models.StoreHouse.CategoryData.StoreHouseCategoryResponse;
import com.example.tooshytoask.Models.StoreHouse.InfoStoreCategory;
import com.example.tooshytoask.Models.StoreHouse.data;
import com.example.tooshytoask.Models.articleblogs;
import com.example.tooshytoask.Models.insightblogcategories;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.example.tooshytoask.Utils.OnBookmarkClicked;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class InformationStorehouseActivity extends AppCompatActivity implements View.OnClickListener, OnBookmarkClicked {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    TextView storehouse_title, tv_category_selection;
    ImageView bookmark_blog, selection_category;
    RelativeLayout categories, rel_back;
    RecyclerView storehouse_recy;
    StoreHouseListingAdapter adapter;
    ArrayList<com.example.tooshytoask.Models.StoreHouse.CategoryData.data>dataList;
    ArrayList<com.example.tooshytoask.Models.StoreHouse.InfoStoreCategory>InfoStoreCategory;
    String title_id = "", type = "storehouse", actions = "";
    OnBookmarkClicked onBookmarkClicked;
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

        listItems = getResources().getStringArray(R.array.category_list);
        checkedItems = new boolean[listItems.length];

        getStoreHouseCategory("");
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

    private void setUpViews(ArrayList<com.example.tooshytoask.Models.StoreHouse.CategoryData.data>data, ArrayList<InfoStoreCategory>InfoStoreCategory) {
        adapter = new StoreHouseListingAdapter(context, data, this);
        storehouse_recy.setAdapter(adapter);
        getAllStoreHouse();

    }

    private void selectCategoryMethod() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(InformationStorehouseActivity.this);
        mBuilder.setTitle("All Category");

        String [] categoryListForSingleItem = new String[InfoStoreCategory.size()+1];
        categoryListForSingleItem[0]="All Categories";
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

    }

    @Override
    public void onBookmarkButtonClick(int position, String Blog_id, String action) {
        title_id = Blog_id;
        actions = action;
        getBookmarkBlogs(action);

    }
}