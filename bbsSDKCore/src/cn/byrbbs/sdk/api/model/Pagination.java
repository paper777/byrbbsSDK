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

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 分页结构体
 * @author dss886
 * @since 2014-9-7
 */
public class Pagination {

	/** 总页数 */
	public int page_all_count;
	/** 当前页数 */
	public int page_current_count;
	/** 每页元素个数 */
	public int item_page_count;
	/** 所有元素个数 */
	public int item_all_count;

	public static Pagination parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return Pagination.parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
	public static Pagination parse(JSONObject jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        Pagination page = new Pagination();
        page.page_all_count = jsonObject.optInt("page_all_count", -1);
        page.page_current_count = jsonObject.optInt("page_current_count", -1);
        page.item_page_count = jsonObject.optInt("item_page_count", -1);
        page.item_all_count = jsonObject.optInt("item_all_count", -1);
        return page;
	}
}
