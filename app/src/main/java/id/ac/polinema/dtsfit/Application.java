package id.ac.polinema.dtsfit;

import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class Application extends android.app.Application {

    private static SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public static SharedPreferences providePreferences() {
        return preferences;
    }

}
