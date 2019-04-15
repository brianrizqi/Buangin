package id.ac.unej.ilkom.ods.buangin.util;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static final int WRITE_EXTERNAL = 101;

    public static final int REQUEST_IMAGE_CAPTURE = 102;

    public static final String util_data_leaderboard = "dataLeaderboard";

//    public static final String util_desc_leaderboard="100000";
public static final String util_desc_leaderboard="99999";

    public static final long MB = 20 * 1024 * 1024;

    public Util() {

    }

    public static String kode_voucher(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy HH mm ss");
        return dateFormat.format(new Date()).replace(" ","");
    }

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

    public static String md5(String input) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String tanggalSekarang() {
        String tanggal = "";
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy-HH:mm");
        tanggal = formatter.format(new Date());
        return tanggal;
    }

    public static String waktuEpochSekarang() {
        String tanggal = "";
        SimpleDateFormat formatter = new SimpleDateFormat("mmSS");
        tanggal = formatter.format(new Date());
        return tanggal;
    }

    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void alertDialog(Context context, String title, String msg, String msgPositive, DialogInterface.OnClickListener positive, String msgNegative, DialogInterface.OnClickListener negative) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        if (!title.equals(null) || !title.equalsIgnoreCase("")) {
            dialog.setTitle(title);
        }
        if (!msgPositive.equals(null) || !msgPositive.equalsIgnoreCase("")) {
            dialog.setPositiveButton(msgPositive, positive);
        }

        if (!msgNegative.equals(null) || !msgNegative.equalsIgnoreCase("")) {
            dialog.setNegativeButton(msgNegative, negative);
        }

        dialog.show();
    }
}
