# ReadMe

# 概述
北邮人论坛API Android 平台SDK 为第三方开发者应用开发提供了简单易用的解决方案，第三方开发者无需了解OAuth2.0[1]的复杂机制即可完成论坛授权，并提供开放API接口的服务：）
------

# 必要术语说明
| 参数                   | 含义    | 
| --------               | :-----  | 
| AppKey(client_id)      | 分配给每个第三方应用的 app key。用于鉴权身份，显示来源等功能。|
| RedirectURI            | 第三方应用授权回调页面。这里建议使用默认回调页`http://bbs.byr.cn/Oauth2/callback` |
| Scope                  | 授权项目，用户在新OAuth2.0[1]授权页中有权利选择赋予应用的功能。| 
| AccessToken            | 表示用户身份的 token，用于 API 的调用。| 
|

------

# Usage
## 引用库
### 1、直接引用 jar 文件
		* 在 release 目录下下载 .jar 文件
		* 将 jar 文件放入工程 libs 目录中
		* 将其引入工程
	2、克隆整个仓库
		* 克隆这个仓库
		* 将工程导入并将其引入您的工程
		* 要将lib工程中assets目录中的内容复制到您的工程assets的目录中

## 使用说明
### 1. 填写 APP_KEY 等参数
```java
public interface Constants {
    /** 填写自己的 APP_KEY */
    public static final String APP_KEY      = "XXXXXXX";

    /** 
     * 应用的回调页。
     * 建议使用默认回调页：http://bbs.byr.cn/Oauth2/callback
     */
    public static final String REDIRECT_URL = "ttp://bbs.byr.cn/Oauth2/callback";

    /**
     * 应用对应的权限，设置成空即获取用户的详细信息。
     * 多个项目请用逗号隔开（不要有空格！！！）
     */
    public static final String SCOPE = ""; // "article,mail,favor,refer,blacklis";

}

```

### 2. 创建Auth对象
```java
mBBSAuth = new BBSAuth(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);

### 3. 实现BBSAuthListener接口

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
### 4. 授权
```java
 mBBSAuth.authorize(new AuthListener());
```

[1]:http://http://oauth.net/2/
