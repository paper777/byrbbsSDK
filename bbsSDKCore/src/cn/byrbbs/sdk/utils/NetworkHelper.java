package cn.byrbbs.sdk.utils;

import java.util.List;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

/*
 * class NetworkHelper
 * description: mainly to check android system's network(get info, check Internet access permission) 
 * 				including WIFI and mobile network
 */

public class NetworkHelper {

	public static boolean hasInternetPermission(Context context) {
		if (context != null) {
			return context
					.checkCallingOrSelfPermission("android.permission.INTERNET") == 0;
		}
		return true;
	}

	public static boolean isNetworkAvailable(Context context) {
		if (context != null) {
			NetworkInfo info = getActiveNetworkInfo(context);
			return (info != null) && (info.isConnected());
		}

		return false;
	}

	public static boolean isWifiValid(Context context) {
		if (context != null) {
			// when active network information type == 1 return true

			NetworkInfo info = getActiveNetworkInfo(context);
			// public int getType ()
			// Reports the type of network to which the info in this NetworkInfo
			// pertains.
			// @return: one of TYPE_MOBILE, TYPE_WIFI, TYPE_WIMAX,
			// TYPE_ETHERNET, TYPE_BLUETOOTH, or other types defined by
			// ConnectivityManager
			return (info != null) && (1 == info.getType())
					&& (info.isConnected());
		}

		return false;
	}

	public static boolean isMobileNetwork(Context context) {
		if (context != null) {
			NetworkInfo info = getActiveNetworkInfo(context);

			if (info == null)
				return false;

			return (info != null) && (info.getType() == 0)
					&& (info.isConnected());
		}
		return false;
	}

	public static NetworkInfo getActiveNetworkInfo(Context context) {
		// func: context.getSystemService(String name)
		// constant value: "connectivity" --> CONNECTIVITY_SERVICE retrieve a
		// connectivityManager for handling
		// management of network connections
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService("connectivity");
		return connectivity.getActiveNetworkInfo();
	}

	// @param: int networkType.
	// NetworkInfo type: TYPE_MOBILE, TYPE_WIFI, TYPE_WIMAX, TYPE_ETHERNET,
	// TYPE_BLUETOOTH,
	public static NetworkInfo getNetworkInfo(Context context, int networkType) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService("connectivity");
		return connectivityManager.getNetworkInfo(networkType);
	}

	// @return: NetworkType OR -1 if unsuccessful
	public static int getNetworkType(Context context) {
		if (context != null) {
			NetworkInfo info = getActiveNetworkInfo(context);
			// NetworkInfo type: TYPE_MOBILE, TYPE_WIFI, TYPE_WIMAX,
			// TYPE_ETHERNET, TYPE_BLUETOOTH,
			// or other types defined by ConnectivityManager
			return info == null ? -1 : info.getType();
		}
		return -1;
	}

	public static int getWifiState(Context context) {
		WifiManager wifi = (WifiManager) context.getSystemService("wifi");
		if (wifi == null) {
			return 4; // WIFI_STATE_UNKNOW
		}
		// func WifiManager.getWifiState()
		// return: One of WIFI_STATE_DISABLED, WIFI_STATE_DISABLING,
		// WIFI_STATE_ENABLED, WIFI_STATE_ENABLING, WIFI_STATE_UNKNOWN
		return wifi.getWifiState();
	}

	public static NetworkInfo.DetailedState getWifiConnectivityState(
			Context context) {
		NetworkInfo networkInfo = getNetworkInfo(context, 1);
		return networkInfo == null ? NetworkInfo.DetailedState.FAILED
				: networkInfo.getDetailedState();
	}

	public static boolean wifiConnection(Context context, String wifiSSID,
			String password) {
		boolean isConnection = false;
		WifiManager wifi = (WifiManager) context.getSystemService("wifi");

		// wifiInfo.getSSID returns the wifi SSID surrounded by double quotation
		// marks
		String strQuotationSSID = "\"" + wifiSSID + "\"";

		WifiInfo wifiInfo = wifi.getConnectionInfo();
		if ((wifiInfo != null)
				&& ((wifiSSID.equals(wifiInfo.getSSID())) || (strQuotationSSID
						.equals(wifiInfo.getSSID())))) {
			isConnection = true;
		} else {
			List scanResults = wifi.getScanResults();
			if ((scanResults != null) && (scanResults.size() != 0)) {
				for (int nAllIndex = scanResults.size() - 1; nAllIndex >= 0; nAllIndex--) {
					String strScanSSID = ((ScanResult) scanResults
							.get(nAllIndex)).SSID;
					if ((wifiSSID.equals(strScanSSID))
							|| (strQuotationSSID.equals(strScanSSID))) {
						WifiConfiguration config = new WifiConfiguration();
						config.SSID = strQuotationSSID;
						config.preSharedKey = ("\"" + password + "\"");
						config.status = 2;

						int nAddWifiId = wifi.addNetwork(config);
						isConnection = wifi.enableNetwork(nAddWifiId, false);
						break;
					} // if(equal...)
				} // for(int....)
			} // if(scan...)
		} // else

		return isConnection;
	}

	public static void clearCookies(Context context, String url) {
		CookieSyncManager.createInstance(context);
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.setAcceptCookie(true);
		cookieManager.removeSessionCookie();
		cookieManager.removeAllCookie();
		CookieSyncManager.getInstance().sync();
	}
}
