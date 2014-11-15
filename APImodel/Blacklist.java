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
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 黑名单结构体
 * @author dss886
 * @since 2014-9-7
 */
public class Blacklist {
	
	/** 黑名单用户的用户元数组 */
	public List<User> users = new ArrayList<User>();
	/** 黑名单列表分页信息 */
	public Pagination pagination;
	
	public static Blacklist parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return Blacklist.parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
	public static Blacklist parse(JSONObject jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        Blacklist blacklist = new Blacklist();
        JSONArray jsonUsers = jsonObject.optJSONArray("user");
        for(int i = 0; i < jsonUsers.length(); i++){
        	blacklist.users.add(User.parse(jsonUsers.optJSONObject(i)));
		}
        blacklist.pagination = Pagination.parse(jsonObject.optJSONObject("pagination"));
        return blacklist;
	}
}
