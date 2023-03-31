package com.example.tooshytoask.activity.Expert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.adapters.ChattingAdapter;
import com.example.tooshytoask.AuthModels.AskQuestionsAuthModel;
import com.example.tooshytoask.AuthModels.ExpertReplyAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.AskQuestionsResponse;
import com.example.tooshytoask.Models.ExpertReplyResponse;
import com.example.tooshytoask.Models.chats;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ExpertActivity extends AppCompatActivity implements View.OnClickListener{
    SPManager spManager;
    Context context;
    CustomProgressDialog dialog;
    RelativeLayout rel_back, send_msg;
    RecyclerView recy_user_msg;
    EditText ask_questions;
    ChattingAdapter adapter;
    ArrayList<chats> chats;
    ArrayList<chats> demochat;
    SwipeRefreshLayout swipe_refresh;
    String refresh_status = "open", chat_ref_status = "no";
    int chatSize, select_chat_size = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = ExpertActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        swipe_refresh = findViewById(R.id.swipe_refresh);
        ask_questions = findViewById(R.id.ask_questions);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        send_msg = findViewById(R.id.send_msg);
        send_msg.setOnClickListener(this);

        chats = new ArrayList<>();
        demochat = new ArrayList<>();

        recy_user_msg = findViewById(R.id.recy_user_msg);
        LinearLayoutManager lm = new LinearLayoutManager(context);
        lm.setOrientation(RecyclerView.VERTICAL);
        recy_user_msg.setLayoutManager(lm);
        recy_user_msg.getLayoutManager().scrollToPosition(chats.size() - 1);


        swipe_refresh.setColorSchemeResources(R.color.purple);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getExpertReply("swipe");
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh_status = "open";
        getExpertReply("w_swipe");
        chat_ref_status = "no";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        refresh_status = "close";
    }

    @Override
    protected void onPause() {
        super.onPause();
        refresh_status = "close";
        //Toast.makeText(context,"onPause",Toast.LENGTH_SHORT).show();
    }


    public void getExpertReply(String refresh){

        ExpertReplyAuthModel model = new ExpertReplyAuthModel();
        model.setUser_id(spManager.getUserId());

        if (refresh.equals("w_swipe")) {
            dialog.show("");
        }

        WebServiceModel.getRestApi().getExpertReply(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ExpertReplyResponse>() {
                    @Override
                    public void onNext(ExpertReplyResponse expertReplyResponse) {
                        if (refresh.equals("w_swipe")) {
                            dialog.dismiss("");
                        } else {
                            swipe_refresh.setRefreshing(false);
                        }

                        String msg = expertReplyResponse.getMsg();


                        if (msg.equals("success")){
                            chats = expertReplyResponse.getChats();

                            if (chats.size() != 0){
                                swipe_refresh.setVisibility(View.VISIBLE);
                                adapter = new ChattingAdapter(context, chats);
                                recy_user_msg.setAdapter(adapter);
                                recy_user_msg.getLayoutManager().smoothScrollToPosition(recy_user_msg, null, adapter.getItemCount() - 1);
                            }
                            if (chats.size() == 0){
                                recy_user_msg.setVisibility(View.GONE);
                            }

                            if (chatSize != select_chat_size) {
                                select_chat_size = chatSize;
                                chats = demochat;


                                if(chat_ref_status.equals("no")) {
                                    adapter = new ChattingAdapter(context, chats);
                                    recy_user_msg.setAdapter(adapter);
                                    recy_user_msg.getLayoutManager().smoothScrollToPosition(recy_user_msg, null, adapter.getItemCount() - 1);
                                }
                                else {
                                    chat_ref_status="no";
                                }
                            }
                        }
                        else {

                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Please Check Your Network..Unable to Connect Server!!", Toast.LENGTH_SHORT).show();
                        if (refresh.equals("w_swipe")) {
                            dialog.dismiss("");
                        } else {
                            swipe_refresh.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    public void getuserReply(){

        ExpertReplyAuthModel model = new ExpertReplyAuthModel();
        model.setUser_id(spManager.getUserId());

        WebServiceModel.getRestApi().getExpertReply(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ExpertReplyResponse>() {
                    @Override
                    public void onNext(ExpertReplyResponse expertReplyResponse) {
                        String msg = expertReplyResponse.getMsg();

                        if (msg.equals("success")){
                            chats = expertReplyResponse.getChats();

                            adapter = new ChattingAdapter(context, chats);
                            recy_user_msg.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            recy_user_msg.getLayoutManager().smoothScrollToPosition(recy_user_msg, null, adapter.getItemCount() - 1);

                        }
                        else {

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
    public void getAskQuestions(String message){

        AskQuestionsAuthModel model = new AskQuestionsAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setReply(message);

        WebServiceModel.getRestApi().getAskQuestions(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<AskQuestionsResponse>() {
                    @Override
                    public void onNext(AskQuestionsResponse askQuestionsResponse) {
                        String msg = askQuestionsResponse.getMsg();

                        if (msg.equals("success")){
                            getuserReply();
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

    public void sendMessage() {

        /*if (ask_questions.getText().toString().trim().equals("")) {
            Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
        }*/
        if (!ask_questions.getText().toString().trim().equals("")) {
            String message = ask_questions.getText().toString().trim();

            if (adapter != null) {
                adapter.notifyDataSetChanged();
                recy_user_msg.getLayoutManager().smoothScrollToPosition(recy_user_msg, null, adapter.getItemCount() - 1);
                getAskQuestions(message);
                chat_ref_status="yes";
                ask_questions.setText("");
            }
            else  if (adapter == null) {

                getAskQuestions(message);
                chat_ref_status="yes";
                ask_questions.setText("");
            }
            else {
                chat_ref_status="no";
                getExpertReply("w_swipe");
            }
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == rel_back.getId()){
            finish();
        }
        else if (id == send_msg.getId()){
            sendMessage();
        }

    }
}