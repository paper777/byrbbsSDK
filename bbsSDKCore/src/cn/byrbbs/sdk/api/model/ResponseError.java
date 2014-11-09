package cn.byrbbs.sdk.api.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Error code structure
 * @author ALSO
 *
 */
public class ResponseError {
	public String code;
	
	public String description;
	
	public String requestURL;
	
	public static ResponseError parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return ResponseError.parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public static ResponseError parse(JSONObject jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        ResponseError error = new ResponseError();
        error.code = jsonObject.optString("code", "");
        if(error.code == ""){ return null; } // not a error response
        error.description = jsonObject.optString("msg", "");
        error.requestURL = jsonObject.optString("request", "");
        return error;
	}
}
