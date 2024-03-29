package com.neuronimbus.metropolis.activity.Blogs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.LocaleHelper;
import com.neuronimbus.metropolis.activity.Bookmark.BookmarkActivity;
import com.neuronimbus.metropolis.adapters.AllBlogAdapter;
import com.neuronimbus.metropolis.AuthModels.AllBlogAuthModel;
import com.neuronimbus.metropolis.AuthModels.BlogCategoryAuthModel;
import com.neuronimbus.metropolis.AuthModels.BookmarkBlogAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.AllBlogResponse;
import com.neuronimbus.metropolis.Models.BlogCategoryResponse;
import com.neuronimbus.metropolis.Models.BookmarkBlogResponse;
import com.neuronimbus.metropolis.Models.insightblogcategories;
import com.neuronimbus.metropolis.Models.articleblogs;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.Utils.OnBookmarkClicked;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AllBlogsActivity extends AppCompatActivity implements View.OnClickListener, OnBookmarkClicked {
    RecyclerView blog_recy;
    SPManager spManager;
    Context context;
    RelativeLayout rel_back,selection_button_rl;
    AllBlogAdapter adapter;
    ImageView bookmark_blog;
    TextView tv_category_selection;
    ImageView selection_category;
    CustomProgressDialog dialog;
    ArrayList<articleblogs>insightblogsArrayList;
    ArrayList<com.neuronimbus.metropolis.Models.insightblogcategories>insightblogcategories;
    int selectedPosition=0;
    String[] listItems ;
    String blog_id = "", type = "blog",actions = "", category ="";
    boolean[] checkedItems;
    Spinner spinner_blogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_blogs);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = AllBlogsActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        spinner_blogs = findViewById(R.id.spinner_blogs);
        selection_button_rl = findViewById(R.id.selection_button_rl);
        selection_button_rl.setOnClickListener(this);
        selection_category = findViewById(R.id.selection_category);
        tv_category_selection = findViewById(R.id.tv_category_selection);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        bookmark_blog = findViewById(R.id.bookmark_blog);
        bookmark_blog.setOnClickListener(this);

        insightblogsArrayList = new ArrayList<>();
        insightblogcategories = new ArrayList<>();
        blog_recy = findViewById(R.id.blog_recy);
        blog_recy.setLayoutManager(new GridLayoutManager(context,2, GridLayoutManager.VERTICAL, false));

        listItems = getResources().getStringArray(R.array.category_list);
        checkedItems = new boolean[listItems.length];

        blog_id = getIntent().getStringExtra("blog_id");
        category = getIntent().getStringExtra("category");


        getAllBlogs(category);
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }
    public void getBookmarkBlogs(String action){

        BookmarkBlogAuthModel model = new BookmarkBlogAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setPost_id(blog_id);
        model.setType(type);
        model.setAction(action);

        WebServiceModel.getRestApi().getBookmarkBlogs(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<BookmarkBlogResponse>() {
                    @Override
                    public void onNext(BookmarkBlogResponse bookmarkBlogResponse) {
                        String msg = bookmarkBlogResponse.getMsg();

                        if (msg.equals("Article Bookmarked")) {

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

    public void getBlogCategory() {

        BlogCategoryAuthModel model = new BlogCategoryAuthModel();
        model.setUser_id(spManager.getUserId());

        WebServiceModel.getRestApi().getBlogCategory(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<BlogCategoryResponse>() {
                    @Override
                    public void onNext(BlogCategoryResponse blogCategoryResponse) {
                        String msg = blogCategoryResponse.getMsg();

                        if (msg.equals("success")) {
                            insightblogcategories = blogCategoryResponse.getInsightblogcategories();

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

    public void getAllBlogs(String category){
        dialog.show("");

        AllBlogAuthModel model = new AllBlogAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setCategory_id(category);

        WebServiceModel.getRestApi().getAllBlogs(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<AllBlogResponse>() {
                    @Override
                    public void onNext(AllBlogResponse allBlogResponse) {
                        String msg = allBlogResponse.getMsg();
                        dialog.dismiss("");
                        if (msg.equals("success")){
                            insightblogsArrayList.clear();
                            insightblogsArrayList = allBlogResponse.getArticleblogs();
                            setUpViews(insightblogsArrayList,insightblogcategories);

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
    private void setUpViews(ArrayList<articleblogs> insightblogs, List<insightblogcategories> insightblogcategories) {

        adapter = new AllBlogAdapter(context ,insightblogs, this);
        blog_recy.setAdapter(adapter);
        getBlogCategory();
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == rel_back.getId()) {
            finish();
        }
        else if (id == bookmark_blog.getId()) {
            Intent intent = new Intent(context, BookmarkActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else if (id == selection_button_rl.getId()){
            selectCategoryMethod();
        }

    }

    private void selectCategoryMethod() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(AllBlogsActivity.this);
        mBuilder.setTitle(R.string.all_categories);

        String [] categoryListForSingleItem = new String[insightblogcategories.size()+1];
        categoryListForSingleItem[0]=getResources().getString(R.string.all_categories);
        for (int i = 0; i<insightblogcategories.size(); i++){
            categoryListForSingleItem[i+1]=insightblogcategories.get(i).getCategory_title() ;
        }

        if (!insightblogcategories.isEmpty()){
            mBuilder.setSingleChoiceItems(categoryListForSingleItem, selectedPosition, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
//               checkedItem [0] = which;
                    selectedPosition=which;
                    // now also update the TextView which previews the selected item
                    getAllBlogs(which==0?"":insightblogcategories.get(which-1).getCategory_id()+"");
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
    public void onBookmarkButtonClick(int position, String Blog_id, String action) {
        blog_id = Blog_id;
        actions = action;
        getBookmarkBlogs(action);
    }
}