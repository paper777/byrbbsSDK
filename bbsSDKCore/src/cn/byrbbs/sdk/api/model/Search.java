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
 * 鎼滅储缁撴灉缁撴瀯浣�?
 * @author dss886
 * @since 2014-9-7
 */
public class Search {

	/** 
	 * 褰撳墠鎼滅储缁撴灉鎵�鍖呭惈鐨勫垎鍖虹洰褰曞悕鏁扮粍
	 * 浠呭瓨鍦ㄤ簬/search/:board涓�
	 *  */
	public List<Section> sections = new ArrayList<Section>();
	/** 
	 * 褰撳墠鎼滅储缁撴灉鎵�鍖呭惈鐨勭増闈㈠厓鏁版嵁鏁扮粍
	 * 浠呭瓨鍦ㄤ簬/search/:board涓�
	 *  */
	public List<Board> boards = new ArrayList<Board>();
	/** 褰撳墠鎼滅储缁撴灉鎵�鍖呭惈鐨勬枃绔犲厓鏁扮�? */
	public List<Article> articles = new ArrayList<Article>();
	/** 褰撳墠鎼滅储缁撴灉鍒嗛�?�淇℃伅 */
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
        for(int i = 0; i < jsonSections.length(); i++){
        	search.sections.add(Section.parse(jsonSections.optJSONObject(i)));
		}
        JSONArray jsonBoards = jsonObject.optJSONArray("board");
        for(int i = 0; i < jsonBoards.length(); i++){
        	search.boards.add(Board.parse(jsonBoards.optJSONObject(i)));
		}
        JSONArray jsonArticles = jsonObject.optJSONArray("article");
        for(int i = 0; i < jsonArticles.length(); i++){
        	search.articles.add(Article.parse(jsonArticles.optJSONObject(i)));
		}
        JSONArray jsonThreads = jsonObject.optJSONArray("threads");
        for(int i = 0; i < jsonThreads.length(); i++){
        	search.articles.add(Article.parse(jsonThreads.optJSONObject(i)));
		}
        search.pagination = Pagination.parse(jsonObject.optJSONObject("pagination"));
        return search;
	}
}
