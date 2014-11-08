package cn.byrbbs.sdk.api;

import android.text.TextUtils;
import cn.byrbbs.sdk.auth.Oauth2AccessToken;
import cn.byrbbs.sdk.net.BBSParameters;
import cn.byrbbs.sdk.net.RequestListener;

/**
 * favorite API
 * @author ALSO
 *
 */
public class FavApi extends BaseApi {
	private String F_URL = BASE_URL + "/favorite/";

	public FavApi(Oauth2AccessToken accessToken) {
		super(accessToken);
	}

	/**
	 * show favorite
	 * @param level integer top level is 0
	 * @param listener
	 */
	public void show(int level, RequestListener listener){
		if(level <0) return;
		String url = F_URL + level;
		asyncRequest(url, HTTP_GET, null, listener);
	}
	
	/**
	 * add favorite board
	 * @param level 
	 * @param name board name OR custom directory name
	 * @param dir need to be 0 OR 1, 1=>custom directory
	 * @param listener
	 */
	public void add(int level, String name, int dir, RequestListener listener){
		if(level < 0 || name == null || TextUtils.isEmpty(name)) return;
		if(dir != 0 && dir != 1) return;
		
		BBSParameters param = new BBSParameters();
		param.put("name", name);
		param.put("dir", dir);
		
		String url = F_URL + "add/" + level;
		
		asyncRequest(url, HTTP_POST, param, listener);
	}
	
	/**
	 * remove favorite board or custom dir
	 * @param level 
	 * @param name board name OR custom directory name
	 * @param dir need to be 0 OR 1, 1=>custom directory
	 * @param listener
	 */
	public void delete(int level, String name, int dir, RequestListener listener){
		if(level < 0 || name == null || TextUtils.isEmpty(name)) return;
		if(dir != 0 && dir != 1) return;
		
		BBSParameters param = new BBSParameters();
		param.put("name", name);
		param.put("dir", dir);
		
		String url = F_URL + "delete/" + level;
		
		asyncRequest(url, HTTP_POST, param, listener);
	}
	
}
