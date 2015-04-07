package cn.byr.bbs.sdk.exception;

/**
 * Created by ALSO on 2015/3/31.
 */
public class BBSHttpException extends BBSException {
    private final int mStatusCode;

    public BBSHttpException(String message, int statusCode) {
        super(message);
        this.mStatusCode = statusCode;
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }
}
