package com.neuronimbus.metropolis;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Utils.LocaleHelper;

import java.util.Locale;

public class SetLocalLanguage extends Application {

    Context context;
    SPManager spManager;

    @Override
    public void onCreate() {
        super.onCreate();
//        spManager=new SPManager(getApplicationContext());
//        String lang = spManager.getLanguage();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            updateResources(context, lang);
//        }else {
//            updateResourcesLegacy(context, lang);
//        }

    }

    public void setLocale() {

        String lang = spManager.getLanguage();
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        spManager.setLanguage(lang);

    }

    @SuppressWarnings("deprecation")
    private static Context updateResourcesLegacy(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = new Configuration(resources.getConfiguration());
        configuration.locale = locale;

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        return context;
    }

    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);

        return context.createConfigurationContext(configuration);
    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(LocaleHelper.onAttach(base, "en")); // Change "en" to the default language
//    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        setLanguageFromNewConfig(newConfig);
        super.onConfigurationChanged(newConfig);
    }

    /* also handle chnage  language if  device language chnaged **/
    private void setLanguageFromNewConfig(Configuration newConfig){
        LocaleHelper.onAttach(this);
    }
}
