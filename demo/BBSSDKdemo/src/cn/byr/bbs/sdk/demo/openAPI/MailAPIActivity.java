package cn.byr.bbs.sdk.demo.openAPI;

import cn.byr.bbs.sdk.demo.AccessTokenKeeper;
import cn.byr.bbs.sdk.demo.R;
import cn.byrbbs.sdk.api.MailApi;
import cn.byrbbs.sdk.api.model.Mail;
import cn.byrbbs.sdk.api.model.Mailbox;
import cn.byrbbs.sdk.auth.Oauth2AccessToken;
import cn.byrbbs.sdk.exception.BBSException;
import cn.byrbbs.sdk.net.RequestListener;
import cn.byrbbs.sdk.utils.LogUtil;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MailAPIActivity extends Activity implements OnItemClickListener {
	   private static final String TAG = MailAPIActivity.class.getName();
	    
		private ListView mFuncListView;
	    /** func list */
	    private String[] mFuncList;

	    private MailApi mMailApi;
	    
	    private Oauth2AccessToken mAccessToken;
	    
		@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.openapi_item);
	        
	        mAccessToken = AccessTokenKeeper.readAccessToken(this);
	        mMailApi = new MailApi(mAccessToken);
	        
	        // 获取功能列表
	        mFuncList = getResources().getStringArray(R.array.mail_func_list);
	        // 初始化功能列表 ListView
	        mFuncListView = (ListView)findViewById(R.id.api_func_list);
	        mFuncListView.setAdapter(new ArrayAdapter<String>(
	        		this, android.R.layout.simple_list_item_1, mFuncList));
	        mFuncListView.setOnItemClickListener(this);       
		}

		private RequestListener mListener = new RequestListener(){
				@Override
				public void onComplete(String response) {
			            if (!TextUtils.isEmpty(response)) {
			                LogUtil.i(TAG, response);
			                
			                Mail mail = Mail.parse(response);
			                if (mail != null) {
			                    Toast.makeText(MailAPIActivity.this, 
			                            "Success: " + mail.title, 
			                            Toast.LENGTH_LONG).show();
			                } else {
			                    Toast.makeText(MailAPIActivity.this, response, Toast.LENGTH_LONG).show();
			                }
			            }
			        }

					@Override
					public void onException(BBSException e) {
						// TODO Auto-generated method stub
			            LogUtil.e(TAG, e.getMessage());
					}	
		};
		
		private RequestListener mboxListener = new RequestListener(){
				@Override
				public void onComplete(String response) {
			            if (!TextUtils.isEmpty(response)) {
			                LogUtil.i(TAG, response);
			                
			                Mailbox mailbox = Mailbox.parse(response);
			                if (mailbox != null) {
			                    Toast.makeText(MailAPIActivity.this, 
			                            "Success: " + mailbox.new_mail, 
			                            Toast.LENGTH_LONG).show();
			                } else {
			                    Toast.makeText(MailAPIActivity.this, response, Toast.LENGTH_LONG).show();
			                }
			            }
			        }

					@Override
					public void onException(BBSException e) {
						// TODO Auto-generated method stub
			            LogUtil.e(TAG, e.getMessage());
					}	
		};
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			if (view instanceof TextView) {
	            if (mAccessToken != null && mAccessToken.isSessionValid()) {
	                switch (position) {
	                case 0:
	                	mMailApi.inbox(mListener);
	                    break;
	                    
	                case 1:
	                	mMailApi.outbox(mListener);
	                    break;
	                    
	                case 2:
	                	mMailApi.trashbox(mListener);
	                    break;
	                    
	                case 3:
	                	mMailApi.boxInfo(mboxListener);
	                    break;
	                    
	                case 4:
	                	mMailApi.showMail("inbox", 4, mListener);
	                    break;
	                    
	                case 5:
	                	mMailApi.send("paper777", "notitle", "tst", 0, 1, mListener);
	                    break;
	                    
	                case 6:
	                	mMailApi.reply("inbox", 4, "notitle", "tst", 0, 1, mListener);
	                    break;
	                    
	                case 7:
	                	mMailApi.forward("inbox", 4, "paper777", mListener);
	                    break;
	                    
	                case 8:
	                	mMailApi.delete("inbox", 0, mListener);
	                    break;
	                    
	                default:
	                    break;
	                }
	            } else {
	                Toast.makeText(MailAPIActivity.this, 
	                        R.string.bbsSDK_token_empty, 
	                        Toast.LENGTH_LONG).show();
	            }// else
	        }// if( view)
		}// func

}
