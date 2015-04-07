package cn.byr.bbs.sdkdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import cn.byr.bbs.sdk.auth.Oauth2AccessToken;

/**
 * 该类定义了微博授权时�?�?要的参数�?
 */
public class AccessTokenKeeper {
    private static final String PREFERENCES_NAME = "bbs_oauth_demo";

    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_EXPIRES_IN = "expires_in";
    private static final String KEY_REFRESH_TOKEN = "refresh_token";

    /**
     * 保存 Token 对象�? SharedPreferences�?
     *
     * @param context 应用程序上下文环�?
     * @param token   Token 对象
     */
    public static void writeAccessToken(Context context, Oauth2AccessToken token) {
        if (null == context || null == token) {
            return;
        }

        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
        Editor editor = pref.edit();
        editor.putString(KEY_ACCESS_TOKEN, token.getAccessToken());
        editor.putLong(KEY_EXPIRES_IN, token.getExpiresTime());
        editor.putString(KEY_REFRESH_TOKEN, token.getRefreshToken());
        editor.commit();
    }

    /**
     * �? SharedPreferences 读取 Token 信息�?
     *
     * @param context 应用程序上下文环�?
     * @return 返回 Token 对象
     */
    public static Oauth2AccessToken readAccessToken(Context context) {
        if (null == context) {
            return null;
        }

        Oauth2AccessToken token = new Oauth2AccessToken();
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
        token.setAccessToken(pref.getString(KEY_ACCESS_TOKEN, ""));
        token.setExpiresTime(pref.getLong(KEY_EXPIRES_IN, 0));
        token.setRefreshToken(pref.getString(KEY_REFRESH_TOKEN, ""));
        return token;
    }

    /**
     * 清空 SharedPreferences �? Token信息�?
     *
     * @param context 应用程序上下文环�?
     */
    public static void clear(Context context) {
        if (null == context) {
            return;
        }

        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
        Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}
