package cn.byrbbs.sdk.auth;

import cn.byrbbs.sdk.exception.BBSException;

import android.os.Bundle;

public abstract interface BBSAuthListener {
	public abstract void onComplete(Bundle paramBundle);
	public abstract void onException(BBSException paramException);
	public abstract void onCancel();
}
