package cn.byrbbs.sdk.api;

import android.text.TextUtils;
import cn.byrbbs.sdk.auth.Oauth2AccessToken;
import cn.byrbbs.sdk.net.RequestListener;
import cn.byrbbs.sdk.utils.LogUtil;

/**
 * -API: query user information
 * @author ALSO
 *
 */
public class UserApi extends BaseApi {
	
	private String USER_URL = BASE_URL + "/user";

	public UserApi(Oauth2AccessToken accessToken) {
		super(accessToken);
	}
	
	/**
	 * get user information
	 * @param return_format: 1=> return XML format ELSE return JSON
	 * @param listener
	 */
	public void show(RequestListener listener){
		String url = USER_URL + "/getinfo";
		asyncRequest(url, HTTP_GET, null, listener);
	}
	
	/**
	 * query somebody's info.
	 * @param id:  bbs id 
	 * @param listener
	 */
	public void query(String id, RequestListener listener){
		if(TextUtils.isEmpty(id)){
			LogUtil.e(TAG, "id null error");
			return;
		}
		String url = USER_URL + "/query/" + id;
		asyncRequest(url, HTTP_GET, null, listener);
	}

}
