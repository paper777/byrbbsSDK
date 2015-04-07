package cn.byr.bbs.sdk.api;

import android.text.TextUtils;

import cn.byr.bbs.sdk.auth.Oauth2AccessToken;
import cn.byr.bbs.sdk.net.BBSParameters;
import cn.byr.bbs.sdk.net.RequestListener;

/**
 * Mail API
 *
 * @author ALSO
 */
public class MailApi extends BaseApi {

    private String M_URL = BASE_URL + "/mail/";

    public MailApi(Oauth2AccessToken accessToken) {
        super(accessToken);
    }

    /**
     * show in box using default params
     *
     * @param listener
     */
    public void inbox(RequestListener listener) {
        String url = M_URL + "inbox";
        asyncRequest(url, HTTP_GET, null, listener);
    }

    /**
     * show out box using default params
     *
     * @param listener
     */
    public void outbox(RequestListener listener) {
        String url = M_URL + "outbox";
        asyncRequest(url, HTTP_GET, null, listener);
    }

    /**
     * show trash box using default params
     *
     * @param listener
     */
    public void trashbox(RequestListener listener) {
        String url = M_URL + "deleted";
        asyncRequest(url, HTTP_GET, null, listener);
    }


    /**
     * show mailbox info <b>include new mail checking</b>
     *
     * @param listener
     */
    public void boxInfo(RequestListener listener) {
        String url = M_URL + "info";
        asyncRequest(url, HTTP_GET, null, listener);
    }

    /**
     * show mail
     *
     * @param boxType  must be one of below 3
     *                 "inbox"
     *                 "outbox"
     *                 "deleted": trash box
     * @param index
     * @param listener
     */
    public void showMail(String boxType, int index, RequestListener listener) {
        if (boxType == null || index < 0) {
            return;
        }
        if (boxType.equals("inbox")
                || boxType.equals("outbox")
                || boxType.equals("deleted")) {
            String url = M_URL + boxType + "/" + index;
            asyncRequest(url, HTTP_GET, null, listener);
        }
    }

    /**
     * send a mail
     *
     * @param userid
     * @param title
     * @param content
     * @param sig      0: no signature OR 1~6 signature number
     * @param backup   1: backup 0: nooop
     * @param listener
     */
    public void send(String userid, String title,
                     String content, int sig, int backup,
                     RequestListener listener) {
        if (userid == null || TextUtils.isEmpty(userid)) {
            return;
        }
        BBSParameters param = new BBSParameters();
        param.put("id", userid);
        // title and content can be empty
        if (title != null) {
            param.put("title", title);
        }
        if (content != null) {
            param.put("content", content);
        }

        param.put("signature", sig);
        param.put("backup", backup);

        String url = M_URL + "send";
        asyncRequest(url, HTTP_POST, param, listener);
    }

    /**
     * reply a mail
     *
     * @param boxType  must be one of below 3
     *                 inbox"
     *                 outbox"
     *                 deleted":  trash box
     * @param index
     * @param title
     * @param content
     * @param sig
     * @param backup
     * @param listener
     */
    public void reply(String boxType, int index, String title,
                      String content, int sig, int backup, RequestListener listener) {
        if (boxType == null || index < 0) {
            return;
        }
        if (boxType.equals("inbox")
                || boxType.equals("outbox")
                || boxType.equals("deleted")) {

            BBSParameters param = new BBSParameters();
            // title and content can be empty
            if (title != null) {
                param.put("title", title);
            }
            if (content != null) {
                param.put("content", content);
            }
            param.put("signature", sig);
            param.put("backup", backup);

            String url = M_URL + boxType + "/reply/" + index;
            asyncRequest(url, HTTP_POST, param, listener);
        }// if
    }

    /**
     * delete a mail
     *
     * @param boxType  must be one of below 3
     *                 inbox"
     *                 outbox"
     *                 deleted":  trash box
     * @param index
     * @param listener
     */
    public void delete(String boxType, int index, RequestListener listener) {
        if (boxType == null || index < 0) {
            return;
        }
        if (boxType.equals("inbox")
                || boxType.equals("outbox")
                || boxType.equals("deleted")) {
            String url = M_URL + boxType + "/delete/" + index;
            asyncRequest(url, HTTP_POST, null, listener);
        }
    }// func


    /**
     * forward a mail
     *
     * @param boxType      must be one of below 3
     *                     inbox"
     *                     outbox"
     *                     deleted": trash box
     * @param index
     * @param targetUserid
     * @param noansi       0= keep ANSI 1=noop
     * @param big5         1=encode with BIG5 0= noop
     * @param listener
     */
    public void forward(String boxType, int index, String targetUserid,
                        int noansi, int big5, RequestListener listener) {
        if (boxType == null || index < 0) {
            return;
        }
        if (targetUserid == null || TextUtils.isEmpty(targetUserid)) {
            return;
        }
        if (boxType.equals("inbox")
                || boxType.equals("outbox")
                || boxType.equals("deleted")) {

            BBSParameters param = new BBSParameters();
            param.put("target", targetUserid);
            param.put("noansi", noansi);
            param.put("big5", big5);
            String url = M_URL + boxType + "/forward/" + index;
            asyncRequest(url, HTTP_POST, param, listener);
        }
    }// func

    /**
     * forward a mail using default params
     *
     * @param boxType
     * @param index
     * @param targetUserid
     * @param listener
     */
    public void forward(String boxType, int index, String targetUserid, RequestListener listener) {
        forward(boxType, index, targetUserid, 0, 0, listener);
    }
}
