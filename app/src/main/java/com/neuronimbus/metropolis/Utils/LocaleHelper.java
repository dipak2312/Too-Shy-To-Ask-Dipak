package com.neuronimbus.metropolis.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import com.neuronimbus.metropolis.Helper.SPManager;

import java.util.Locale;

public class LocaleHelper {

    private static final String SELECTED_LANGUAGE = "selected_language";

    public static Context onAttach(Context context) {
        SPManager spManager=new SPManager(context);
        String lang=spManager.getLanguage();
        if (lang == null){
            lang = "en";
        }
        return setLocale(context, lang);
    }

    public static Context onAttach(Context context, String defaultLanguage) {
        SPManager spManager=new SPManager(context);
        String lang=spManager.getLanguage();
        return setLocale(context, lang);
    }

    public static String getLanguage(Context context) {
        SPManager spManager=new SPManager(context);
        String lang=spManager.getLanguage();
        return lang;
    }

    public static Context setLocale(Context context, String language) {

        SPManager spManager=new SPManager(context);
        spManager.setLanguage(language);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, language);
        }

        return updateResourcesLegacy(context, language);
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
}
