package cn.byrbbs.sdk.exception;

public class BBSDialogException extends BBSException {
	private static final long serialVersionUID = 1L;
	private int mErrorCode;
	private String mFailingUrl;

	public BBSDialogException(String message, int errorCode, String failingUrl) {
		super(message);
		this.mErrorCode = errorCode;
		this.mFailingUrl = failingUrl;
	}

	public int getErrorCode() {
		return this.mErrorCode;
	}

	public String getFailingUrl() {
		return this.mFailingUrl;
	}

}
