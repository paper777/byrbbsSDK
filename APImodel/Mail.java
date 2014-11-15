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
 * 信件结构体
 * @author dss886
 * @since 2014-9-7
 */
public class Mail {
	
	/** 信件编号，此编号为/mail/:box/:num中的num */
	public int index;
	/** 是否标记为m */
	public boolean is_m;
	/** 是否已读 */
	public boolean is_read;
	/** 是否回复 */
	public boolean is_reply;
	/** 是否有附件 */
	public boolean has_attachment;
	/** 信件标题 */
	public String title;
	/** 发信人 */
	public User user;
	/** 发信时间 */
	public int post_time;
	/** 所属信箱名 */
	public String box_name;
	/** 
	 * 信件内容
	 * 只存在于/mail/:box/:num中
	 *  */
	public String content;
	/** 
	 * 信件的附件列表
	 * 只存在于/mail/:box/:num中
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
