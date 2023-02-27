package com.example.tooshytoask.Activity.Game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;

public class GameMainPageActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout rel_leader_board,rel_help,rel_back;
    Context context;
    TextView txt_name;
    Button btn_play_now;
    CustomProgressDialog dialog;
    SPManager spManager;
    TextView txt_best_score,txt_overall_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main_page);
        getWindow().setStatusBarColor(getResources().getColor(R.color.purple));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context=GameMainPageActivity.this;
        spManager=new SPManager(context);
        dialog=new CustomProgressDialog(context);

        txt_name=(TextView)findViewById(R.id.txt_name);
        txt_best_score=(TextView)findViewById(R.id.txt_best_score);
        txt_overall_score=(TextView)findViewById(R.id.txt_overall_score);
        rel_back=(RelativeLayout)findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);

        rel_leader_board=(RelativeLayout)findViewById(R.id.rel_leader_board);
        rel_leader_board.setOnClickListener(this);
        rel_help=(RelativeLayout)findViewById(R.id.rel_help);
        rel_help.setOnClickListener(this);

        btn_play_now=(Button)findViewById(R.id.btn_play_now);
        btn_play_now.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        if(id==rel_back.getId())
        {
            finish();
        }
        else if(id==rel_leader_board.getId())
        {
            Intent intent = new Intent(context, LeaderBoardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        else if(id==btn_play_now.getId())
        {
            Intent intent = new Intent(context, GameHomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
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