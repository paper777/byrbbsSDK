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
 * 鎶曠エ閫夐�?�缁撴�?�浣�?
 * @author dss886
 * @since 2014-9-7
 */
public class VoteOption {
	
	/** 鎶曠エ閫夐�?�鏍囪瘑id */
	public int viid;
	/** 閫夐」鍐呭 */
	public String label;
	/** �?归�夐�?�宸叉姇绁ㄦ暟锛屽鏋滆缃姇绁ㄥ悗鍙涓旇繕娌℃姇绁ㄨ繖涓�间负-1 */
	public int num;
	
	public static VoteOption parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return VoteOption.parse(jsonObject);
        } catch (JSONException e) {
        	VoteOption voteOption = new VoteOption();
        	voteOption.viid = Integer.parseInt(jsonString);
        	return voteOption;
        }
    }
	
	public static VoteOption parse(JSONObject jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        VoteOption voteOption = new VoteOption();
        voteOption.viid = jsonObject.optInt("viid", -1);
        voteOption.label = jsonObject.optString("user_name", "");
        voteOption.num = jsonObject.optInt("num", -1);
        return voteOption;
	}
}
