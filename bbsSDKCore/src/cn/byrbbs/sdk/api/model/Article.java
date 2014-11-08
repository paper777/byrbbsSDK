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
 * 文章结构体
 * @author dss886
 * @since 2014-9-7
 */
public class Article {

	/** 文章id */
	public int id;
	/** 该文章所属主题的id */
	public int group_id;
	/** 该文章回复文章的id */
	public int reply_id;
	/** 
	 * 十大中参与id的数量
	 * 此属性仅存在于/widget/top10中
	 *  */
	public String id_count;
	/** 文章标记 分别是m g ; b u o 8 */
	public String flag;
	/** 
	 * 文章所在主题的位置或文章在默写浏览模式下的位置，
	 * /board/:name的非主题模式下为访问此文章的id，
	 * 在/threads/:board/:id中为所在主题中的位置，其余为空
	 *  */
	public int position;
	/** 文章是否置顶 */
	public boolean is_top;
	/** 该文章是否是主题帖 */
	public boolean is_subject;
	/** 文章是否有附件 */
	public boolean has_attachment;
	/** 当前登陆用户是否对文章有管理权限 包括编辑，删除，修改附件 */
	public boolean is_admin;
	/** 文章标题 */
	public String title;
	/** 文章发表用户 */
	public User user;
	/** 文章发表时间，unixtimestamp格式 */
	public int post_time;
	/** 所属版面名称 */
	public String board_name;
	/** 
	 * 文章内容，
	 * 在/board/:name的文章列表和/search/(article|threads)中不存在此属性
	 *  */
	public String content;
	/** 
	 * 文章附件列表，
	 * 在/board/:name的文章列表和/search/(article|threads)中不存在此属性
	 *  */
	public Attachment attachment;
	/** 
	 * 该文章的前一篇文章id，
	 * 只存在于/article/:board/:id中
	 *  */
	public int previous_id;	
	/** 
	 * 该文章的后一篇文章id，
	 * 只存在于/article/:board/:id中
	 *  */
	public int next_id;	
	/** 
	 * 该文章同主题前一篇文章id，
	 * 只存在于/article/:board/:id中
	 *  */
	public int threads_previous_id;	
	/** 
	 * 该文章同主题后一篇文章id，
	 * 只存在于/article/:board/:id中
	 *  */
	public int threads_next_id;	
	/** 
	 * 该主题回复文章数，
	 * 只存在于/board/:name，/threads/:board/:id和/search/threads中
	 *  */
	public int reply_count;	
	/** 
	 * 该文章最后回复者的id，
	 * 只存在于/board/:name，/threads/:board/:id和/search/threads中
	 *  */
	public String last_reply_user_id;
	/** 
	 * 该文章最后回复的时间 unxitmestamp格式，
	 * 只存在于/board/:name，/threads/:board/:id和/search/threads中
	 *  */
	public int last_reply_time;
	
	public static Article parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return Article.parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
	public static Article parse(JSONObject jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        Article article = new Article();
        article.id = jsonObject.optInt("id", -1);
        article.group_id = jsonObject.optInt("group_id", -1);
        article.reply_id = jsonObject.optInt("reply_id", -1);
        article.id_count = jsonObject.optString("id_count", "");
        article.flag = jsonObject.optString("flag", "");
        article.position = jsonObject.optInt("position", -1);
        article.is_top = jsonObject.optBoolean("is_top", false);
        article.is_subject = jsonObject.optBoolean("is_subject", false);
        article.has_attachment = jsonObject.optBoolean("has_attachment", false);
        article.is_admin = jsonObject.optBoolean("is_admin", false);
        article.title = jsonObject.optString("title", "");
        article.user = User.parse(jsonObject.optJSONObject("user"));
        article.post_time = jsonObject.optInt("post_time", -1);
        article.board_name = jsonObject.optString("board_name", "");
        article.content = jsonObject.optString("content", "");
        article.attachment = Attachment.parse(jsonObject.optJSONObject("attachment"));
        article.previous_id = jsonObject.optInt("previous_id", -1);
        article.next_id = jsonObject.optInt("next_id", -1);
        article.threads_previous_id = jsonObject.optInt("threads_previous_id", -1);
        article.threads_next_id = jsonObject.optInt("threads_next_id", -1);
        article.reply_count = jsonObject.optInt("reply_count", -1);
        article.last_reply_user_id = jsonObject.optString("last_reply_user_id", "");
        article.last_reply_time = jsonObject.optInt("last_reply_time", -1);
        return article;
	}
}
