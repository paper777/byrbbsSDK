package cn.byr.bbs.sdk.demo.openAPI;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.byr.bbs.sdk.demo.AccessTokenKeeper;
import cn.byr.bbs.sdk.demo.R;
import cn.byrbbs.sdk.api.VoteApi;
import cn.byrbbs.sdk.api.model.Vote;
import cn.byrbbs.sdk.auth.Oauth2AccessToken;
import cn.byrbbs.sdk.exception.BBSException;
import cn.byrbbs.sdk.net.RequestListener;
import cn.byrbbs.sdk.utils.LogUtil;

public class VoteAPIActivity extends Activity implements OnItemClickListener {
	   private static final String TAG = VoteAPIActivity.class.getName();
	    
		private ListView mFuncListView;
	    /** func list */
	    private String[] mFuncList;

	    private VoteApi mVoteApi;
	    
	    private Oauth2AccessToken mAccessToken;
	    
		@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.openapi_item);
	        
	        mAccessToken = AccessTokenKeeper.readAccessToken(this);
	        mVoteApi = new VoteApi(mAccessToken);
	        
	        // 获取功能列表
	        mFuncList = getResources().getStringArray(R.array.vote_func_list);
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
			                
			                Vote result = Vote.parse(response);
			                if (result != null) {
			                    Toast.makeText(VoteAPIActivity.this, 
			                            "Success: " + result.user_count, 
			                            Toast.LENGTH_LONG).show();
			                } else {
			                    Toast.makeText(VoteAPIActivity.this, response, Toast.LENGTH_LONG).show();
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
	                	mVoteApi.queryVoteList("paper777", mListener);
	                    break;
	                    
	                case 1:
	                	mVoteApi.voteList("new", mListener);
	                    break;
	                    
	                case 2:
	                	int[] choose = new int[3];
	                	choose[0] = 1; choose[1] = 2; choose[2] = 3;
	                	//mVoteApi.vote(3428, choose, mListener);
	                	mVoteApi.vote(3428, choose, mListener);
	                    break;
	                    
	                default:
	                    break;
	                }
	            } else {
	                Toast.makeText(VoteAPIActivity.this, 
	                        R.string.bbsSDK_token_empty, 
	                        Toast.LENGTH_LONG).show();
	            }// else
	        }// if( view)
		}// func

}
