package cn.byr.bbs.sdk.exception;

/**
 * Created by ALSO on 2015/3/31.
 */
public class BBSException extends Exception {
    public BBSException() {
    }

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
