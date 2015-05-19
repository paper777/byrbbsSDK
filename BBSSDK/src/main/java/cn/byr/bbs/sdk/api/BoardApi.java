package cn.byr.bbs.sdk.api;

import cn.byr.bbs.sdk.auth.Oauth2AccessToken;
import cn.byr.bbs.sdk.net.BBSParameters;
import cn.byr.bbs.sdk.net.RequestListener;
import cn.byr.bbs.sdk.utils.URLHelper;

/**
 * BoardApi: get board information
 *
 * @author ALSO
 */
public class BoardApi extends BaseApi {

    private String BD_URL = URLHelper.BOARD;

    public BoardApi(Oauth2AccessToken accessToken) {
        super(accessToken);
    }

    /**
     * get board information
     *
     * @param name     valid board name
     * @param count  int 1~50 article number of each page
     * @param page int page number
     * @param listener
     */
    public void getBoardInfo(String name, int count, int page, RequestListener listener) {
        if (name == null || listener == null) {
            return;
        }

        BBSParameters param = new BBSParameters();
        param.put("count", count);
        param.put("page", page);
        String url = BD_URL + '/' + name;
        asyncRequest(url, HTTP_GET, param, listener);
    }

    /**
     * get board information using default params count 30, page 1
     * @param name     valid board name
     * @param listener
     */
    public void getBoardInfo(String name,  RequestListener listener) {
        getBoardInfo(name, 30, 1, listener);
    }

    /**
     * get board information using default params count 30
     * @param name     valid board name
     * @param page  int page number
     * @param listener
     */
    public void getBoardInfo(String name, int page, RequestListener listener) {
        getBoardInfo(name, 30, page, listener);
    }

}
