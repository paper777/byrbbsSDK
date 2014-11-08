package cn.byrbbs.sdk.api;

import android.text.TextUtils;
import cn.byrbbs.sdk.auth.Oauth2AccessToken;
import cn.byrbbs.sdk.net.BBSParameters;
import cn.byrbbs.sdk.net.RequestListener;

/**
 * VOTE API
 * @author ALSO
 *
 */
public class VoteApi extends BaseApi {

	private String VOTE_URL = BASE_URL + "/vote/";
	public VoteApi(Oauth2AccessToken accessToken) {
		super(accessToken);
	}
	
	/**
	 * get vote list
	 * @param category: must be one of below 5
	 * 		<li> "me"  => current user's vote list</li>
	 * 		<li> "join" => list of current user's joined </li>
	 * 		<li> "new" => votes(still open) in reverse<b>(chronological)</b> order list</li>
	 * 		<li> "hot" => votes(still open) in reverse<b>(number of participants)</b> order list</li>
	 * 		<li> "all" => votes(all) in reverse chronological order list</li>
	 * @param listener
	 */
	public void voteList(String category, RequestListener listener, String user){
		if(!(category.equals("me") 
				|| category.equals("join")
				|| category.equals("new")
				|| category.equals("hot")
				|| category.equals("all"))){
			return;
		}
		
		String url = VOTE_URL + "category/" + category;
		asyncRequest(url, HTTP_GET, null, listener);
	}
	
	/**
	 * query sb's vote history
	 * @param userid
	 * @param listener
	 */
	public void queryVoteList(String userid, RequestListener listener){
		if(TextUtils.isEmpty(userid)){ return; }
		String url = VOTE_URL + "category/list";
		
		BBSParameters param = new BBSParameters();
		param.put("u", userid);
		asyncRequest(url, HTTP_GET, param, listener);
		
	}
	
	/**
	 * Vote
	 * @param vid
	 * @param viid
	 * @param listener
	 */
	public void vote(int vid, int[] viid, RequestListener listener){
		if(viid.length == 0){ return; }
		BBSParameters param = new BBSParameters();
		// TODO
		param.put("vote", viid);
		String url = VOTE_URL + vid;
		asyncRequest(url, HTTP_GET, param, listener);
		
	}

}
