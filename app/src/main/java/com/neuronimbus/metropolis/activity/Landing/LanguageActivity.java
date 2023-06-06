package com.neuronimbus.metropolis.activity.Landing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.adapters.LanguageAdapter;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.Language.LanguageResponse;
import com.neuronimbus.metropolis.Models.Language.data;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.OnClickListner;

import java.util.ArrayList;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LanguageActivity extends AppCompatActivity implements View.OnClickListener, OnClickListner {
    Context context;
    SPManager spManager;
    Button btn_next;
    RadioButton eng_lang, hindi_lang, marathi_lang, gujarati_lang, Tamil_lang;
    ArrayList<data>data;
    RecyclerView recy_language;
    LanguageAdapter adapter;
    OnClickListner onClickListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = LanguageActivity.this;
        spManager = new SPManager(context);

        eng_lang = findViewById(R.id.eng_lang);
        hindi_lang = findViewById(R.id.hindi_lang);
        marathi_lang = findViewById(R.id.marathi_lang);
        gujarati_lang = findViewById(R.id.gujarati_lang);
        Tamil_lang = findViewById(R.id.Tamil_lang);
        recy_language = findViewById(R.id.recy_language);
        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

        selectLanguage();
        //languageget();

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        String selectValue=spManager.getLanguage();

        if (id == btn_next.getId()) {

            if (selectValue.equals("")){
                Toast.makeText(context, "Select Your Language ", Toast.LENGTH_SHORT).show();
            }

            else {
                Intent intent = new Intent(context, SignInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        }

    }

    public void languageget(){

        WebServiceModel.getRestApi().languageget()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<LanguageResponse>() {
                    @Override
                    public void onNext(LanguageResponse languageResponse) {
                        String msg = languageResponse.getMsg();

                        if (msg.equals("success")) {

                            data = languageResponse.getData();
                            if (data != null) {
                                adapter = new LanguageAdapter(data, context, spManager, onClickListner);
                                recy_language.setAdapter(adapter);
                                adapter.notifyDataSetChanged();

                            }
                        } else {
                            //Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

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

    private void setLocale(String lang) {

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        spManager.setLanguage(lang);

    }

    public void radioButtonClickEvent(View view){
        boolean isChecked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.eng_lang:
                if(isChecked){

                    setLocale("en");
                    btn_next.setText(R.string.select);
                    eng_lang.setTextColor(ContextCompat.getColor(context, R.color.purple));
                    hindi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    marathi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    gujarati_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    Tamil_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    selectLanguage();
                }
                break;
            case R.id.hindi_lang:
                if(isChecked){

                    setLocale("hi");
                    btn_next.setText(R.string.चुनें);
                    hindi_lang.setTextColor(ContextCompat.getColor(context, R.color.purple));
                    eng_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    marathi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    gujarati_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    Tamil_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    selectLanguage();
                }
                break;
            case R.id.marathi_lang:
                if(isChecked){

                    setLocale("mr");
                    btn_next.setText(R.string.निवडा);
                    marathi_lang.setTextColor(ContextCompat.getColor(context, R.color.purple));
                    hindi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    eng_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    gujarati_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    Tamil_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    selectLanguage();
                }
                break;
            case R.id.gujarati_lang:
                if(isChecked){

                    setLocale("gr");
                    btn_next.setText(R.string.select);
                    gujarati_lang.setTextColor(ContextCompat.getColor(context, R.color.purple));
                    Tamil_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    marathi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    hindi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    eng_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    selectLanguage();
                }
                break;
            case R.id.Tamil_lang:
                if(isChecked){

                    setLocale("tm");
                    btn_next.setText(R.string.select);
                    Tamil_lang.setTextColor(ContextCompat.getColor(context, R.color.purple));
                    gujarati_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    marathi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    hindi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    eng_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    selectLanguage();
                }
                break;
        }
    }

    public void selectLanguage(){

        String selectValue=spManager.getLanguage();

        if(selectValue.equals("en"))
        {
            eng_lang.setChecked(true);
            eng_lang.setTextColor(ContextCompat.getColor(context, R.color.purple));
            Tamil_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            gujarati_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            marathi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            hindi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
        }
        else if(selectValue.equals("hi"))
        {
            hindi_lang.setChecked(true);
            hindi_lang.setTextColor(ContextCompat.getColor(context, R.color.purple));
            Tamil_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            gujarati_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            marathi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            eng_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
        }
        else if(selectValue.equals("mr"))
        {
            marathi_lang.setChecked(true);
            marathi_lang.setTextColor(ContextCompat.getColor(context, R.color.purple));
            Tamil_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            gujarati_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            hindi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            eng_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
        }
        else if(selectValue.equals("gr"))
        {
            gujarati_lang.setChecked(true);
            gujarati_lang.setTextColor(ContextCompat.getColor(context, R.color.purple));
            Tamil_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            marathi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            hindi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            eng_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
        }
        else if(selectValue.equals("tm"))
        {
            Tamil_lang.setChecked(true);
            Tamil_lang.setTextColor(ContextCompat.getColor(context, R.color.purple));
            gujarati_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            marathi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            hindi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            eng_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
        }
    }

    @Override
    public void onClickData(int position, String id) {

    }
}