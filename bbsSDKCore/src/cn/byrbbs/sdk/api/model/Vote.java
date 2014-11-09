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
 * 投票结构体
 * @author dss886
 * @since 2014-9-7
 */
public class Vote {
	
	/** 投票标识id */
	public int vid;
	/** 投票标题 */
	public String title;
	/** 投票发起时间戳 */
	public int start;
	/** 投票截止时间戳 */
	public int end;
	/** 投票参与的人数 */
	public int user_count;
	/** 
	 * 投票总票数(投票类型为单选时与user_count相等)，如果设置投票后可见且还没投票这个值为-1
	 * 只存在于/vote/:id中
	 *  */
	public int vote_count;
	/** 投票类型，0为单选，1为多选 */
	public int type;
	/** 每个用户能投票数的最大值，只有当type为1时，此属性有效 */
	public int limit;
	/** 投票所关联的投票版面的文章id */
	public int aid;
	/** 投票是否截止 */
	public boolean is_end;
	/** 投票是否被删除 */
	public boolean is_deleted;
	/** 投票结果是否投票后可见 */
	public boolean is_result_voted;
	/** 投票发起人的用户元数据 */
	public User user;
	/** 当前用户是否投票 */
	public boolean is_voted;
	/** 当前用户的投票时间，未投票为-1 */
	public int user_vote_time;
	/** 当前用户的投票结果，未投票为空数组 */
	public List<VoteOption> user_voted_options = new ArrayList<VoteOption>();
	/** 投票选项，由投票选项元数据组成的数组 */
	public List<VoteOption> options = new ArrayList<VoteOption>();
	/** 
	 * 所查询的投票列表的投票元数据构成的数组
	 * 只存在于/vote/category/:cate中
	 *  */
	public List<Vote> votes = new ArrayList<Vote>();
	/** 
	 * 当前投票列表的分页信息
	 * 只存在于/vote/category/:cate中
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
        if(jsonOptions != null){
        	for(int i = 0; i < jsonOptions.length(); i++){
        		vote.options.add(VoteOption.parse(jsonOptions.optJSONObject(i)));
        	}
        }
    	JSONArray jsonVotes = jsonObject.optJSONArray("votes");
    	if(jsonVotes != null){
    		for(int i = 0; i < jsonVotes.length(); i++){
    			vote.votes.add(Vote.parse(jsonVotes.optJSONObject(i)));
    		}
    	}
        vote.pagination = Pagination.parse(jsonObject.optJSONObject("pagination"));
    	
    	vote.is_voted = jsonObject.optBoolean("voted", true);
    	if(!vote.is_voted){
    		vote.user_vote_time = -1;
    	}else{
    		JSONObject tmp = jsonObject.optJSONObject("voted");
    		if(tmp != null){
    			vote.user_vote_time = tmp.optInt("time", -1);
    		}
    		tmp = jsonObject.optJSONObject("voted");
    		JSONArray jsonUserVotedOptions = null;
    		if(tmp != null){
    			jsonUserVotedOptions = tmp.optJSONArray("viid");
    		}
    		if(jsonUserVotedOptions != null){
    			for(int i = 0; i < jsonUserVotedOptions.length(); i++){
    				try {
    					vote.user_voted_options.add(VoteOption.parse(jsonUserVotedOptions.get(i).toString()));
    				} catch (JSONException e) {
    					e.printStackTrace();
    				}
    			}// for
    		}//if
    	}// else
        return vote;
	}
}
