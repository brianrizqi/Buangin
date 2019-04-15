package id.ac.unej.ilkom.ods.buangin.admin.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Util {
    public static final String util_harga_kertas = "hargaKertas";
    public static final String util_harga_plastik="hargaPlastik";

    public static boolean get_internet(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        if (info != null && info.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo data = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (wifi != null && wifi.isConnectedOrConnecting() || data != null && data.isConnectedOrConnecting()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
