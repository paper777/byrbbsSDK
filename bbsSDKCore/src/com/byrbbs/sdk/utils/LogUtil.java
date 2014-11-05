package com.byrbbs.sdk.utils;

import android.util.Log;

public class LogUtil {
	private static boolean sIsLogEnable = true;

	public static void enableLog() {
		sIsLogEnable = true;
	}

	public static void disableLog() {
		sIsLogEnable = false;
	}

	public static void d(String tag, String msg) {
		if (sIsLogEnable) {
			StackTraceElement stackTrace = java.lang.Thread.currentThread()
					.getStackTrace()[3];
			String fileInfo = stackTrace.getFileName() + "("
					+ stackTrace.getLineNumber() + ") "
					+ stackTrace.getMethodName();
			Log.d(tag, fileInfo + ": " + msg);
		}
	}

	public static void i(String tag, String msg) {
		if (sIsLogEnable) {
			StackTraceElement stackTrace = java.lang.Thread.currentThread()
					.getStackTrace()[3];
			String fileInfo = stackTrace.getFileName() + "("
					+ stackTrace.getLineNumber() + ") "
					+ stackTrace.getMethodName();
			Log.i(tag, fileInfo + ": " + msg);
		}
	}

	public static void e(String tag, String msg) {
		if (sIsLogEnable) {
			StackTraceElement stackTrace = java.lang.Thread.currentThread()
					.getStackTrace()[3];
			String fileInfo = stackTrace.getFileName() + "("
					+ stackTrace.getLineNumber() + ") "
					+ stackTrace.getMethodName();
			Log.e(tag, fileInfo + ": " + msg);
		}
	}

	public static void w(String tag, String msg) {
		if (sIsLogEnable) {
			StackTraceElement stackTrace = java.lang.Thread.currentThread()
					.getStackTrace()[3];
			String fileInfo = stackTrace.getFileName() + "("
					+ stackTrace.getLineNumber() + ") "
					+ stackTrace.getMethodName();
			Log.w(tag, fileInfo + ": " + msg);
		}
	}

	public static void v(String tag, String msg) {
		if (sIsLogEnable) {
			StackTraceElement stackTrace = java.lang.Thread.currentThread()
					.getStackTrace()[3];
			String fileInfo = stackTrace.getFileName() + "("
					+ stackTrace.getLineNumber() + ") "
					+ stackTrace.getMethodName();
			Log.v(tag, fileInfo + ": " + msg);
		}
	}

	public static String getStackTraceMsg() {
		StackTraceElement stackTrace = java.lang.Thread.currentThread()
				.getStackTrace()[3];
		String fileInfo = stackTrace.getFileName() + "("
				+ stackTrace.getLineNumber() + ") "
				+ stackTrace.getMethodName();
		return fileInfo;
	}
}
