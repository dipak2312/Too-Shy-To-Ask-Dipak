package com.example.tooshytoask.Activity.Game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;

import java.util.ArrayList;

public class LeaderBoardActivity extends AppCompatActivity implements View.OnClickListener{
    //LeaderboardFirstAdapter firstAdapter;
    //LeaderboardSecondAdapter secondAdapter;
    RecyclerView recy_first_list, recy_second_list;
    Context context;
    CustomProgressDialog dialog;
    //ArrayList<TopThreeList> firstList;
    //ArrayList<TopTenList> secondList;
    RelativeLayout rel_back;
    RelativeLayout rel_help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        getWindow().setStatusBarColor(getResources().getColor(R.color.purple));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = LeaderBoardActivity.this;

        recy_first_list = (RecyclerView) findViewById(R.id.recy_first_list);
        LinearLayoutManager lm = new LinearLayoutManager(context);
        lm.setOrientation(RecyclerView.VERTICAL);
        recy_first_list.setLayoutManager(lm);
        recy_first_list.setNestedScrollingEnabled(true);
        recy_first_list.setOverScrollMode(View.OVER_SCROLL_NEVER);

        dialog = new CustomProgressDialog(context);
        //firstList = new ArrayList<>();
        //secondList = new ArrayList<>();

        rel_help=(RelativeLayout)findViewById(R.id.rel_help);
        rel_help.setOnClickListener(this);

        rel_back=(RelativeLayout)findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);

        recy_second_list = (RecyclerView) findViewById(R.id.recy_second_list);
        LinearLayoutManager lm1 = new LinearLayoutManager(context);
        lm1.setOrientation(RecyclerView.VERTICAL);
        recy_second_list.setLayoutManager(lm1);
        recy_second_list.setNestedScrollingEnabled(true);
        recy_second_list.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        if(id==rel_back.getId())
        {
            finish();
        }
        else if(id==rel_help.getId())
        {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.help_view);

            TextView txt_help=(TextView)dialog.findViewById(R.id.txt_help);
            txt_help.setText("To play Word game, you need to single tap the letters to create words, and double-tap to remove letters. In this game, you need to guess words. Refresh the letters on the screen and shuffle the letter's, guess the word correct in minimum time and win prizes!! THE WINNER'S WILL BE LISTED ON LEADERBOARD PLAY HELP LEADERBOARD");


            dialog.show();
        }
    }
}