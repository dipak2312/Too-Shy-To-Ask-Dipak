package com.neuronimbus.metropolis.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.neuronimbus.metropolis.R;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {
    WebView web_view;
    ProgressBar progressBar;
    String url,title;
    TextView txt_title;
    RelativeLayout rel_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        rel_back= findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        txt_title= findViewById(R.id.txt_title);

        Intent intent = getIntent();
        url = intent.getStringExtra("link1");
        title=intent.getStringExtra("title");

        web_view= findViewById(R.id.web_view);
        txt_title.setText(title);



        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.getSettings().setDomStorageEnabled(true);
        web_view.getSettings().setPluginState(WebSettings.PluginState.ON);
        web_view.getSettings().setMediaPlaybackRequiresUserGesture(false);
        web_view.setWebViewClient(new WebViewClient());
        web_view.getSettings().setLoadWithOverviewMode(true);
        web_view.setVerticalScrollBarEnabled(false);
        web_view.setHorizontalScrollBarEnabled(false);
        web_view.loadUrl(url);

        progressBar = findViewById(R.id.progressBar);

        web_view.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                progressBar.setProgress(progress);
                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);

                } else {
                    progressBar.setVisibility(View.VISIBLE);

                }
            }


            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                //LogAV.d("onPermissionRequest");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    request.grant(request.getResources());
                }
            }



        });

    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        if(id==rel_back.getId())
        {
            finish();
        }
    }
    @Override
    public void onBackPressed() {

        if(web_view.canGoBack())
        {
            web_view.goBack();
        }
        else
        {
            super.onBackPressed();
        }
    }
}