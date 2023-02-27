package com.example.tooshytoask.Activity.Game;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.example.tooshytoask.Utils.GameValueClick;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;

public class GameHomeActivity extends AppCompatActivity implements View.OnClickListener, GameValueClick {
    Context context;
    RecyclerView recy_select_text;
    //SelectTextAdapter selectAdapter;
    //ArrayList<AddChar> value;
    String name = "", name2 = "";
    GridLayoutManager layout;
    Dialog dialog;
    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;
    TextView show_timer, txt_level;
    String final_time;
    ImageView img_option, img_hint;
    PinView pinView, pinView2;
    String final_char = "";
    ArrayList<String> selectvalue = new ArrayList<>();
    CustomProgressDialog custdialog;
    //ArrayList<GetWordList> wordLists;
    String level = "1";
    int position = 0, set_status = 0;
    int multicharsize;
    SPManager spManager;
    RelativeLayout rel_quit_game;
    LinkedHashMap<Integer,String> linkedvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_home);
        getWindow().setStatusBarColor(getResources().getColor(R.color.purple));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = GameHomeActivity.this;
        recy_select_text = (RecyclerView) findViewById(R.id.recy_select_text);

        txt_level = (TextView) findViewById(R.id.txt_level);

        custdialog = new CustomProgressDialog(context);
        //wordLists = new ArrayList<>();
        linkedvalue=new LinkedHashMap<Integer, String>();

        rel_quit_game=(RelativeLayout)findViewById(R.id.rel_quit_game);
        rel_quit_game.setOnClickListener(this);

        spManager = new SPManager(context);

        img_option = (ImageView) findViewById(R.id.img_option);
        img_option.setOnClickListener(this);
        img_hint = (ImageView) findViewById(R.id.img_hint);
        img_hint.setOnClickListener(this);


        layout = new GridLayoutManager(context, 5, GridLayoutManager.VERTICAL, false);
        recy_select_text.setLayoutManager(layout);
        recy_select_text.setOverScrollMode(View.OVER_SCROLL_NEVER);

        show_timer = (TextView) findViewById(R.id.show_timer);

        timer = new Timer();

        //value = new ArrayList<>();

        pinView = (PinView) findViewById(R.id.pinView);
        pinView2 = (PinView) findViewById(R.id.pinView2);

        showpendingGame(level);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == img_option.getId()) {
            openOptionPopup();
        } else if (id == img_hint.getId()) {
            openhintpopup();
        }
        else if(id==rel_quit_game.getId())
        {
            quitDilog();
        }

    }

    public void openhintpopup() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.select_hint_view);

        TextView txt_hint = (TextView) dialog.findViewById(R.id.txt_hint);

//        if (wordLists.size() != 1) {
//            txt_hint.setText(wordLists.get(position).getHint());
//        }

        dialog.show();

    }

    public void openOptionPopup() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.select_game_option);

        Button btn_resume = (Button) dialog.findViewById(R.id.btn_resume);
        btn_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();

            }
        });
        Button btn_new_game = (Button) dialog.findViewById(R.id.btn_new_game);
        btn_new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();

                AlertDialog.Builder builder = new AlertDialog.Builder(context);


                builder.setMessage("Are you sure to start new game?");


                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog1, int which) {

                        Intent intent = new Intent(context, GameHomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                        dialog1.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog1, int which) {


                        dialog1.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });
        Button btn_quit = (Button) dialog.findViewById(R.id.btn_quit);
        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();

                quitDilog();
            }
        });


        dialog.show();

    }

    public void quitDilog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);


        builder.setMessage("Are you sure to quit?");


        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog1, int which) {

                finish();
                dialog1.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog1, int which) {


                dialog1.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }

    public void opennextPopup() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.level_completed_view);

        Button btn_next_level = (Button) dialog.findViewById(R.id.btn_next_level);
        TextView txt_desc = (TextView) dialog.findViewById(R.id.txt_desc);

        //txt_desc.setText(wordLists.get(position).getExplanation());


        btn_next_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dialog.dismiss();
                pinView.setText("");
                pinView2.setText("");
                //value.clear();
                position++;

                //int size = wordLists.size();
                //if (position == size) {
                    //submitwordapi();
                //} else {
                    //showpendingGame(wordLists.get(position).getLevel());
                    //txt_level.setText("LEVEL" + " " + wordLists.get(position).getLevel());

                    //set_status = 0;
                    //showword();
               // }


            }
        });


        dialog.show();
    }

    public void showpendingGame(String level) {
        dialog = new Dialog(context, R.style.tsta_game);
        dialog.setContentView(R.layout.game_level_view);

        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);


        TextView level1 = (TextView) dialog.findViewById(R.id.txt_level);
        level1.setText("LEVEL" + " " + level);


        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.show();


    }

    public void showword() {

       // String[] parts = wordLists.get(position).getWord().split(" ");

        //multicharsize = parts.length;

        if (multicharsize == 1) {
            //name = parts[0];
            name = name.replaceAll("-", "");
            name = name.replaceAll(" ", "");

            pinView.setItemCount(name.length());
            pinView2.setVisibility(View.GONE);
            //showchar(name);

        } else {
            pinView2.setVisibility(View.VISIBLE);
            //name = parts[0];
            //name2 = parts[1];

            name = name.replaceAll("-", "");
            name = name.replaceAll(" ", "");

            //showchar(name);

            name2 = name2.replaceAll("-", "");
            name2 = name2.replaceAll(" ", "");

            pinView.setItemCount(name.length());
            pinView2.setItemCount(name2.length());
        }


        //String show_text=wordLists.get(position).getWord();
        checkPreviousActivityStatus();


    }

    private void checkPreviousActivityStatus() {
        Completable.complete()
                .delay(2, TimeUnit.SECONDS)
                .doOnComplete(() -> {
                    //Do your stuff here
                    dialog.dismiss();


                })
                .subscribe();
    }

    public void showchar(String show_text) {
        show_text = show_text.replaceAll("-", "");
        show_text = show_text.replaceAll(" ", "");


        int length = show_text.length();


        char letter = 0;


        for (int i = 0; i < length; i++) {
            letter = show_text.charAt(i);
            //value.add(new AddChar(Character.toString(letter).toUpperCase(), false));

        }

        //Collections.shuffle(value);

        //selectAdapter = new SelectTextAdapter(context, value, this);
        //recy_select_text.setAdapter(selectAdapter);
    }

    public void starttimer() {
        timerTask = new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        time++;
                        //final_time = getTimerText();
                        show_timer.setText(final_time);

                    }
                });


            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000);

    }
    private String getTimerText() {
        int rounded = (int) Math.round(time);
        int seconds = ((rounded % 86400) % 3600) % 60;
        int minuts = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) % 3600);

        return formatTime(hours, minuts, seconds);
    }

    private String formatTime(int hours, int minut, int second) {
        return String.format("%02d", minut) + " : " + String.format("%02d", second);
    }


    @Override
    public void gameData(String character, int position, String status) {
        if (status.equals("add")) {

            // selectvalue.add(character);

            linkedvalue.put(position,character);

        } else if (status.equals("remove")) {
            // selectvalue.remove(character);
            linkedvalue.remove(position);

        }


        selectvalue=new ArrayList<>(linkedvalue.values());

        final_char = TextUtils.join("", selectvalue);

        if (multicharsize == 1) {
            pinView.setText(final_char);

            pinView.setLineColor(
                    ResourcesCompat.getColorStateList(getResources(), R.color.white, getTheme()));
            if (name.length() == final_char.length()) {
                if (name.toUpperCase().equals(final_char)) {
                    selectvalue.clear();
                    linkedvalue.clear();
                    final_char = "";
                    opennextPopup();

                }
                else
                {
                    pinView.setAnimationEnable(true);
                    pinView.setLineColor(
                            ResourcesCompat.getColorStateList(getResources(), R.color.red, getTheme()));
                }
            }


        } else {

            if (set_status == 0) {
                pinView.setText(final_char);
                pinView.setLineColor(
                        ResourcesCompat.getColorStateList(getResources(), R.color.white, getTheme()));
                if (name.length() == final_char.length()) {

                    if (name.toUpperCase().equals(final_char)) {


                        set_status = 1;
                        selectvalue.clear();
                        linkedvalue.clear();
                        final_char = "";

                        Toast.makeText(context, "GOOD", Toast.LENGTH_SHORT).show();

                        //value.clear();
                        showchar(name2);


                    }
                    else
                    {
                        pinView.setAnimationEnable(true);
                        pinView.setLineColor(
                                ResourcesCompat.getColorStateList(getResources(), R.color.red, getTheme()));
                    }

                }

            } else {
                pinView2.setText(final_char);
                pinView2.setLineColor(
                        ResourcesCompat.getColorStateList(getResources(), R.color.white, getTheme()));
                if (name2.length() == final_char.length()) {
                    if (name2.toUpperCase().equals(final_char)) {
                        selectvalue.clear();
                        linkedvalue.clear();
                        final_char = "";
                        opennextPopup();

                    }
                    else
                    {
                        pinView2.setAnimationEnable(true);
                        pinView2.setLineColor(
                                ResourcesCompat.getColorStateList(getResources(), R.color.red, getTheme()));
                    }
                }
            }


        }


    }
}