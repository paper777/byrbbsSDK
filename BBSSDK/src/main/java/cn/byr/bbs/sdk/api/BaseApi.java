package cn.byr.bbs.sdk.api;

import android.text.TextUtils;

import cn.byr.bbs.sdk.auth.Oauth2AccessToken;
import cn.byr.bbs.sdk.net.AsyncRunner;
import cn.byr.bbs.sdk.net.BBSParameters;
import cn.byr.bbs.sdk.net.RequestListener;
import cn.byr.bbs.sdk.utils.LogUtil;

/**
 * base class
 *
 * @author ALSO
 */
public class BaseApi {
    protected static final String TAG = "api";

    protected static final String BASE_URL = "http://eid.byr.cn/paper/nforum/open"; // TODO for debug

    protected static final String ACCESS_TOKEN = "oauth_token";

    protected static final String HTTP_POST = "POST";
    protected static final String HTTP_GET = "GET";

    protected static final String RETURN_JSON = ".json";
    protected static final String RETURN_XML = ".xml";

    protected Oauth2AccessToken mAccessToken;

    /**
     * construct
     *
     * @param accessToken: access token obj
     */
    public BaseApi(Oauth2AccessToken accessToken) {
        this.mAccessToken = accessToken;
    }

    /**
     * send a asynchronous HTTP request
     *
     * @param url
     * @param httpMethod
     * @param params
     * @param rqListener callback interface
     */
    public void asyncRequest(String url, String httpMethod, BBSParameters params, RequestListener rqListener) {
        if (null == mAccessToken
                || TextUtils.isEmpty(url)
                || TextUtils.isEmpty(httpMethod)
                || null == rqListener) {
            LogUtil.e(TAG, "Argument error!");
            return;
        }
        if (params == null) params = new BBSParameters();
        params.put(ACCESS_TOKEN, mAccessToken.getAccessToken());
        url += RETURN_JSON;
        AsyncRunner.requestAsync(url, params, httpMethod, rqListener);
    }

}
