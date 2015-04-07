package cn.byr.bbs.sdk.exception;

/**
 * Created by ALSO on 2015/3/31.
 */
public class BBSAuthException extends BBSException {

    public static final String DEFAULT_AUTH_ERROR_CODE = "-1";
    public static final String DEFAULT_AUTH_ERROR_DESC = "Unknown Error Description";
    public static final String DEFAULT_AUTH_ERROR_TYPE = "Unknown Error Type";

    public BBSAuthException(String errorDescription) {
        super(errorDescription);
    }
}
