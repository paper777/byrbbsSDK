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
 * Widget缁撴瀯浣�?
 * @author dss886
 * @since 2014-9-7
 */
public class Widget {

	/** widget鏍囪�? */
	public String name;
	/** widget鏍囬�? */
	public String title;
	/** 涓婃淇敼鏃堕�? */
	public int time;
	/** 鍗佸ぇ鐑棬璇濋鎵�鍖呭惈鐨勬枃绔犲厓鏁版嵁鏁扮粍 */
	public List<Article> articles = new ArrayList<Article>();
	
	public static Widget parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return Widget.parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
	public static Widget parse(JSONObject jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        Widget widget = new Widget();
        widget.name = jsonObject.optString("name", "");
        widget.title = jsonObject.optString("title", "");
        JSONArray jsonArticles = jsonObject.optJSONArray("article");
        for(int i = 0; i < jsonArticles.length(); i++){
        	widget.articles.add(Article.parse(jsonArticles.optJSONObject(i)));
		}
        return widget;
	}
}
