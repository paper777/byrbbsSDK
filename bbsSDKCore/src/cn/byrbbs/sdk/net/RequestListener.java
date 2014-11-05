package cn.byrbbs.sdk.net;

import cn.byrbbs.sdk.exception.BBSException;

public abstract interface RequestListener {
  public abstract void onComplete(String paramString);
  public abstract void onException(BBSException paramWeiboException);
}