package cn.byrbbs.sdk.api;

import android.text.TextUtils;
import cn.byrbbs.sdk.auth.Oauth2AccessToken;
import cn.byrbbs.sdk.net.RequestListener;

/**
 * Widget API
 * @author ALSO
 *
 */
public class WidgetApi extends BaseApi {

	private String WG_URL = BASE_URL + "/widget/";
	
	public WidgetApi(Oauth2AccessToken accessToken) {
		super(accessToken);
	}
	
	/**
	 * get TOP 10 information
	 * @param listener
	 */
	public void topten(RequestListener listener){
		String url = WG_URL + "topten";
		asyncRequest(url, HTTP_GET, null, listener);
	}
	
	/**
	 * recommend info
	 * @param listener
	 */
	public void recommend(RequestListener listener){
		String url = WG_URL + "recommend";
		asyncRequest(url, HTTP_GET, null, listener);
	}

	/**
	 * section top articles
	 * @param sectionName
	 * @param listener
	 */
	public void sectionTop(String sectionName, RequestListener listener){
		if(!TextUtils.isEmpty(sectionName)){
			String url = WG_URL + "section-" + sectionName;
			asyncRequest(url, HTTP_GET, null, listener);
		}
	}// func
}
