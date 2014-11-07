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
 * �?惰棌澶圭粨鏋勪綋
 * @author dss886
 * @since 2014-9-7
 */
public class Favourite {

	/** 
	 * �?惰棌澶圭骇鏁帮紝椤跺眰鏀惰棌澶筶evel涓�0
	 * 姝ゅ睘鎬т负鍏冩暟鎹睘鎬�?
	 *  */
	public int level;
	/** 
	 * �?惰棌澶圭洰褰�?
	 * 姝ゅ睘鎬т负鍏冩暟鎹睘鎬�?
	 *  */
	public String description;
	/** 
	 * �?惰棌澶圭洰褰曚綅缃紝璇ュ�肩敤浜庡垹闄ゆ敹钘忓す鐩綍
	 * 姝ゅ睘鎬т负鍏冩暟鎹睘鎬�?
	 * 	 */
	public int position;
	/** 璇ュ眰鏀惰棌澶瑰寘鍚殑鑷畾涔夌洰褰曠殑鏁扮粍锛屾暟缁勫厓绱犱负鏀惰棌澶瑰厓鏁版嵁 */
	public List<Favourite> sub_favorite = new ArrayList<Favourite>();
	/** 璇ュ眰鏀惰棌澶瑰寘鍚殑鍒嗗尯鐨勬暟缁勶紝鏁扮粍鍏冪礌涓哄垎鍖哄厓鏁版嵁 */
	public List<Section> sections = new ArrayList<Section>();
	/** 璇ュ眰鏀惰棌澶瑰寘鍚殑鐗堥潰鐨勬暟缁勶紝鏁扮粍鍏冪礌涓虹増闈㈠厓鏁版嵁 */
	public List<Board> boards = new ArrayList<Board>();
	
	public static Favourite parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return Favourite.parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
	public static Favourite parse(JSONObject jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        Favourite favourite = new Favourite();
        favourite.level = jsonObject.optInt("level", -1);
        favourite.description = jsonObject.optString("description", "");
        favourite.position = jsonObject.optInt("position", -1);
        JSONArray jsonSubFavourite = jsonObject.optJSONArray("sub_favorite");
        for(int i = 0; i < jsonSubFavourite.length(); i++){
        	favourite.sub_favorite.add(Favourite.parse(jsonSubFavourite.optJSONObject(i)));
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
