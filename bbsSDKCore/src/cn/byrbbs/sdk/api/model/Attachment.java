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
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 闄勪欢缁撴�?�浣�?
 * @author dss886
 * @since 2014-9-7
 */
public class Attachment {
	
	/** 鏂囦欢鍒楄�?? */
	public ArrayList<File> file = new ArrayList<File>();
	/** 鍓╀綑绌洪棿澶у皬 */
	public String remain_space;
	/** 鍓╀綑闄勪欢涓�? */
	public int remain_count;
	
	public static Attachment parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return Attachment.parse(jsonObject);
        } catch (JSONException e) {
            return null;
        }
    }
	
	public static Attachment parse(JSONObject jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        Attachment att = new Attachment();
        JSONArray jsonFiles = jsonObject.optJSONArray("file");
        for(int i = 0; i < jsonFiles.length(); i++){
        	att.file.add(File.parse(jsonFiles.optJSONObject(i)));
		}
        att.remain_space = jsonObject.optString("remain_space", "");
        att.remain_count = jsonObject.optInt("remain_count", -1);
        return att;
	}
}