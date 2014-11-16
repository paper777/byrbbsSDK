package cn.byrbbs.sdk.exception;

public class BBSAuthException extends BBSException {
	private static final long serialVersionUID = 1L;
	public static final String DEFAULT_AUTH_ERROR_CODE = "-1";
	public static final String DEFAULT_AUTH_ERROR_DESC = "Unknown Error Description";
	public static final String DEFAULT_AUTH_ERROR_TYPE = "Unknown Error Type";

	public BBSAuthException(String errorDescription) {
		super(errorDescription);
	}

}
