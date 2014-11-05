package com.byrbbs.sdk.exception;

public class BBSHttpException extends Exception {
	private static final long serialVersionUID = 1L;
	private final int mStatusCode;

	public BBSHttpException(String message, int statusCode) {
		super(message);
		this.mStatusCode = statusCode;
	}

	public int getStatusCode() {
		return this.mStatusCode;
	}
}
