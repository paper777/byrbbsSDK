# ReadMe

------
基于北邮人论坛新版authorization、API 编写的Android平台SDK。为第三方开发者应用开发提供了简单易用的解决方案，第三方开发者无需了解[OAuth2.0][1]的复杂机制即可完成论坛授权，并提供开放API接口的服务

------

# Features
- [北邮人论坛][2]的开放授权，基于[OAuth2.0][1]中client-side模式
- 封装了北邮人论坛API2.0的各个接口

# About Demo

	为OAuth与API调用提供了简单的示例，** 请务必下载后不要更改签名，否则会导致授权失败！**

# Usage

### 1、直接引用 jar 文件
- 在 release 目录下下载 .jar 文件
- 将 jar 文件放入工程 libs 目录中并将其引入工程

###	2、克隆整个仓库
- 克隆这个仓库
- 将工程作为librarya引入您的工程
- 要将lib工程中assets目录中的内容复制到您的工程assets的目录中，否则无法使用

# Authorization setup

### 0、必要术语说明
| 参数                   | 含义    | 
| --------               | :-----  | 
| AppKey(client_id)      | 分配给每个第三方应用的 app key。用于鉴权身份，显示来源等功能。|
| RedirectURI            | 第三方应用授权回调页面。这里建议使用默认回调页`http://bbs.byr.cn/Oauth2/callback` |
| Scope                  | 授权项目，用户在新OAuth2.0[1]授权页中有权利选择赋予应用的功能。| 
| AccessToken            | 表示用户身份的 token，用于 API 的调用。| 
|

### 1、填写 APP_KEY 等参数

```java
public interface Constants {
    /** 填写自己的 APP_KEY */
    public static final String APP_KEY      = "XXXXXXX";

    /** 
     * 应用的回调页。
     * 建议使用默认回调页：http://bbs.byr.cn/Oauth2/callback
     */
    public static final String REDIRECT_URL = "http://bbs.byr.cn/Oauth2/callback";

    /**
     * 应用对应的权限，设置成空即获取用户的详细信息。
     * 多个项目请用逗号隔开（不要有空格！！！）
     */
    public static final String SCOPE = ""; // "article,mail,favor,refer,blacklis";

}

```

### 2、创建Auth对象

```java
mBBSAuth = new BBSAuth(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
```
### 3、实现BBSAuthListener接口

```java
class AuthListener implements BBSAuthListener {
        
        @Override
        public void onComplete(Bundle values) {
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            if (mAccessToken.isSessionValid()) {
            	// TODO maybe to store the token informations
            } else {
             	// TODO actually won't happen
            }
        }

        @Override
        public void onCancel() {
           // TODO user canceled the authorization
        }

        @Override
        public void onException(BBSException e) {
            // TODO exception handle
        }
    }
```
### 4、授权

```java
 mBBSAuth.authorize(new AuthListener());
```

# API Quick setup

确保在获取了TOKEN之后调用API，否则无法使用

### 1、实例化API类

```java
	/** mAccessToken 为Oauth2AccessToken类 
		不能为空*/
    mUserApi = new UserApi(mAccessToken);
```

###2、实现RequestListener接口

```java
	private RequestListener mListener = new RequestListener() {
        @Override
        public void onComplete(String response) {
            if (!TextUtils.isEmpty(response)) {
                /** 解析返回数据 */
                User user = User.parse(response);
            }
        }
		@Override
		public void onException(BBSException e) {
			/** 异常处理 */
            LogUtil.e(TAG, e.getMessage());
		}
    };
```

### 3、调用API函数

```java
	/** 获取授权用户信息 */
	mUserApi.show(mListener);
```
------
如有错误、bug等请移步[这里][4]，欢迎pull request!

------
Special Thanks to [@dss886][3] for contributions(API model) to this projects

-----
[1]:http://http://oauth.net/2/
[2]:http://bbs.byr.cn
[3]:https://github.com/dss886
[4]:https://github.com/paper777/byrbbsSDK/issues
