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
 * 鎻愰啋缁撴�?�浣�?
 * @author dss886
 * @since 2014-9-7
 */
public class Refer {
	
	/** 鎻愰啋缂栧彿锛屾缂栧彿鐢ㄤ簬鎻愰啋鐨勭浉鍏虫搷浣� */
	public int index;
	/** 鎻愰啋鏂囩珷鐨刬d */
	public int id;
	/** 鎻愰啋鏂囩珷鐨刧roup id */
	public int group_id;
	/** 鎻愰啋鏂囩珷鐨剅eply id */
	public int reply_id;
	/** 鎻愰啋鏂囩珷鎵�鍦ㄧ増闈�? */
	public String board_name;
	/** 鎻愰啋鏂囩珷鐨勬爣棰�? */
	public String title;
	/** 鎻愰啋鏂囩珷鐨勫彂淇′汉*/
	public User user;
	/** 鍙戝嚭鎻愰啋鐨勬椂闂�? */
	public int time;
	/** 鎻愰啋鏄惁宸茶�? */
	public boolean is_read;
	/** 
	 * 鎻愰啋绫诲�?�鎻忚堪锛屽寘鎷細@鎴戠殑鏂囩珷锛屽洖澶嶆垜鐨勬枃绔�?
	 * 浠呭瓨鍦ㄤ笌/refer/:type涓�
	 *  */
	public String description;
	/** 
	 * 褰撳墠鎻愰啋鍒楄〃鎵�鍖呭惈鐨勬彁閱掑厓鏁版嵁鏁扮粍
	 * 浠呭瓨鍦ㄤ笌/refer/:type涓�
	 *  */
	public List<Refer> refers = new ArrayList<Refer>();
	/** 
	 * 褰撳墠鎻愰啋鍒楄〃鐨勫垎椤典俊鎭�?
	 * 浠呭瓨鍦ㄤ笌/refer/:type涓�
	 *  */
	public Pagination pagination;
	/** 
	 * 褰撳墠绫诲�?�鐨勬彁閱掓槸鍚�?惎鐢�?
	 * 浠呭瓨鍦ㄤ笌/refer/:type/info涓�
	 *  */
	public boolean enable;
	/** 
	 * 褰撳墠绫诲�?�鐨勬柊鎻愰啋涓暟
	 * 浠呭瓨鍦ㄤ笌/refer/:type/info涓�
	 *  */
	public int new_count;
	
	public static Refer parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return Refer.parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
	public static Refer parse(JSONObject jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        Refer refer = new Refer();
        refer.index = jsonObject.optInt("index", -1);
        refer.id = jsonObject.optInt("id", -1);
        refer.group_id = jsonObject.optInt("group_id", -1);
        refer.reply_id = jsonObject.optInt("reply_id", -1);
        refer.board_name = jsonObject.optString("board_name", "");
        refer.title = jsonObject.optString("title", "");
        refer.user = User.parse(jsonObject.optJSONObject("user"));
        refer.time = jsonObject.optInt("time", -1);
        refer.is_read = jsonObject.optBoolean("has_attachment", true);
        refer.description = jsonObject.optString("description", "");
        JSONArray jsonRefers = jsonObject.optJSONArray("article");
        for(int i = 0; i < jsonRefers.length(); i++){
        	refer.refers.add(Refer.parse(jsonRefers.optJSONObject(i)));
		}
        refer.pagination = Pagination.parse(jsonObject.optJSONObject("pagination"));
        refer.enable = jsonObject.optBoolean("enable", true);
        refer.new_count = jsonObject.optInt("new_count", -1);
        return refer;
	}
}
