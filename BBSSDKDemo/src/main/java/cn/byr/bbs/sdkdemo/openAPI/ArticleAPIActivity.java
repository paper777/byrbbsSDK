package cn.byr.bbs.sdkdemo.openAPI;

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

import cn.byr.bbs.sdk.api.ArticleApi;
import cn.byr.bbs.sdk.auth.Oauth2AccessToken;
import cn.byr.bbs.sdk.exception.BBSException;
import cn.byr.bbs.sdk.net.RequestListener;
import cn.byr.bbs.sdk.utils.LogUtil;
import cn.byr.bbs.sdkdemo.AccessTokenKeeper;
import cn.byr.bbs.sdkdemo.R;

public class ArticleAPIActivity extends Activity implements OnItemClickListener {

    private static final String TAG = ArticleAPIActivity.class.getName();
    private RequestListener mListener = new RequestListener() {
        @Override
        public void onComplete(String response) {
            if (!TextUtils.isEmpty(response)) {
                LogUtil.i(TAG, response);
                Toast.makeText(ArticleAPIActivity.this, response, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onException(BBSException e) {
            // TODO Auto-generated method stub
            LogUtil.e(TAG, e.getMessage());
//		            Toast.makeText(SectionActivity.this, info.toString(), Toast.LENGTH_LONG).show();
        }
    };
    private ListView mFuncListView;
    /**
     * func list
     */
    private String[] mFuncList;
    private ArticleApi mArticleApi;
    private Oauth2AccessToken mAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.openapi_item);

        mAccessToken = AccessTokenKeeper.readAccessToken(this);
        mArticleApi = new ArticleApi(mAccessToken);

        // ��ȡ�����б�
        mFuncList = getResources().getStringArray(R.array.article_func_list);
        // ��ʼ�������б� ListView
        mFuncListView = (ListView) findViewById(R.id.api_func_list);
        mFuncListView.setAdapter(new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, mFuncList));
        mFuncListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (view instanceof TextView) {
            if (mAccessToken != null && mAccessToken.isSessionValid()) {
                switch (position) {
                    case 0:
                        mArticleApi.showArticle("Zhejiang", 152900, mListener);
                        break;

                    case 1:
                        mArticleApi.showThread("Zhejiang", 152900, mListener);
                        break;

                    case 2:
                        mArticleApi.post("Zhejiang", mListener, "Android SDK tst", "it seems good!");
                        break;

                    case 3:
                        mArticleApi.forward("Zhejiang", 152900, "paper777", mListener);
                        break;

                    case 4:
                        mArticleApi.reproduce("Zhejiang", 152900, "Zhejiang", mListener);
                        break;

                    case 5:
                        mArticleApi.update("Zhejiang", 153368, "change title", "change content", mListener);
                        break;

                    case 6:
                        mArticleApi.reply("Zhejiang", mListener, "AAA", "reply", 128879);
                        break;

                    case 7:
                        mArticleApi.delete("Zhejiang", 128879, mListener);
                        break;

                    default:
                        break;
                }
            } else {
                Toast.makeText(ArticleAPIActivity.this,
                        R.string.bbsSDK_token_empty,
                        Toast.LENGTH_LONG).show();
            }// else
        }// if( view)
    }// func

}
