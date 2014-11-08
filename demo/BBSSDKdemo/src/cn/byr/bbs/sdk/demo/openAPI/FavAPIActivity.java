package cn.byr.bbs.sdk.demo.openAPI;

import cn.byr.bbs.sdk.demo.AccessTokenKeeper;
import cn.byr.bbs.sdk.demo.R;
import cn.byrbbs.sdk.api.FavApi;
import cn.byrbbs.sdk.api.model.Favorite;
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

public class FavAPIActivity extends Activity implements OnItemClickListener {
	   private static final String TAG = FavAPIActivity.class.getName();
	    
		private ListView mFuncListView;
	    /** func list */
	    private String[] mFuncList;

	    private FavApi mFavApi;
	    
	    private Oauth2AccessToken mAccessToken;
	    
		@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.openapi_item);
	        
	        mAccessToken = AccessTokenKeeper.readAccessToken(this);
	        mFavApi = new FavApi(mAccessToken);
	        
	        // 获取功能列表
	        mFuncList = getResources().getStringArray(R.array.fav_func_list);
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
			                
			                Favorite fav = Favorite.parse(response);
			                if (fav != null) {
			                    Toast.makeText(FavAPIActivity.this, 
			                            "Success: " + fav.description, 
			                            Toast.LENGTH_LONG).show();
			                } else {
			                    Toast.makeText(FavAPIActivity.this, response, Toast.LENGTH_LONG).show();
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
	                	mFavApi.show(0, mListener);
	                    break;
	                    
	                case 1:
	                	mFavApi.add(0, "Zhejiang", 0, mListener);
	                	//mFavApi.add(0, "Home", 1, mListener);
	                    break;
	                    
	                case 2:
	                	mFavApi.delete(0, "Zhejiang", 0, mListener);
	                	//mFavApi.delete(0, "Home", 1, mListener);
	                    break;
	                    
	                default:
	                    break;
	                }
	            } else {
	                Toast.makeText(FavAPIActivity.this, 
	                        R.string.bbsSDK_token_empty, 
	                        Toast.LENGTH_LONG).show();
	            }// else
	        }// if( view)
		}// func

}
