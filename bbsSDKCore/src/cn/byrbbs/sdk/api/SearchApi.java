package cn.byrbbs.sdk.api;

import android.text.TextUtils;
import cn.byrbbs.sdk.auth.Oauth2AccessToken;
import cn.byrbbs.sdk.net.BBSParameters;
import cn.byrbbs.sdk.net.RequestListener;

/**
 * search(board, article, thread) API
 * @author ALSO
 */

/*
 * TODO list:
 * search article
 * search thread by day OR m OR attachment and default parameters(count, page)
 * 
 */
public class SearchApi extends BaseApi {
	private String SR_URL = BASE_URL + "/search/";
	
	public SearchApi(Oauth2AccessToken accessToken) {
		super(accessToken);
	}
	
	/**
	 * search board
	 * @param board: board name
	 * @param listener
	 */
	public void board(String board, RequestListener listener){
		if(TextUtils.isEmpty(board)) return;
		
		BBSParameters param = new BBSParameters();
		param.put("board", board);
		String url = SR_URL + "board";
		asyncRequest(url, HTTP_GET, param, listener);
	}
	
	/**
	 * search thread with title by default parameters
	 * @param board
	 * @param title1
	 * @param title2
	 * @param titlen threads do not contained 
	 * @param listener
	 */
	public void threadByTitle(String board, 
			String title1, String title2, String titlen, 
			RequestListener listener){
		if(board == null || TextUtils.isEmpty(board)) return;
		BBSParameters param = new BBSParameters();
		param.put("board", board);
		if(TextUtils.isEmpty(title1)){
			param.put("title1", title1);
		}
		if(TextUtils.isEmpty(title2)){
			param.put("title2", title2);
		}
		
		if(TextUtils.isEmpty(titlen)){
			param.put("titlen", titlen);
		}
		
		String url = SR_URL + "/threads";
		asyncRequest(url, HTTP_GET, param, listener);
	}
	
	/**
	 * search by author name
	 * @param board
	 * @param author
	 * @param listener
	 */
	public void threadByAuthor(String board, String author, RequestListener listener){
		if(board == null || TextUtils.isEmpty(board)) return;
		if(author == null || TextUtils.isEmpty(author)) return;
		
		BBSParameters param = new BBSParameters();
		param.put("board", board);
		param.put("author", author);
		
		String url = SR_URL + "/threads";
		asyncRequest(url, HTTP_GET, param, listener);
	}
}
