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
 * 鎶曠エ缁撴�?�浣�?
 * @author dss886
 * @since 2014-9-7
 */
public class Vote {
	
	/** 鎶曠エ鏍囪瘑id */
	public int vid;
	/** 鎶曠エ鏍囬 */
	public String title;
	/** 鎶曠エ鍙戣捣鏃堕棿鎴�? */
	public int start;
	/** 鎶曠エ鎴鏃堕棿鎴�? */
	public int end;
	/** 鎶曠エ鍙備笌鐨勪汉鏁�? */
	public int user_count;
	/** 
	 * 鎶曠エ鎬荤エ鏁�(鎶曠エ绫诲�?�涓哄崟閫夋椂涓巙ser_count鐩哥�?)锛屽鏋滆缃姇绁ㄥ悗鍙涓旇繕娌℃姇绁ㄨ繖涓�间�?-1
	 * 鍙瓨鍦ㄤ簬/vote/:id涓�
	 *  */
	public int vote_count;
	/** 鎶曠エ绫诲�?�锛�?0涓哄崟閫夛紝1涓哄閫�? */
	public int type;
	/** 姣忎釜鐢ㄦ埛鑳芥姇绁ㄦ暟鐨勬渶澶у�硷紝鍙湁褰搕ype涓�1鏃讹紝姝ゅ睘鎬ф湁鏁� */
	public int limit;
	/** 鎶曠エ鎵�鍏宠仈鐨勬姇绁ㄧ増闈㈢殑鏂囩珷id */
	public int aid;
	/** 鎶曠エ鏄惁鎴�? */
	public boolean is_end;
	/** 鎶曠エ鏄惁琚垹闄�? */
	public boolean is_deleted;
	/** 鎶曠エ缁撴灉鏄惁鎶曠エ鍚庡彲瑙�? */
	public boolean is_result_voted;
	/** 鎶曠エ鍙戣捣浜虹殑鐢ㄦ埛鍏冩暟鎹�? */
	public User user;
	/** 褰撳墠鐢ㄦ埛鏄惁鎶曠エ */
	public boolean is_voted;
	/** 褰撳墠鐢ㄦ埛鐨勬姇绁ㄦ椂闂达紝鏈姇绁ㄤ�?-1 */
	public int user_vote_time;
	/** 褰撳墠鐢ㄦ埛鐨勬姇绁ㄧ粨鏋滐紝鏈姇绁ㄤ负绌烘暟缁� */
	public List<VoteOption> user_voted_options = new ArrayList<VoteOption>();
	/** 鎶曠エ閫夐�?�锛�?敱鎶曠エ閫夐」鍏冩暟鎹粍鎴愮殑鏁扮�? */
	public List<VoteOption> options = new ArrayList<VoteOption>();
	/** 
	 * 鎵�鏌ヨ鐨勬姇绁ㄥ垪琛ㄧ殑鎶曠エ鍏冩暟鎹瀯鎴愮殑鏁扮�?
	 * 鍙瓨鍦ㄤ簬/vote/category/:cate涓�
	 *  */
	public List<Vote> votes = new ArrayList<Vote>();
	/** 
	 * 褰撳墠鎶曠エ鍒楄〃鐨勫垎椤典俊鎭�?
	 * 鍙瓨鍦ㄤ簬/vote/category/:cate涓�
	 *  */
	public Pagination pagination;
	
	public static Vote parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return Vote.parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
	public static Vote parse(JSONObject jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        Vote vote = new Vote();
        vote.vid = jsonObject.optInt("vid", -1);
        vote.title = jsonObject.optString("title", "");
        vote.start = jsonObject.optInt("start", -1);
        vote.end = jsonObject.optInt("end", -1);
        vote.user_count = jsonObject.optInt("user_count", -1);
        vote.vote_count = jsonObject.optInt("vote_count", -1);
        vote.type = jsonObject.optInt("type", -1);
        vote.limit = jsonObject.optInt("limit", -1);
        vote.aid = jsonObject.optInt("aid", -1);
        vote.is_end = jsonObject.optBoolean("is_end", false);
        vote.is_deleted = jsonObject.optBoolean("is_deleted", false);
        vote.is_result_voted = jsonObject.optBoolean("is_result_voted", false);
        vote.user = User.parse(jsonObject.optJSONObject("user"));
        JSONArray jsonOptions = jsonObject.optJSONArray("options");
    	for(int i = 0; i < jsonOptions.length(); i++){
    		vote.options.add(VoteOption.parse(jsonOptions.optJSONObject(i)));
    	}
    	JSONArray jsonVotes = jsonObject.optJSONArray("votes");
        for(int i = 0; i < jsonVotes.length(); i++){
        	vote.votes.add(Vote.parse(jsonVotes.optJSONObject(i)));
		}
        vote.pagination = Pagination.parse(jsonObject.optJSONObject("pagination"));
    	
    	vote.is_voted = jsonObject.optBoolean("voted", true);
    	if(vote.is_voted){
    		vote.user_vote_time = jsonObject.optJSONObject("voted").optInt("time", -1);
    		JSONArray jsonUserVotedOptions = jsonObject.optJSONObject("voted").optJSONArray("viid");
    		for(int i = 0; i < jsonUserVotedOptions.length(); i++){
    			try {
					vote.user_voted_options.add(VoteOption.parse(jsonUserVotedOptions.get(i).toString()));
				} catch (JSONException e) {
					e.printStackTrace();
				}
    		}
    	}else{
    		vote.user_vote_time = -1;
    	}
        return vote;
	}
}
