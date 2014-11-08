package cn.byrbbs.sdk.api;

import android.text.TextUtils;
import cn.byrbbs.sdk.auth.Oauth2AccessToken;
import cn.byrbbs.sdk.net.BBSParameters;
import cn.byrbbs.sdk.net.RequestListener;

/**
 * articleApi: handle some article operations
 * @author ALSO
 *
 */
public class ArticleApi extends BaseApi {

	private String AR_URL = BASE_URL + "/article/";
	
	public ArticleApi(Oauth2AccessToken accessToken) {
		super(accessToken);
	}
	
	/**
	 * get article by article id
	 * @param board: valid board name
	 * @param id: valid article id
	 * @param listener
	 */
	public void showArticle(String board, int id, RequestListener listener){
		if(TextUtils.isEmpty(board) 
				|| id < 0
				|| listener == null){ 
			return; 
		}
		
		String url = AR_URL + board + "/" + id;
		asyncRequest(url, HTTP_GET, null, listener);
	}
	
	/**
	 * get a thread 
	 * @param board
	 * @param id
	 * @param listener
	 * 
	 */
	public void showThread(String board, int id, RequestListener listener
			/** params below is not requested*/
			/*, String au, int count, int page */ ){
		if(TextUtils.isEmpty(board) 
				|| id <0
				|| listener == null){ 
			return; 
		}
		
		String url = AR_URL + "/thread/" + board + "/" + id;
		asyncRequest(url, HTTP_GET, null, listener);
	}
	
	
	/**
	 * post an article 
	 * @param board
	 * @param listener
	 * @param title
	 * @param content
	 */
	public void post(String board, RequestListener listener,
			String title, String content ){ /* content empty is OK.*/
		if(TextUtils.isEmpty(board) || listener == null){ 
			return; 
		}
		
		/* buid params */
		BBSParameters param = new BBSParameters();
		if(!TextUtils.isEmpty(title)){
			param.put("title", title);
		}
		param.put("content", content);

		String url = AR_URL + board + "/post";
		asyncRequest(url, HTTP_POST, param, listener);
	}
	
	/**
	 * reply an article
	 * @param board
	 * @param listener
	 * @param title
	 * @param content
	 * @param reid target article id to reply
	 */
	public void reply(String board, RequestListener listener,
			String title, String content, int reid){ /* content empty is OK.*/
		if(TextUtils.isEmpty(board) || listener == null){ 
			return; 
		}
		
		/* buid params */
		BBSParameters param = new BBSParameters();
		if(!TextUtils.isEmpty(title)){
			param.put("title", title);
		}
		param.put("content", content);
		param.put("reid", reid);

		String url = AR_URL + board + "/post";
		asyncRequest(url, HTTP_POST, param, listener);
	}
	
	/**
	 *  forward an article to someone
	 * @param board
	 * @param id: article id
	 * @param targetUserid
	 * @param thread: 
	 * 			1=> collection the article and then forward
	 * @param noref
	 * 			0=>forward with it's refer
	 * @param noatt
	 * 			0=>forward the article with it's attachments
	 * @param noansi
	 * 			0=>forward with ANSI characters
	 * @param big5
	 * 			1=>encode with BIG5
	 */
	public void forward(String board, int id, String targetUserid,
			boolean thread, boolean noref, boolean noatt,
			boolean noansi, boolean big5, RequestListener listener){
		
		if(TextUtils.isEmpty(board) || listener == null){ 
			return; 
		}
		
		BBSParameters param = new BBSParameters();
		
		if(!TextUtils.isEmpty(targetUserid)){
			param.put("target", targetUserid);
		}
		
		String url = AR_URL + board + "/forward/" + id;
		asyncRequest(url, HTTP_POST, param, listener);
	}
	
	/**
	 * forward an article
	 * @param board
	 * @param id: article id
	 * @param targetUserid
	 * @param listener
	 */
	public void forward(String board, int id, String targetUserid, RequestListener listener){
		if(TextUtils.isEmpty(board) || listener == null){ 
			return; 
		}
		forward(board, id, targetUserid, false, false, false, false, false, listener);
	}
	
	
	/**
	 * reproduce (cross @apiwiki) an article
	 * @param board
	 * @param id article id
	 * @param targetBoard
	 * @param listener
	 */
	public void reproduce(String board, int id, String targetBoard, RequestListener listener){
		if(TextUtils.isEmpty(board) 
				|| TextUtils.isEmpty(targetBoard)
				|| listener == null){ 
			return; 
		}
		
		BBSParameters param = new BBSParameters();
		param.put("target", targetBoard);
		String url = AR_URL + board + "/cross/" + id;
		
		asyncRequest(url, HTTP_POST, param, listener);
	}
	
	/**
	 * update an article
	 * @param board
	 * @param id
	 * @param title
	 * @param content
	 * @param listener
	 */
	public void update(String board, int id, String title, 
			String content, RequestListener listener){
		if(TextUtils.isEmpty(board) || listener == null){ 
			return; 
		}
		
		BBSParameters param = new BBSParameters();
		param.put("title", title);
		param.put("content", content);
		String url = AR_URL + board + "/update/" + id;
		
		asyncRequest(url, HTTP_POST, param, listener);
	}

	/**
	 * delete an article
	 * @param board
	 * @param id
	 * @param listener
	 */
	public void delete(String board, int id, RequestListener listener){
		if(TextUtils.isEmpty(board) || listener == null){ 
			return; 
		}
		
		String url = AR_URL + board + "/delete/" + id;
		
		asyncRequest(url, HTTP_POST, null, listener);
	}
	
	
}
