package cn.byrbbs.sdk.exception;

public class BBSException extends RuntimeException {
	private static final long serialVersionUID = 475022994858770424L;
	public BBSException() { }

	public BBSException(String message) {
		super(message);
	}

	public BBSException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public BBSException(Throwable throwable) {
		super(throwable);
	}

}
