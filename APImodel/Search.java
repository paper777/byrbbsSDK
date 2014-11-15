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
 * 搜索结果结构体
 * @author dss886
 * @since 2014-9-7
 */
public class Search {

	/** 
	 * 当前搜索结果所包含的分区目录名数组
	 * 仅存在于/search/:board中
	 *  */
	public List<Section> sections = new ArrayList<Section>();
	/** 
	 * 当前搜索结果所包含的版面元数据数组
	 * 仅存在于/search/:board中
	 *  */
	public List<Board> boards = new ArrayList<Board>();
	/** 当前搜索结果所包含的文章元数组 */
	public List<Article> articles = new ArrayList<Article>();
	/** 当前搜索结果分页信息 */
	public Pagination pagination;
	
	public static Search parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return Search.parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
	public static Search parse(JSONObject jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        Search search = new Search();
        JSONArray jsonSections = jsonObject.optJSONArray("section");
        if(jsonSections != null){
        	for(int i = 0; i < jsonSections.length(); i++){
        		search.sections.add(Section.parse(jsonSections.optJSONObject(i)));
        	}
        }
        JSONArray jsonBoards = jsonObject.optJSONArray("board");
        if(jsonBoards != null){
        	for(int i = 0; i < jsonBoards.length(); i++){
        		search.boards.add(Board.parse(jsonBoards.optJSONObject(i)));
        	}
        }
        JSONArray jsonArticles = jsonObject.optJSONArray("article");
        if(jsonArticles != null){
        	for(int i = 0; i < jsonArticles.length(); i++){
        		search.articles.add(Article.parse(jsonArticles.optJSONObject(i)));
        	}
        }
        JSONArray jsonThreads = jsonObject.optJSONArray("threads");
        if(jsonThreads != null){
        	for(int i = 0; i < jsonThreads.length(); i++){
        		search.articles.add(Article.parse(jsonThreads.optJSONObject(i)));
        	}
        }
        search.pagination = Pagination.parse(jsonObject.optJSONObject("pagination"));
        return search;
	}
}
