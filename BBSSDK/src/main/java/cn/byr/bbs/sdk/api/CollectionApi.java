package cn.byr.bbs.sdk.api;

import android.text.TextUtils;

import cn.byr.bbs.sdk.auth.Oauth2AccessToken;
import cn.byr.bbs.sdk.net.BBSParameters;
import cn.byr.bbs.sdk.net.RequestListener;
import cn.byr.bbs.sdk.utils.URLHelper;

/**
 * Created by ALSO on 2015/5/19.
 */
public class CollectionApi extends BaseApi {
    /**
     * construct
     * @param accessToken : access token obj
     */
    public CollectionApi(Oauth2AccessToken accessToken) {
        super(accessToken);
    }

    /**
     * show collection list
     * @param page int
     * @param count int article count of each page
     * @param listener
     */
    public void showList(int page, int count, RequestListener listener) {
        if(count > 50 || count < 1 || page < 1) {
            return;
        }

        BBSParameters param = new BBSParameters();
        param.put("count", count);
        param.put("page", page);
        asyncRequest(URLHelper.COLLECTION, HTTP_GET, param, listener);
    }

    /**
     * show collection list using default count number 30
     * @param page
     * @param listener
     */
    public void showList(int page, RequestListener listener) {
        showList(page, 30, listener );
    }


    public void add(String board, int id, RequestListener listener) {
        if(TextUtils.isEmpty(board)) {
            return;
        }

        BBSParameters param = new BBSParameters();
        param.put("board", board);
        param.put("id", id);
        asyncRequest(URLHelper.COLLECTION+"/add", HTTP_POST, param, listener);
    }

    public void delete(int id, RequestListener listener) {
        BBSParameters param = new BBSParameters();
        param.put("id", id);
        asyncRequest(URLHelper.COLLECTION+"/delete", HTTP_POST, param, listener);
    }
}
