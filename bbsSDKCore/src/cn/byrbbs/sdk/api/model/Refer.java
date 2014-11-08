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
 * 提醒结构体
 * @author dss886
 * @since 2014-9-7
 */
public class Refer {
	
	/** 提醒编号，此编号用于提醒的相关操作 */
	public int index;
	/** 提醒文章的id */
	public int id;
	/** 提醒文章的group id */
	public int group_id;
	/** 提醒文章的reply id */
	public int reply_id;
	/** 提醒文章所在版面 */
	public String board_name;
	/** 提醒文章的标题 */
	public String title;
	/** 提醒文章的发信人*/
	public User user;
	/** 发出提醒的时间 */
	public int time;
	/** 提醒是否已读 */
	public boolean is_read;
	/** 
	 * 提醒类型描述，包括：@我的文章，回复我的文章
	 * 仅存在与/refer/:type中
	 *  */
	public String description;
	/** 
	 * 当前提醒列表所包含的提醒元数据数组
	 * 仅存在与/refer/:type中
	 *  */
	public List<Refer> refers = new ArrayList<Refer>();
	/** 
	 * 当前提醒列表的分页信息
	 * 仅存在与/refer/:type中
	 *  */
	public Pagination pagination;
	/** 
	 * 当前类型的提醒是否启用
	 * 仅存在与/refer/:type/info中
	 *  */
	public boolean enable;
	/** 
	 * 当前类型的新提醒个数
	 * 仅存在与/refer/:type/info中
	 *  */
	public int new_count;
	
	public static Refer parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return Refer.parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
	public static Refer parse(JSONObject jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        Refer refer = new Refer();
        refer.index = jsonObject.optInt("index", -1);
        refer.id = jsonObject.optInt("id", -1);
        refer.group_id = jsonObject.optInt("group_id", -1);
        refer.reply_id = jsonObject.optInt("reply_id", -1);
        refer.board_name = jsonObject.optString("board_name", "");
        refer.title = jsonObject.optString("title", "");
        refer.user = User.parse(jsonObject.optJSONObject("user"));
        refer.time = jsonObject.optInt("time", -1);
        refer.is_read = jsonObject.optBoolean("has_attachment", true);
        refer.description = jsonObject.optString("description", "");
        JSONArray jsonRefers = jsonObject.optJSONArray("article");
        if(jsonRefers != null){
        	for(int i = 0; i < jsonRefers.length(); i++){
        		refer.refers.add(Refer.parse(jsonRefers.optJSONObject(i)));
        	}
        }
        refer.pagination = Pagination.parse(jsonObject.optJSONObject("pagination"));
        refer.enable = jsonObject.optBoolean("enable", true);
        refer.new_count = jsonObject.optInt("new_count", -1);
        return refer;
	}
}
