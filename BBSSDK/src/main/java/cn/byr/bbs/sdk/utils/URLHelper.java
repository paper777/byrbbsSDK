package cn.byr.bbs.sdk.utils;

import cn.byr.bbs.sdk.Config;

/**
 * Created by ALSO on 2015/4/7.
 */
public class URLHelper {

    public static final String URL_OAUTH2_AUTHORIZE = (Config.Debug == true ?
            "http://eid.byr.cn/paper/nforum/oauth2/authorize" :
            "http://bbs.byr.cn/oauth2/authorize" );

    private static final String URL_BBS_API = (Config.Debug == true ?
            "http://eid.byr.cn/paper/nforum/open/" :
            "http://bbs.byr.cn/open/" );

    public static final String ARTICLE = URL_BBS_API + "article";
    public static final String THREADS= URL_BBS_API + "threads";
    public static final String ATTACHMENT = URL_BBS_API + "attachment";
    public static final String BLACKLIST = URL_BBS_API + "blacklist";
    public static final String BOARD = URL_BBS_API + "board";
    public static final String FAV = URL_BBS_API + "favorite";
    public static final String MAIL = URL_BBS_API + "mail";
    public static final String REFER = URL_BBS_API + "refer";
    public static final String SEARCH = URL_BBS_API + "search";
    public static final String SECTION = URL_BBS_API + "section";
    public static final String USER = URL_BBS_API + "user";
    public static final String VOTE = URL_BBS_API + "vote";
    public static final String WIDGET = URL_BBS_API + "widget";
    public static final String COLLECTION = URL_BBS_API + "collection";

    /**
     * TODO
     */

}
