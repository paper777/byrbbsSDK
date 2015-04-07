package cn.byr.bbs.sdk.net;

import cn.byr.bbs.sdk.exception.BBSException;

/**
 * Created by ALSO on 2015/3/31.
 */
public abstract interface RequestListener {
    public abstract void onComplete(String paramString);

    public abstract void onException(BBSException paramException);
}
