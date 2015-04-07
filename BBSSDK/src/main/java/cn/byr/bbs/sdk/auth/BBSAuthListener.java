package cn.byr.bbs.sdk.auth;

import android.os.Bundle;

import cn.byr.bbs.sdk.exception.BBSException;

/**
 * Created by ALSO on 2015/3/31.
 */
public abstract interface BBSAuthListener {
    public abstract void onComplete(Bundle paramBundle);

    public abstract void onException(BBSException paramException);

    public abstract void onCancel();
}
