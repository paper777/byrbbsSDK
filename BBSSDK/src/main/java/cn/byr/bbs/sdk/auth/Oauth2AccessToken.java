package cn.byr.bbs.sdk.auth;

import android.os.Bundle;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ALSO on 2015/3/31.
 */
public class Oauth2AccessToken {
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_EXPIRES_IN = "expires_in";
    private static final String KEY_REFRESH_TOKEN = "refresh_token";

    // if authorization successful, we will get
// {access_token, access_token_expires, refresh_token, refresh_token_expires}
// from redirect url
    private String mAccessToken = "";

    private String mRefreshToken = "";

    private long mExpiresTime = 0L;

    // constructor
    public Oauth2AccessToken() {
    }

    public Oauth2AccessToken(String accessToken, String expiresIn) {
        this.setAccessToken(accessToken);

        // we convert expires time here: period to time point
        this.setExpiresTime(System.currentTimeMillis());
        if (expiresIn != null) {
            this.setExpiresTime(this.getExpiresTime() + Long.parseLong(expiresIn) * 1000L);
        } // if
    }


    // func: paresAccessToken(String responseJsonText)
    // des: convert Json String which contains access token information to String and store
    // @return Class token OR null(if failed)
    public static Oauth2AccessToken parseAccessToken(String responseJsonText) {
        if ((!TextUtils.isEmpty(responseJsonText)) &&
                (responseJsonText.indexOf("{") >= 0)) {
            try {
                JSONObject json = new JSONObject(responseJsonText);
                Oauth2AccessToken token = new Oauth2AccessToken();
                token.setAccessToken(json.optString(KEY_ACCESS_TOKEN));
                token.setExpiresIn(json.optString(KEY_EXPIRES_IN));
                token.setRefreshToken(json.optString(KEY_REFRESH_TOKEN));
                return token;
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }// try
        return null;
    }

    public static Oauth2AccessToken parseAccessToken(Bundle bundle) {
        if (bundle != null) {
            Oauth2AccessToken accessToken = new Oauth2AccessToken();
            accessToken.setAccessToken(getString(bundle, KEY_ACCESS_TOKEN, ""));
            accessToken.setExpiresIn(getString(bundle, KEY_EXPIRES_IN, ""));
            accessToken.setRefreshToken(getString(bundle, KEY_REFRESH_TOKEN, ""));
            return accessToken;
        }
        return null;
    }

    // get string frome bundle
    private static String getString(Bundle bundle, String key, String defaultValue) {
        if (bundle != null) {
            String value = bundle.getString(key);
            return value != null ? value : defaultValue;
        }
        return defaultValue;
    }

    // func: isSessionValid()
    // des: if access token OR expires time is invalid, return false
    public boolean isSessionValid() {
        return (!TextUtils.isEmpty(this.mAccessToken))
                && (this.mExpiresTime != 0L)
                && (System.currentTimeMillis() < this.mExpiresTime);
    }

    // func: setExpiresIn(String expires)
    // des: convert expires time from period to point and store it
    public void setExpiresIn(String expires) {
        if ((!TextUtils.isEmpty(expires)) && (!expires.equals("0"))) {
            setExpiresTime(System.currentTimeMillis() + Long.parseLong(expires) * 1000L);
        }
    }

    // func: toBundle()
    // des: put these access token to Bundle
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ACCESS_TOKEN, this.mAccessToken);
        bundle.putString(KEY_REFRESH_TOKEN, this.mRefreshToken);
        bundle.putString(KEY_EXPIRES_IN, Long.toString(this.mExpiresTime));
        return bundle;
    }

    public String toString() {
        return KEY_ACCESS_TOKEN + ": " + this.mAccessToken + ", " +
                KEY_EXPIRES_IN + ": " + Long.toString(this.mExpiresTime) + ", " +
                KEY_REFRESH_TOKEN + ": " + this.mRefreshToken;
    }

    // setters & getters
    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String mAccessToken) {
        this.mAccessToken = mAccessToken;
    }

    public String getRefreshToken() {
        return mRefreshToken;
    }

    public void setRefreshToken(String mRefreshToken) {
        this.mRefreshToken = mRefreshToken;
    }

    public long getExpiresTime() {
        return mExpiresTime;
    }

    public void setExpiresTime(long mExpiresTime) {
        this.mExpiresTime = mExpiresTime;
    }
}