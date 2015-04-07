package cn.byr.bbs.sdk.exception;

/**
 * Created by ALSO on 2015/3/31.
 */
public class BBSDialogException extends BBSException {
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
