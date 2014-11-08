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
 * 附件文件结构体
 * @author dss886
 * @since 2014-9-7
 */
public class FileMeta {
	
	/** 文件名 */
	public String name;
	/** 文件链接，在用户空间的文件，该值为空 */
	public String url;
	/** 文件大小 */
	public String size;
	/** 小缩略图链接(宽度120px)，用户空间的文件，该值为空 */
	public String thumbnail_small;
	/** 中缩略图链接(宽度240px)，用户空间的文件，该值为空 */
	public String thumbnail_middle;
	
	public static FileMeta parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return FileMeta.parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
	public static FileMeta parse(JSONObject jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        FileMeta file = new FileMeta();
        file.name = jsonObject.optString("name", "");
        file.url = jsonObject.optString("url", "");
        file.size = jsonObject.optString("size", "");
        file.thumbnail_small = jsonObject.optString("thumbnail_small", "");
        file.thumbnail_middle = jsonObject.optString("thumbnail_middle", "");
        return file;
	}
}
