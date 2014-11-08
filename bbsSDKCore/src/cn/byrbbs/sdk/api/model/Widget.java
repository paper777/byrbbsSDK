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
 * Widget结构体
 * @author dss886
 * @since 2014-9-7
 */
public class Widget {

	/** widget标识 */
	public String name;
	/** widget标题 */
	public String title;
	/** 上次修改时间 */
	public int time;
	/** 十大热门话题所包含的文章元数据数组 */
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
