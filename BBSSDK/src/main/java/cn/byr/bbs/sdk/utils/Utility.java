package cn.byr.bbs.sdk.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by ALSO on 2015/3/31.
 */
public class Utility {
    public Utility() {
    }

    /*
     * public static get app signature
     * @param Context
     *
     * @return String: app signature
     */
    public static String getSign(Context con) {
        StringBuilder stringBuilder = new StringBuilder();
        PackageManager packageManager = con.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(con.getPackageName(), PackageManager.GET_SIGNATURES);
            for (int j = 0; j < packageInfo.signatures.length; j++) {
                byte[] str = packageInfo.signatures[j].toByteArray();
                if (str != null) {
                    return MD5.hexdigest(str);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
        return null;
    }

    public static String getSign(Context context, String pkgName) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(pkgName, 64);
        } catch (PackageManager.NameNotFoundException localNameNotFoundException) {
            return null;
        }
        for (int j = 0; j < packageInfo.signatures.length; j++) {
            byte[] str = packageInfo.signatures[j].toByteArray();
            if (str != null) {
                return MD5.hexdigest(str);
            }
        }
        return null;
    }

    public static Bundle parseUrl(String url) {
        try {
            URL u = new URL(url);
            Bundle b = decodeUrl(u.getQuery());
            b.putAll(decodeUrl(u.getRef()));
            return b;
        } catch (MalformedURLException e) {
        }
        return new Bundle();
    }

    public static Bundle decodeUrl(String s) {
        Bundle params = new Bundle();
        if (s != null) {
            String[] array = s.split("&");
            for (String parameter : array) {
                String[] v = parameter.split("=");
                try {
                    params.putString(URLDecoder.decode(v[0], "UTF-8"),
                            URLDecoder.decode(v[1], "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return params;
    }
}
