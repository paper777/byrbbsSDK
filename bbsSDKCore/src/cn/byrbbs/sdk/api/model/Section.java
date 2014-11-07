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
 * 分区结构体
 * @author dss886
 * @since 2014-9-7
 */
public class Section {
	
	/** 分区名称 */
	public String name;
	/** 分区表述 */
	public String description;
	/** 是否是根分区 */
	public boolean is_root;
	/** 该分区所属根分区名称 */
	public String parent;
	/** 
	 * 根分区数量,
	 * 只存在于/section中
	 *  */
	public int section_count;
	/** 
	 * 所有根分区元数据所组成的数组,
	 * 只存在于/section中
	 *  */
	public ArrayList<Section> sections = new ArrayList<Section>();
	/** 
	 * 当前分区包含的分区目录名数组,
	 * 只存在于/section/:name中
	 *  */
	public String[] sub_section;
	/** 
	 * 当前分区包含的版面元数据数组,
	 * 只存在于/section/:name中
	 *  */
	public ArrayList<Board> boards = new ArrayList<Board>();
	
	public static Section parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return Section.parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
	@SuppressWarnings("null")
	public static Section parse(JSONObject jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        Section section = new Section();
        section.name = jsonObject.optString("name", "");
        section.description = jsonObject.optString("description", "");
        section.is_root = jsonObject.optBoolean("is_root", false);
        section.parent = jsonObject.optString("parent", "");
        section.section_count = jsonObject.optInt("section_count", -1);
        JSONArray jsonSections = jsonObject.optJSONArray("section");
        if(jsonSections != null){ 
        	for(int i = 0; i < jsonSections.length(); i++){
        		section.sections.add(Section.parse(jsonSections.optJSONObject(i)));
        	}
        }
        JSONArray jsonSubSections = jsonObject.optJSONArray("sub_section");
        if(jsonSubSections != null ){ 
        	section.sub_section = new String[jsonSubSections.length()];
        	for(int i = 0; i < jsonSubSections.length(); i++){
        		section.sub_section[i] = jsonSubSections.optString(i);
        	}
        }
        JSONArray jsonBoards = jsonObject.optJSONArray("board");
        if(jsonBoards != null){
        	for(int i = 0; i < jsonBoards.length(); i++){
        		section.boards.add(Board.parse(jsonBoards.optJSONObject(i)));
        	}
        }
        return section;
	}
}
