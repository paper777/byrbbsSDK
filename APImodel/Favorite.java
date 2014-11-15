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
 * 收藏夹结构体
 * @author dss886
 * @since 2014-9-7
 */
public class Favorite {

	/** 
	 * 收藏夹级数，顶层收藏夹level为0
	 * 此属性为元数据属性
	 *  */
	public int level;
	/** 
	 * 收藏夹目录
	 * 此属性为元数据属性
	 *  */
	public String description;
	/** 
	 * 收藏夹目录位置，该值用于删除收藏夹目录
	 * 此属性为元数据属性
	 * 	 */
	public int position;
	/** 该层收藏夹包含的自定义目录的数组，数组元素为收藏夹元数据 */
	public List<Favorite> sub_favorite = new ArrayList<Favorite>();
	/** 该层收藏夹包含的分区的数组，数组元素为分区元数据 */
	public List<Section> sections = new ArrayList<Section>();
	/** 该层收藏夹包含的版面的数组，数组元素为版面元数据 */
	public List<Board> boards = new ArrayList<Board>();
	
	public static Favorite parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return Favorite.parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
	public static Favorite parse(JSONObject jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        Favorite favourite = new Favorite();
        favourite.level = jsonObject.optInt("level", -1);
        favourite.description = jsonObject.optString("description", "");
        favourite.position = jsonObject.optInt("position", -1);
        JSONArray jsonSubFavourite = jsonObject.optJSONArray("sub_favorite");
        for(int i = 0; i < jsonSubFavourite.length(); i++){
        	favourite.sub_favorite.add(Favorite.parse(jsonSubFavourite.optJSONObject(i)));
		}
        JSONArray jsonSections = jsonObject.optJSONArray("section");
        for(int i = 0; i < jsonSections.length(); i++){
        	favourite.sections.add(Section.parse(jsonSections.optJSONObject(i)));
		}
        JSONArray jsonBoards = jsonObject.optJSONArray("board");
        for(int i = 0; i < jsonBoards.length(); i++){
        	favourite.boards.add(Board.parse(jsonBoards.optJSONObject(i)));
		}
        return favourite;
	}
}
