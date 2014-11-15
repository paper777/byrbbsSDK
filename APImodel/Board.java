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
 * 版面结构体
 * @author dss886
 * @since 2014-9-7
 */
public class Board {
	
	/** 版面名称 */
	public String name;
	/** 版主列表，以空格分隔各个id */
	public String manager;
	/** 版面描述 */
	public String description;
	/** 版面所属类别 */
	public String classify;
	/** 版面所属分区号 */
	public String section;
	/** 今日发文总数 */
	public int post_today_count;
	/** 版面主题总数 */
	public int post_threads_count;
	/** 版面文章总数 */
	public int post_all_count;
	/** 版面是否只读 */
	public boolean is_read_only;
	/** 版面是否不可回复 */
	public boolean is_no_reply;
	/** 版面书否允许附件 */
	public boolean allow_attachment;
	/** 版面是否允许匿名发文 */
	public boolean allow_anonymous;
	/** 版面是否允许转信 */
	public boolean allow_outgo;
	/** 当前登陆用户是否有发文/回复权限 */
	public boolean allow_post;
	/** 版面当前在线用户数 */
	public int user_online_count;
	/** 版面历史最大在线用户数 */
	public int user_online_max_count;
	/** 版面历史最大在线用户数发生时间 */
	public int user_online_max_time;
	/** 当前版面模式所包含的文章元数组 */
	public Pagination pagination;
	/** 当前版面模式的分页信息 */
	public List<Article> articles = new ArrayList<Article>();
	
	public static Board parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return Board.parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
	public static Board parse(JSONObject jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        Board board = new Board();
        board.name = jsonObject.optString("name", "");
        board.manager = jsonObject.optString("manager", "");
        board.description = jsonObject.optString("description", "");
        board.classify = jsonObject.optString("class", "");
        board.section = jsonObject.optString("section", "");
        board.post_today_count = jsonObject.optInt("post_today_count", -1);
        board.post_threads_count = jsonObject.optInt("post_threads_count", -1);
        board.post_all_count = jsonObject.optInt("post_all_count", -1);
        board.is_read_only = jsonObject.optBoolean("is_read_only", false);
        board.is_no_reply = jsonObject.optBoolean("is_no_reply", false);
        board.allow_attachment = jsonObject.optBoolean("allow_attachment", true);
        board.allow_anonymous = jsonObject.optBoolean("allow_anonymous", false);
        board.allow_outgo = jsonObject.optBoolean("allow_outgo", false);
        board.allow_post = jsonObject.optBoolean("allow_post", true);
        board.user_online_count = jsonObject.optInt("user_online_count", -1);
        board.user_online_count = jsonObject.optInt("user_online_count", -1);
        board.user_online_max_time = jsonObject.optInt("user_online_max_time", -1);
        JSONArray jsonArticles = jsonObject.optJSONArray("article");
        if(jsonArticles != null){
        	for(int i = 0; i < jsonArticles.length(); i++){
        		board.articles.add(Article.parse(jsonArticles.optJSONObject(i)));
        	}
        }
        board.pagination = Pagination.parse(jsonObject.optJSONObject("pagination"));
        return board;
	}
}
