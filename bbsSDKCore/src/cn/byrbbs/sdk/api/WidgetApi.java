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
	
	public static int BBS_AFFAIR 		= 0;
	public static int SCHOOL     		= 1;
	public static int SCIENCE_TEC 		= 2;
	public static int INFO_SOCIETY 		= 3;
	public static int HUMANITIES_ART 	= 4;
	public static int LIFE				= 5;
	public static int Entertainment 	= 6;
	public static int FITNESS 			= 7;
	public static int GAME 				= 8;
	public static int HOMETOWN 			= 9;
	
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
	 * @param sectionNumber	0~9
	 * 
	 * @param listener
	 */
	public void sectionTop(int sectionNum, RequestListener listener){
		if(sectionNum >= 0 && sectionNum <= 9){
			String url = WG_URL + "section-" + sectionNum;
			asyncRequest(url, HTTP_GET, null, listener);
		}
	}// func
}
