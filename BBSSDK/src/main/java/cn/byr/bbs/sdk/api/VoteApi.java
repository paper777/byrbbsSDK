package cn.byr.bbs.sdk.api;

import android.text.TextUtils;

import cn.byr.bbs.sdk.auth.Oauth2AccessToken;
import cn.byr.bbs.sdk.net.BBSParameters;
import cn.byr.bbs.sdk.net.RequestListener;
import cn.byr.bbs.sdk.utils.URLHelper;

/**
 * VOTE API
 *
 * @author ALSO
 */
public class VoteApi extends BaseApi {

    private String VOTE_URL = URLHelper.VOTE;

    public VoteApi(Oauth2AccessToken accessToken) {
        super(accessToken);
    }

    /**
     * get vote list
     *
     * @param category must be one of below 5
     *                 "me"  = current user's vote list
     *                 "join" = list of current user's joined
     *                 "new" = votes(still open) in reverse<b>(chronological)</b> order list
     *                 "hot" = votes(still open) in reverse<b>(number of participants)</b> order list
     *                 "all" = votes(all) in reverse chronological order list
     * @param listener
     */
    public void voteList(String category, RequestListener listener) {
        if (!(category.equals("me")
                || category.equals("join")
                || category.equals("new")
                || category.equals("hot")
                || category.equals("all"))) {
            return;
        }

        String url = VOTE_URL + "/category/" + category;
        asyncRequest(url, HTTP_GET, null, listener);
    }

    /**
     * query sb's vote history
     *
     * @param userid
     * @param listener
     */
    public void queryVoteList(String userid, RequestListener listener) {
        if (TextUtils.isEmpty(userid)) {
            return;
        }
        String url = VOTE_URL + "/category/list";

        BBSParameters param = new BBSParameters();
        param.put("u", userid);
        asyncRequest(url, HTTP_GET, param, listener);

    }

    /**
     * Vote
     *
     * @param vid
     * @param viid     array of vote options
     * @param isMulti  true = multiple choice
     * @param listener
     */
    public void vote(int vid, int[] viid, boolean isMulti, RequestListener listener) {
        if (viid.length == 0) {
            return;
        }
        BBSParameters param = new BBSParameters();
        if (!isMulti) {
            param.put("vote", viid[0]);
        } else if (viid.length > 0) {
            for (int i = 0; i < viid.length; i++) {
                param.put("vote[" + i + "]", viid[i] + "");
            }
        } else {
            return;
        }
        String url = VOTE_URL + '/' + vid;
        asyncRequest(url, HTTP_POST, param, listener);

    }

}
