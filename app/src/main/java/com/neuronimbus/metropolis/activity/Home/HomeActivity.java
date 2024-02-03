package com.neuronimbus.metropolis.activity.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.neuronimbus.metropolis.Fragment.AskExpertFragment;
import com.neuronimbus.metropolis.Fragment.HomeFragment;
import com.neuronimbus.metropolis.Fragment.InsightsFragment;
import com.neuronimbus.metropolis.Fragment.Quiz.QuizFragment;
import com.neuronimbus.metropolis.Fragment.SettingsFragment;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.GuestLoginPopup;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.neuronimbus.metropolis.Utils.LocaleHelper;

import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottom_view;
    SPManager spManager;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setupNavigationView();
        context = HomeActivity.this;
        spManager = new SPManager(context);


    }

    public void setupNavigationView() {
        bottom_view = (BottomNavigationView) findViewById(R.id.customBottomBar);
        if (bottom_view != null) {

            bottom_view.setItemIconTintList(null);

            // Select first menu item by default and show Fragment accordingly.
            Menu menu = bottom_view.getMenu();
            selectFragment(menu.getItem(0));
            bottom_view.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);

            // Set action to perform when any menu-item is selected.
            bottom_view.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                selectFragment(item);
                            return true;
                        }
                    });
        }
    }

    protected void selectFragment(MenuItem item) {

        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.action_home:
                // Action to perform when Home Menu item is selected.
                pushFragment(new HomeFragment());
                break;
            case R.id.action_ask_expert:
                // Action to perform when Bag Menu item is selected.
                if (spManager.getTstaguestLoginStatus().equals("false")) {
                    pushFragment(new AskExpertFragment());
                }
                else {
                    item.setChecked(false);
                    GuestLoginPopup.LogOut(context, spManager);
                    item.setIcon(R.drawable.ask_expert);
                    bottom_view.setActivated(false);

                }
                break;
            case R.id.action_insights:
                // Action to perform when Account Menu item is selected.
                pushFragment(new InsightsFragment());
                break;

            case R.id.action_quiz:
                pushFragment(new QuizFragment());
                break;

            case R.id.action_setting:

                if (spManager.getTstaguestLoginStatus().equals("false")) {
                    pushFragment(new SettingsFragment());
                }
                else {
                    item.setChecked(false);
                    GuestLoginPopup.LogOut(context, spManager);
                    item.setIcon(R.drawable.setting);
                    bottom_view.setActivated(false);
                }
                break;

        }
    }
    protected void pushFragment(Fragment fragment) {
        if (fragment == null)
            return;

        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();

        if (ft != null) {
            ft.replace(R.id.rootLayout,fragment);
            ft.commit();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        //setLocale(spManager.getLanguage());
    }

    private void setLocale(String lang) {

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;
        this.getResources().updateConfiguration(config, this.getResources().getDisplayMetrics());
        spManager.setLanguage(lang);

    }

    @Override
    public void onBackPressed() {
        bottom_view = findViewById(R.id.customBottomBar);

        if (bottom_view!=null && bottom_view.getSelectedItemId() == R.id.action_home)
        {
            super.onBackPressed();

        }else {
            bottom_view.setSelectedItemId(R.id.action_home);

        } }
}