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
 * 鐗堥潰缁撴�?�浣�?
 * @author dss886
 * @since 2014-9-7
 */
public class Board {
	
	/** 鐗堥潰鍚嶇�? */
	public String name;
	/** 鐗堜富鍒楄�?�锛屼互绌烘牸鍒嗛殧鍚勪釜id */
	public String manager;
	/** 鐗堥潰鎻忚堪 */
	public String description;
	/** 鐗堥潰鎵�灞炵被鍒� */
	public String classify;
	/** 鐗堥潰鎵�灞炲垎鍖哄�? */
	public String section;
	/** 浠婃棩鍙戞枃鎬绘�? */
	public int post_today_count;
	/** 鐗堥潰涓婚鎬绘�? */
	public int post_threads_count;
	/** 鐗堥潰鏂囩珷鎬绘�? */
	public int post_all_count;
	/** 鐗堥潰鏄惁鍙�? */
	public boolean is_read_only;
	/** 鐗堥潰鏄惁涓嶅彲鍥炲 */
	public boolean is_no_reply;
	/** 鐗堥潰涔�?惁鍏佽闄勪�? */
	public boolean allow_attachment;
	/** 鐗堥潰鏄惁鍏佽鍖垮悕鍙戞�? */
	public boolean allow_anonymous;
	/** 鐗堥潰鏄惁鍏佽杞俊 */
	public boolean allow_outgo;
	/** 褰撳墠鐧婚檰鐢ㄦ埛鏄惁鏈夊彂鏂�?/鍥炲鏉冮檺 */
	public boolean allow_post;
	/** 鐗堥潰褰撳墠鍦ㄧ嚎鐢ㄦ埛鏁� */
	public int user_online_count;
	/** 鐗堥潰鍘嗗彶鏈�澶у湪绾跨敤鎴锋暟 */
	public int user_online_max_count;
	/** 鐗堥潰鍘嗗彶鏈�澶у湪绾跨敤鎴锋暟鍙戠敓鏃堕棿 */
	public int user_online_max_time;
	/** 褰撳墠鐗堥潰妯�?�紡鎵�鍖呭惈鐨勬枃绔犲厓鏁扮粍 */
	public Pagination pagination;
	/** 褰撳墠鐗堥潰妯�?�紡鐨勫垎椤典俊鎭� */
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
