package cn.byr.bbs.sdk.api;

import cn.byr.bbs.sdk.auth.Oauth2AccessToken;
import cn.byr.bbs.sdk.net.BBSParameters;
import cn.byr.bbs.sdk.net.RequestListener;

/**
 * Refer(notify) API
 *
 * @author ALSO
 */
public class ReferApi extends BaseApi {

    private String RF_URL = BASE_URL + "/refer/";

    public ReferApi(Oauth2AccessToken accessToken) {
        super(accessToken);
    }

    /**
     * get mentions
     *
     * @param count
     * @param page
     * @param listener
     */
    public void mention(int count, int page, RequestListener listener) {
        BBSParameters param = new BBSParameters();
        param.put("count", count);
        param.put("page", page);

        String url = RF_URL + "at";

        asyncRequest(url, HTTP_GET, param, listener);
    }

    /**
     * get mentions using default parameters
     *
     * @param listener
     */
    public void mention(RequestListener listener) {
        mention(20, 1, listener);
    }

    /**
     * get reply
     *
     * @param count
     * @param page
     * @param listener
     */
    public void reply(int count, int page, RequestListener listener) {
        BBSParameters param = new BBSParameters();
        param.put("count", count);
        param.put("page", page);

        String url = RF_URL + "reply";

        asyncRequest(url, HTTP_GET, param, listener);
    }

    /**
     * get reply using default parameters
     *
     * @param listener
     */
    public void reply(RequestListener listener) {
        reply(20, 1, listener);
    }

    /**
     * get refer's properties
     *
     * @param type     must be one of below 2
     *                 "at" = mentioned article
     *                 "reply" = replied article
     * @param listener
     */
    public void info(String type, RequestListener listener) {
        if (type.equals("at") || type.equals("reply")) {
            String url = RF_URL + type + "/info";
            asyncRequest(url, HTTP_GET, null, listener);
        }
    }

    /**
     * set read
     *
     * @param type     must be one of below 2
     *                 "at" = mentioned article
     *                 "reply" = replied article
     * @param index    refer meta index number
     * @param listener
     */
    public void setRead(String type, int index, RequestListener listener) {
        if (type.equals("at") || type.equals("reply")) {
            String url = RF_URL + type + "/setRead/" + index;
            asyncRequest(url, HTTP_POST, null, listener);
        }
    }

    /**
     * read all
     *
     * @param type     must be one of below 2
     *                 "at" = mentioned article
     *                 "reply" = replied article
     * @param listener
     */
    public void readAll(String type, RequestListener listener) {
        if (type.equals("at") || type.equals("reply")) {
            String url = RF_URL + type + "/setRead";
            asyncRequest(url, HTTP_POST, null, listener);
        }
    }

    /**
     * delete refer
     *
     * @param type     must be one of below 2
     *                  "at" = mentioned article
     *                 "reply" = replied article
     * @param index    refer meta index number
     * @param listener
     */
    public void delete(String type, int index, RequestListener listener) {
        if (type.equals("at") || type.equals("reply")) {
            String url = RF_URL + type + "/delete/" + index;
            asyncRequest(url, HTTP_POST, null, listener);
        }
    }

    /**
     * delete all refers
     *
     * @param type     must be one of below 2
     *                  "at" = mentioned article
     *                  "reply" = replied article
     * @param listener
     */
    public void deleteAll(String type, RequestListener listener) {
        if (type.equals("at") || type.equals("reply")) {
            String url = RF_URL + type + "/delete";
            asyncRequest(url, HTTP_POST, null, listener);
        }
    }

}
