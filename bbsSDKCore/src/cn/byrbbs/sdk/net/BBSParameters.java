package cn.byrbbs.sdk.net;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Set;

import android.graphics.Bitmap;
import android.text.TextUtils;

/*
 *  class bbsparameters 
 *  usually to store request parameters for sending
 */
public class BBSParameters {
	
	private LinkedHashMap<String, Object> mParams = new LinkedHashMap<String, Object>();

	// LinkedHashMap setter and getter
	public LinkedHashMap<String, Object> getParams() {
		return mParams;
	}

	public void setParams(LinkedHashMap<String, Object> Params) {
		this.mParams = Params;
	}
	
	
	// putters
	public void put(String key, String val){
		this.mParams.put(key, val);
	}
	
	public void put(String key, int val){
		this.mParams.put(key, String.valueOf(val));
	}
	
	public void put(String key, Object val){
		this.mParams.put(key, val.toString());
	}
	
	// getter
	public Object get(String key) {
		return this.mParams.get(key);
	}
	
	public Set<String> keySet() {
		return this.mParams.keySet();
	}
	// remove
	public void remove(String key){
		if(mParams.containsKey(key)){
			mParams.remove(key);
			// confirm delete
			mParams.remove(mParams.get(key));
		}
	}

	public String encodeUrl() {
	    StringBuilder sb = new StringBuilder();
	    boolean first = true;
	    for (String key : this.mParams.keySet()) {
	    	//  first parameter doesn't need "&"
	    	if (first){
	    		first = false;
	    	} else {
	    		sb.append("&");
	    	}

	    	Object value = this.mParams.get(key);
	    	if ((value instanceof String)) {
	    		String param = (String)value;
	    		
	    		if (!TextUtils.isEmpty(param)) { // need to check to void useless parameters
	    			try {
	    				sb.append(URLEncoder.encode(key, "UTF-8") + "=" + 
	    						URLEncoder.encode(param, "UTF-8"));
	    			} catch (UnsupportedEncodingException e) {
	    				e.printStackTrace();
	    			}
	    		}
	    		/* send log info. TODO */ // LogUtil.i("encodeUrl", sb.toString());
	    	} // if (value instanceof....)
	    }// for
	    return sb.toString();	
	} // func

	// func: boolean hasBinaryData()
	// des: handle message sending when user send binary attachments(pic, music...)
	public boolean hasBinaryData() {
		// check every parameters
		for (String key : this.mParams.keySet()) {
			Object value = this.mParams.get(key);
			if (((value instanceof ByteArrayOutputStream)) || 
					((value instanceof Bitmap))) {
				return true;
			}
		}
		return false;
	}


}
