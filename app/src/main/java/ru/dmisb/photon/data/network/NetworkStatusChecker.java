package ru.dmisb.photon.data.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import io.reactivex.Observable;
import ru.dmisb.photon.App;

class NetworkStatusChecker {

    private static boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) App.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    static Observable<Boolean> isInternetAvailable() {
        return Observable.just(NetworkStatusChecker.isNetworkAvailable());
    }
}
