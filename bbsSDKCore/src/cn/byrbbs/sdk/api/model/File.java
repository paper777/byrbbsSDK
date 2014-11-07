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
 * 闄勪欢鏂囦欢缁撴瀯浣�?
 * @author dss886
 * @since 2014-9-7
 */
public class File {
	
	/** 鏂囦欢鍚�? */
	public String name;
	/** 鏂囦欢閾炬帴锛屽湪鐢ㄦ埛绌洪棿鐨勬枃浠讹紝璇ュ�间负绌� */
	public String url;
	/** 鏂囦欢澶у�? */
	public String size;
	/** 灏忕缉鐣ュ浘閾炬�?(瀹藉�?120px)锛岀敤鎴风┖闂寸殑鏂囦欢锛岃鍊间负绌� */
	public String thumbnail_small;
	/** 涓缉鐣ュ浘閾炬�?(瀹藉�?240px)锛岀敤鎴风┖闂寸殑鏂囦欢锛岃鍊间负绌� */
	public String thumbnail_middle;
	
	public static File parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return File.parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
	public static File parse(JSONObject jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        File file = new File();
        file.name = jsonObject.optString("name", "");
        file.url = jsonObject.optString("url", "");
        file.size = jsonObject.optString("size", "");
        file.thumbnail_small = jsonObject.optString("thumbnail_small", "");
        file.thumbnail_middle = jsonObject.optString("thumbnail_middle", "");
        return file;
	}
}
