package cn.byrbbs.sdk.utils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class Utility {

	public Utility(){};

	/*
	 * public static get app signature
	 * @param Context
	 * @param String pkgName: package name
	 * 
	 * @return String: app signature
	 */
	public static String getSign(Context con){
		PackageInfo pkgInfo;
		try{
			pkgInfo = con.getPackageManager().getPackageInfo(con.getPackageName(), PackageManager.GET_SIGNATURES);
		}catch(PackageManager.NameNotFoundException e){

			return null;
		}

		// TODO
		for(int i = 0; i < pkgInfo.signatures.length; i++){
			// convert sig to byte
			byte[] str = pkgInfo.signatures[i].toByteArray();
			if (str != null) {

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
		} catch (MalformedURLException e) {}
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
	
	public static String getSign(Context context, String pkgName) {
		PackageInfo packageInfo;
		try {
			packageInfo = context.getPackageManager().getPackageInfo(pkgName, 64);
		}
		catch (PackageManager.NameNotFoundException localNameNotFoundException) {
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
	
}
