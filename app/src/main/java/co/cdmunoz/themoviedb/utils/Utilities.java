package co.cdmunoz.themoviedb.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Utilities {
    public static Boolean isConnectionAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) return activeNetwork.isConnectedOrConnecting();
        else return false;
    }

    public static void putLongSharedPreferences(Context context, String longPrefName, Long longValue) {
        SharedPreferences preferences = context.getSharedPreferences("MoviesDbPreferences", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(longPrefName, longValue);
        editor.apply();
    }

    public static Long getLongSharedPreferences(Context context, String longPrefName) {
        SharedPreferences preferences = context.getSharedPreferences("MoviesDbPreferences", Context.MODE_PRIVATE);
        //first time is current time minus 5 minutes
        return preferences.getLong(longPrefName, Calendar.getInstance().getTimeInMillis() - 5000 * 60);
    }

    public static Long convertMillisecondsToMinutes(Long milliseconds) {
        return TimeUnit.MILLISECONDS.toMinutes(milliseconds);
    }

    //return true if it's been more than 5 minutes from the last
    public static Boolean shouldGetDataFromApi(Context context) {
        Long timeLastQueryFromApi = getLongSharedPreferences(context, "pref_time_net_query");
        Long minutesFromLastApiQuery = convertMillisecondsToMinutes(timeLastQueryFromApi);
        Long currentTime = convertMillisecondsToMinutes(Calendar.getInstance().getTimeInMillis());
        return currentTime - minutesFromLastApiQuery >= 5;
    }
}

