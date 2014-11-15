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

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 用户信箱结构体
 * @author dss886
 * @since 2014-9-7
 */
public class Mailbox {
	
	/** 是否有新邮件 */
	public boolean new_mail;
	/** 信箱是否已满 */
	public boolean full_mail;
	/** 信箱已用空间 */
	public String space_used;
	/** 当前用户是否能发信 */
	public boolean can_send;
	/** 
	 * 信箱类型描述，包括：收件箱，发件箱，废纸篓，
	 * 仅存在于/mail/:box中
	 *  */
	public String description;
	/** 
	 * 当前信箱所包含的信件元数据数组，
	 * 仅存在于/mail/:box中
	 *  */
	public List<Mail> mails;
	/** 
	 * 当前信箱的分页信息，
	 * 仅存在于/mail/:box中
	 *  */
	public Pagination pagination;
	
	public static Mailbox parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return Mailbox.parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
	public static Mailbox parse(JSONObject jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        Mailbox mailbox = new Mailbox();
        mailbox.new_mail = jsonObject.optBoolean("new_mail", false);
        mailbox.full_mail = jsonObject.optBoolean("full_mail", false);
        mailbox.space_used = jsonObject.optString("space_used", "");
        mailbox.can_send = jsonObject.optBoolean("can_send", true);
        mailbox.description = jsonObject.optString("description", "");
        JSONArray jsonMails = jsonObject.optJSONArray("article");
        if(jsonMails != null){
        	for(int i = 0; i < jsonMails.length(); i++){
        		mailbox.mails.add(Mail.parse(jsonMails.optJSONObject(i)));
        	}
        }
        mailbox.pagination = Pagination.parse(jsonObject.optJSONObject("pagination"));
        return mailbox;
	}
}
