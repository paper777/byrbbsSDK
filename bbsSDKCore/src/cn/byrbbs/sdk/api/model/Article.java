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
 * 鏂囩珷缁撴�?�浣�?
 * @author dss886
 * @since 2014-9-7
 */
public class Article {

	/** 鏂囩珷id */
	public int id;
	/** 璇ユ枃绔犳墍灞炰富棰樼殑id */
	public int group_id;
	/** 璇ユ枃绔犲洖澶嶆枃绔犵殑id */
	public int reply_id;
	/** 
	 * 鍗佸ぇ涓弬涓巌d鐨勬暟閲�?
	 * 姝ゅ睘鎬т粎�?�樺湪浜�?/widget/top10涓�
	 *  */
	public String id_count;
	/** 鏂囩珷鏍囪 鍒嗗埆鏄�? g ; b u o 8 */
	public String flag;
	/** 
	 * 鏂囩珷鎵�鍦ㄤ富棰樼殑浣嶇疆鎴栨枃绔犲湪榛樺啓娴忚妯�?�紡涓嬬殑浣嶇疆锛�
	 * /board/:name鐨勯潪涓婚妯�?�紡涓嬩负璁块棶姝ゆ枃绔犵殑id锛�
	 * 鍦�/threads/:board/:id涓负鎵�鍦ㄤ富棰樹腑鐨勪綅缃紝鍏朵綑涓虹�?
	 *  */
	public int position;
	/** 鏂囩珷鏄惁缃�? */
	public boolean is_top;
	/** 璇ユ枃绔犳槸鍚︽槸涓婚甯� */
	public boolean is_subject;
	/** 鏂囩珷鏄惁鏈夐檮浠�? */
	public boolean has_attachment;
	/** 褰撳墠鐧婚檰鐢ㄦ埛鏄惁瀵规枃绔犳湁绠＄悊鏉冮檺 鍖呮嫭缂栬緫锛屽垹闄わ紝淇敼闄勪欢 */
	public boolean is_admin;
	/** 鏂囩珷鏍囬 */
	public String title;
	/** 鏂囩珷鍙戣�?�鐢ㄦ埛 */
	public User user;
	/** 鏂囩珷鍙戣�?�鏃堕棿锛寀nixtimestamp鏍煎�? */
	public int post_time;
	/** 鎵�灞炵増闈㈠悕绉� */
	public String board_name;
	/** 
	 * 鏂囩珷鍐呭锛�
	 * 鍦�/board/:name鐨勬枃绔犲垪琛ㄥ�?/search/(article|threads)涓笉�?�樺湪姝ゅ睘鎬�
	 *  */
	public String content;
	/** 
	 * 鏂囩珷闄勪欢鍒楄〃锛�?
	 * 鍦�/board/:name鐨勬枃绔犲垪琛ㄥ�?/search/(article|threads)涓笉�?�樺湪姝ゅ睘鎬�
	 *  */
	public Attachment attachment;
	/** 
	 * 璇ユ枃绔犵殑鍓嶄竴绡囨枃绔爄d锛�
	 * 鍙瓨鍦ㄤ簬/article/:board/:id涓�
	 *  */
	public int previous_id;	
	/** 
	 * 璇ユ枃绔犵殑鍚庝竴绡囨枃绔爄d锛�
	 * 鍙瓨鍦ㄤ簬/article/:board/:id涓�
	 *  */
	public int next_id;	
	/** 
	 * 璇ユ枃绔犲悓涓婚鍓嶄竴绡囨枃绔爄d锛�
	 * 鍙瓨鍦ㄤ簬/article/:board/:id涓�
	 *  */
	public int threads_previous_id;	
	/** 
	 * 璇ユ枃绔犲悓涓婚鍚庝竴绡囨枃绔爄d锛�
	 * 鍙瓨鍦ㄤ簬/article/:board/:id涓�
	 *  */
	public int threads_next_id;	
	/** 
	 * 璇ヤ富棰樺洖澶嶆枃绔犳暟锛�
	 * 鍙瓨鍦ㄤ簬/board/:name锛�/threads/:board/:id鍜�/search/threads涓�
	 *  */
	public int reply_count;	
	/** 
	 * 璇ユ枃绔犳渶鍚庡洖澶嶈�呯殑id锛�
	 * 鍙瓨鍦ㄤ簬/board/:name锛�/threads/:board/:id鍜�/search/threads涓�
	 *  */
	public String last_reply_user_id;
	/** 
	 * 璇ユ枃绔犳渶鍚庡洖澶嶇殑鏃堕�? unxitmestamp鏍煎紡锛�?
	 * 鍙瓨鍦ㄤ簬/board/:name锛�/threads/:board/:id鍜�/search/threads涓�
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
