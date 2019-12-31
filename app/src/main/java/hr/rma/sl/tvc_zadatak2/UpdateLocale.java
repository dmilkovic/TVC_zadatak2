package hr.rma.sl.tvc_zadatak2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Locale;

class UpdateLocale {

    static String getLocale(Context context)
    {
        //get language in sharedPrefferences with key "Language", return "" if it doesn't exist
        String langPref = "Language";
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String language = prefs.getString(langPref, "");
        return language;
    }

    static boolean isLocaleSet(Context context)
    {
        //check if language is set
        return !getLocale(context).equals("");
    }

    static void loadLocale(Context context) {
        //set language
        String language = getLocale(context);
        changeLang(language, context);
    }

    static void changeLang(String lang, Context context) {
        if (lang.equalsIgnoreCase(""))
            return;
        Locale myLocale = new Locale(lang);
        saveLocale(lang, context);
        //update locale
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        context.getResources().updateConfiguration(config,context.getResources().getDisplayMetrics());
    }

    static void saveLocale(String lang, Context context) {
        //save language to sharedPreferences
        String langPref = "Language";
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.apply();
    }
}

