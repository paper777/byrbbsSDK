package cn.byr.bbs.sdk.api;

import android.text.TextUtils;

import java.io.File;

import cn.byr.bbs.sdk.auth.Oauth2AccessToken;
import cn.byr.bbs.sdk.net.BBSParameters;
import cn.byr.bbs.sdk.net.RequestListener;

public class AttachmentApi extends BaseApi {

    private String ATT_URL = BASE_URL + "/attachment/";

    public AttachmentApi(Oauth2AccessToken accessToken) {
        super(accessToken);
    }

    /**
     * get the information of attachment(s)
     *
     * @param board
     * @param id       article id
     * @param listener
     */
    public void info(String board, int id, RequestListener listener) {
        if (TextUtils.isEmpty(board)) {
            return;
        }

        String url = ATT_URL + board + "/" + id;

        asyncRequest(url, HTTP_GET, null, listener);
    }


    /**
     * delete attachment(s)
     *
     * @param board
     * @param id       article id
     * @param attName  attachment name to delete
     * @param listener
     */
    public void delete(String board, int id, String attName, RequestListener listener) {
        if (TextUtils.isEmpty(board)) {
            return;
        }


        BBSParameters param = new BBSParameters();
        if (!TextUtils.isEmpty(attName)) {
            param.put("name", attName);
        }

        String url = ATT_URL + board + "/delete/" + id;
        asyncRequest(url, HTTP_POST, param, listener);
    }

    /**
     * upload attachment(s)
     *
     * @param board
     * @param id       article id
     * @param file
     * @param listener
     */
    public void upload(String board, int id, RequestListener listener, File file) {
        if (TextUtils.isEmpty(board)) {
            return;
        }

        BBSParameters param = new BBSParameters();
        if (file.exists()) {
            param.put("file", file);
        }
        String url = ATT_URL + board + "/add/" + id;
        asyncRequest(url, HTTP_POST, param, listener);
    }

}
