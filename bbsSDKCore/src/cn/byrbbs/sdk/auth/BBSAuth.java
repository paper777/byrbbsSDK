package cn.byrbbs.sdk.auth;

import android.content.Context;
import android.os.Bundle;

import cn.byrbbs.sdk.net.BBSParameters;
import cn.byrbbs.sdk.utils.LogUtil;
import cn.byrbbs.sdk.utils.NetworkHelper;
import cn.byrbbs.sdk.utils.ResourceManager;
import cn.byrbbs.sdk.utils.UIUtils;
import cn.byrbbs.sdk.utils.Utility;


public class BBSAuth {
	public static final String TAG = "bbsAuth_login";
	private static final String BASE_URL = "http://eid.byr.cn/paper/nforum/oauth2/authorize?";
	private static final String response_type = "token";
	
	private Context mContext;
	private AuthInfo authInfo;
	
	/**
	 * Constructor
	 * @param client_id: developer's APP_KEY
	 * @param redirect_url: url which developer signed 
	 * @param scope: null for default/ {mail, attachment, article, favor, black}
	 */
	public BBSAuth(Context context, String client_id, String redirect_url, String scope){
		this.mContext = context;
		this.setAuthInfo(new AuthInfo(context, client_id, redirect_url, response_type, scope));
	}
	
	public BBSAuth(Context context, AuthInfo info){
		this.mContext = context;
		this.setAuthInfo(info);
	}

	/**
	 * Oauth2 method to get bbs access token
	 * @param listener: call back class where call back informations include 
	 * 					access token and expire time stored.
	 */
	public void authorize(BBSAuthListener listener){
		startAuthDialog(listener);
	}
	
	/*
	 * start the authorization dialog 
	 * @param listener: call back class
	 */
	private void startAuthDialog(BBSAuthListener listener){
		if(listener == null) return;
		
		// put reuqest parameters for url encode later
		BBSParameters requestParams = new BBSParameters();
		requestParams.put("client_id", this.authInfo.client_id);
		requestParams.put("redirect_url", this.authInfo.redirect_url);
		requestParams.put("response_type", this.authInfo.response_type);
		requestParams.put("scope", this.authInfo.scope);
		// put package name and signature !!REQUESTED
		requestParams.put("packagename", this.authInfo.pkgName);
		requestParams.put("signature", this.authInfo.keyHash);
		String url = BASE_URL + requestParams.encodeUrl();
		
		if (!NetworkHelper.hasInternetPermission(this.mContext)) {
			UIUtils.showAlert(this.mContext, "Error", "Application requires permission to access the Internet");
		}
		else if (NetworkHelper.isNetworkAvailable(this.mContext)) {
			new BBSDialog(this.mContext, url, listener, this).show();
		} else { // network not available, show toast
			String networkNotAvailable = ResourceManager.getString(this.mContext, 2);
			LogUtil.i("bbs_login", "String: " + networkNotAvailable);
			UIUtils.showToast(this.mContext, networkNotAvailable, 0);
		}
	}

	// getters and setters
	public AuthInfo getAuthInfo() {
		return authInfo;
	}
	public void setAuthInfo(AuthInfo authInfo) {
		this.authInfo = authInfo;
	}

	/* 
	 * AuthInfo class
	 * mainly stores authorization request parameters
	 */
	public static class AuthInfo{
		private String client_id = "";
		private String redirect_url = "";
		private String scope = "";
		private String response_type = "";
		
		// package name and app signature
		private String pkgName = "";
		private String keyHash = "";
		
		private Bundle mBundle = null;
		
		public AuthInfo(Context context, String client_id, String redirect_url, String response_type, String scope){
			
			this.client_id = client_id;
			this.redirect_url = redirect_url;
			this.scope = scope;
			this.response_type = response_type;
			
			// get package name from context
			this.pkgName = context.getPackageName();
			
			this.keyHash = Utility.getSign(context, this.pkgName);
			
			initAuthBundle();
		}
		
		// getters
		public String getClient_id() {
			return this.client_id;
		}
		
		public String getRedirect_url() {
			return this.redirect_url;
		}
		
		public String getScope() {
			return this.scope;
		}
		
		public Bundle getmBundle() {
			return this.mBundle;
		}
		
		public String getPkgName() {
			return this.pkgName;
		}
		
		public String getKeyHash(){
			return this.keyHash;
		}
		
		// init Bundle
		private void initAuthBundle(){
			this.mBundle = new Bundle();
			mBundle.putString("client_id", this.client_id);
			mBundle.putString("redirect_url", this.redirect_url);
			mBundle.putString("pkgName", this.pkgName);
			mBundle.putString("scope", this.scope);
			mBundle.putString("keyHash", this.keyHash);
			mBundle.putString("response_type", this.response_type);
		}
	} // class AuthInfo
	
	
} // end