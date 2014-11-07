/*
 * Copyright (C) 2010-2014 dss886
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.byrbbs.sdk.api.model;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 淇�?�欢缁撴瀯浣�?
 * @author dss886
 * @since 2014-9-7
 */
public class Mail {
	
	/** 淇�?�欢缂栧彿锛屾缂栧彿涓�?/mail/:box/:num涓殑num */
	public int index;
	/** 鏄惁鏍囪涓簃 */
	public boolean is_m;
	/** 鏄惁宸茶 */
	public boolean is_read;
	/** 鏄惁鍥炲 */
	public boolean is_reply;
	/** 鏄惁鏈夐檮浠� */
	public boolean has_attachment;
	/** 淇�?�欢鏍囬�? */
	public String title;
	/** 鍙戜俊浜�? */
	public User user;
	/** 鍙戜俊鏃堕棿 */
	public int post_time;
	/** 鎵�灞炰俊绠卞悕 */
	public String box_name;
	/** 
	 * 淇�?�欢鍐呭�?
	 * 鍙瓨鍦ㄤ簬/mail/:box/:num涓�
	 *  */
	public String content;
	/** 
	 * 淇�?�欢鐨勯檮浠跺垪琛�
	 * 鍙瓨鍦ㄤ簬/mail/:box/:num涓�
	 *  */
	public Attachment attachment;
	
	public static Mail parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return Mail.parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
	public static Mail parse(JSONObject jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        Mail mail = new Mail();
        mail.index = jsonObject.optInt("index", -1);
        mail.is_m = jsonObject.optBoolean("is_m", false);
        mail.is_read = jsonObject.optBoolean("is_read", true);
        mail.is_reply = jsonObject.optBoolean("is_reply", false);
        mail.has_attachment = jsonObject.optBoolean("has_attachment", false);
        mail.title = jsonObject.optString("title", "");
        mail.user = User.parse(jsonObject.optString("user", ""));
        mail.post_time = jsonObject.optInt("post_time", -1);
        mail.box_name = jsonObject.optString("box_name", "");
        mail.content = jsonObject.optString("content", "");
        mail.attachment = Attachment.parse(jsonObject.optString("attachment", ""));
        return mail;
	}
}
