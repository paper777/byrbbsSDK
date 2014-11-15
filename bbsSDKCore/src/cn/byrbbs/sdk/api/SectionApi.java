package cn.byrbbs.sdk.api;

import cn.byrbbs.sdk.auth.Oauth2AccessToken;
import cn.byrbbs.sdk.net.RequestListener;


/**
 * SectionAPI: get section list
 * @author ALSO
 *
 */
public class SectionApi extends BaseApi {
	
	private String SEC_URL = BASE_URL + "/section";
	
	public SectionApi(Oauth2AccessToken accessToken) {
		super(accessToken);
	}
	
	/**
	 * get all root sections
	 * @param listener
	 */
	public void getRootSection(RequestListener listener){
		asyncRequest(SEC_URL, HTTP_GET, null, listener);
	}
	
	/**
	 * get section info(sub section if exited and board)
	 * @param namea valid section name
	 * @param listener
	 */
	public void getSection(String name, RequestListener listener){
		if(name == null) return;
		String url = SEC_URL + "/" + name;
		asyncRequest(url, HTTP_GET, null, listener);
	}
}
