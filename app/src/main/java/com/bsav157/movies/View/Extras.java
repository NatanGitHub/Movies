package com.bsav157.movies.View;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Extras {

    public static boolean isOnline(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }

        return false;

    }

}
