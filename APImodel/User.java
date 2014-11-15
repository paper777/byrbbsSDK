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
 * 用户信息结构�?
 * @author dss886
 * @since 2014-9-7
 */
public class User {
	
	/** 用户id */
	public String id;
	/** 用户昵称 */
	public String user_name;
	/** 用户头像地址 */
	public String face_url;
	/** 用户头像宽度 */
	public int face_width;
	/** 用户头像高度 */
	public int face_height;
	/** 用户性别：m表示男�?�，f表示女�?�，n表示隐藏性别 */
	public String gender;
	/** 用户星座 若隐藏星座则为空 */
	public String astro;
	/** 用户生命�? */
	public int life;
	/** 用户qq */
	public String qq;
	/** 用户msn */
	public String msn;
	/** 用户个人主页 */
	public String home_page;
	/** 用户身份 */
	public String level;
	/** 用户是否在线 */
	public boolean is_online;
	/** 用户发文数量 */
	public int post_count;
	/** 用户上次登录时间，unixtimestamp格式 */
	public int last_login_time;
	/** 用户上次登录 */
	public String last_login_ip;
	/** 用户是否隐藏性别和星�? */
	public boolean is_hide;
	/** 用户是否通过注册审批 */
	public boolean is_register;
	/** 
	 * 用户积分�?
	 * 此属性为隐藏属�?�，在某些情况下可用
	 *  */
	public String score;
	/** 
	 * 用户注册时间，unixtimestamp格式�?
	 * 当前登陆用户为自己或是当前用户具有管理权限时可用
	 *  */
	public int first_login_time;
	/** 
	 * 用户登陆次数�?
	 * 当前登陆用户为自己或是当前用户具有管理权限时可用
	 *  */
	public int login_count;
	/** 
	 * 用户是否为管理员�?
	 * 当前登陆用户为自己或是当前用户具有管理权限时可用
	 *  */
	public boolean is_admin;
	/** 
	 * 用户挂站时间，以秒为单位�?
	 * 当前登陆用户为自己或是当前用户具有管理权限时可用
	 *  */
	public int stay_count;

	public static User parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return User.parse(jsonObject);
        } catch (JSONException e) {
        	User user = new User();
        	user.id = jsonString;
        	return user;
        }
    }
	
	public static User parse(JSONObject jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        User user = new User();
        user.id = jsonObject.optString("id", "");
        user.user_name = jsonObject.optString("user_name", "");
        user.face_url = jsonObject.optString("face_url", "");
        user.face_width = jsonObject.optInt("face_width", -1);
        user.face_height = jsonObject.optInt("face_height", -1);
        user.gender = jsonObject.optString("gender", "");
        user.astro = jsonObject.optString("astro", "");
        user.life = jsonObject.optInt("life", -1);
        user.qq = jsonObject.optString("qq", "");
        user.msn = jsonObject.optString("msn", "");
        user.home_page = jsonObject.optString("home_page", "");
        user.level = jsonObject.optString("level", "");
        user.is_online = jsonObject.optBoolean("is_online", false);
        user.post_count = jsonObject.optInt("post_count", -1);
        user.last_login_time = jsonObject.optInt("last_login_time", -1);
        user.last_login_ip = jsonObject.optString("last_login_ip", "");
        user.is_hide = jsonObject.optBoolean("is_hide", false);
        user.is_register = jsonObject.optBoolean("is_register", true);
        user.score = jsonObject.optString("score", "");
        user.first_login_time = jsonObject.optInt("first_login_time", -1);
        user.login_count = jsonObject.optInt("login_count", -1);
        user.is_admin = jsonObject.optBoolean("is_admin", false);
        user.stay_count = jsonObject.optInt("stay_count", -1);
        return user;
	}
}
