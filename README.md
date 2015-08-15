# ReadMe

------
基于北邮人论坛新版authorization、API 编写的Android平台SDK。为第三方开发者应用开发提供了简单易用的解决方案，第三方开发者无需了解[OAuth2.0][1]的复杂机制即可完成论坛授权，并提供开放API接口的服务

------

# 版本更新记录

- 1.1 修复了threads 报告版面不存在的错误 2015/5
- 1.2 API page number fix; 增加了收录文章的API接口 2015/5/20
- 1.3 remove res files(issues #1) 2015/8

---

# Features
- [北邮人论坛][2]的开放授权，基于[OAuth2.0][1]中client-side模式
- 封装了北邮人论坛API的各个元数据
- 封装了北邮人论坛API2.0的各个接口，提供了异步请求工具。

# About Demo

为OAuth与API调用提供了简单的示例，提供了
- APK 安装包
- 源代码工程文件，<html><b>请务必使用提供的[debug keystore][5]签名，否则会导致授权失败！</html>

# About API

- usage 部分示例了API 调用的简单步骤。[**详细API接口函数请查看WIKI**][7]
- API 元数据（write by dss886) 在cn.byr.bbs.sdk.api.model 或者repository中APImodel下提供

# Usage
### 0、Recomanded
```
repositories {
    jcenter()
}

dependencies {
    compile 'com.github.paper777:BBSSDK:1.3@aar'
    // or
    // compile(group: 'com.github.paper777', name: 'BBSSDK', version: '1.3', ext: 'aar')

}
```
** 建议使用0, 并不建议下面两种**

### 1、直接引用 jar 文件
- 在 [release][6] 目录下下载 .jar 文件
- 将 jar 文件放入工程 libs 目录中并将其引入工程

###	2、克隆整个仓库
- 克隆这个仓库
- 将工程作为librarya引入您的工程
- 将BBSSDKDEMO/build.gradle 中的签名文件替换成自己的 storeFile file('path/to/bbsSDKkeystore_debug')

=======



# Authorization setup

### 0、必要术语说明
| 参数                   | 含义    |
| --------               | :-----  |
| AppKey(client_id)      | 分配给每个第三方应用的 app key。用于鉴权身份，显示来源等功能。|
| RedirectURI            | 第三方应用授权回调页面。这里建议使用默认回调页`http://bbs.byr.cn/Oauth2/callback` |
| Scope                  | 授权项目，用户在新[OAuth2.0][1]授权页中有权利选择赋予应用的功能。|
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
[**详细API接口请查看WIKI**][7]
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


-----



## 念奴娇·赤壁怀古

大江东去，浪淘尽，千古风流人物。

故垒西边，人道是，三国周郎赤壁。

乱石穿空，惊涛拍岸，卷起千堆雪。

江山如画，一时多少豪杰。

遥想公瑾当年，小乔初嫁了，雄姿英发。

羽扇纶巾，谈笑间，樯橹灰飞烟灭。

故国神游，多情应笑我，早生华发。

人生如梦，一尊还酹江月。

-------
[1]:http://developers.byr.cn/wiki
[2]:http://bbs.byr.cn
[3]:https://github.com/dss886
[4]:https://github.com/paper777/byrbbsSDK/issues
[5]:https://github.com/paper777/byrbbsSDK/blob/master/bbsSDKkeystore_debug
[6]:https://github.com/paper777/byrbbsSDK/tree/master/release
[7]:https://github.com/paper777/byrbbsSDK/wiki/API-home-page
