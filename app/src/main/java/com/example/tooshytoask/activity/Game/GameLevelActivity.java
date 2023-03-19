package com.example.tooshytoask.activity.Game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tooshytoask.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;

public class GameLevelActivity extends AppCompatActivity {
    TextView txt_level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_level);
        getWindow().setStatusBarColor(getResources().getColor(R.color.purple));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        txt_level=(TextView)findViewById(R.id.txt_level);
        checkPreviousActivityStatus();
    }

    private void checkPreviousActivityStatus() {
        Completable.complete()
                .delay(1, TimeUnit.SECONDS)
                .doOnComplete(()->{
                    //Do your stuff here

                    Intent intent  = new Intent(this, GameHomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();


                })
                .subscribe();
    }
}