package com.since.whellsurf.util;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author luoxinyuan
 * 微信相关工具类
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class WXUtil {
    public class Scope{
        public static final String USER_INFO = "snsapi_userinfo";
        public static final String BASE = "snsapi_base";
    }
    /**
     * {"subscribe":null,
     * "openId":"oIaLN563Ujp5EPzh2agMoIOHiFpY",
     * "nickname":"韩信","sexDesc":"男",
     * "sex":1,"language":"zh_CN",
     * "city":"焦作",
     * "province":"河南",
     * "country":"中国",
     * "headImgUrl":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83epxoWwmuAmwsUb1mSvlRJX4icCBSQk3IMQxo28d0Beler3Hpg41biaJ7icwI8nfrS0exulYUBgNvh9gA/132",
     * "subscribeTime":null,
     * "unionId":"oilT_snzCqs_T3ldEnJEMVJV20fM",
     * "remark":null,"groupId":null,
     * "tagIds":null,"privileges":[],
     * "subscribeScene":null,"qrScene":null,"qrSceneStr":null}
     * @param args
     */
    public static void main(String[] args) {
        String appId = "wx55cd46481d4e28d6";
        String uri = "http://wxtest.easy.echosite.cn/wx/wx55cd46481d4e28d6/shopGreet";
        genGetUserURL(appId,uri);
        genGetBaseURL(appId,uri);
        genURL(appId,uri,Scope.USER_INFO);
        genURL(appId,uri,Scope.BASE);
    }

    /**
     * 生成微信授权用的url
     * @author luoxinyuan
     * @param appId 公众号的appId
     * @param uri 登陆后跳转的url
     * @param scope 微信授权方式
     * @return 用于微信用户授权的的url
     */
    public static String genURL(String appId, String uri,String scope){
        String redirectUri = "redirect_uri="+URLEncoder.encode(uri, StandardCharsets.UTF_8);
        String ret =  "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&"+redirectUri
                +"&response_type=code&scope="+scope+"&state=STATE#wechat_redirect";
        System.out.println("gen URL\n"+ret);
        return ret;
    }


    /**
     * 生成微信静默授权用的url
     * @author luoxinyuan
     * @param appId 公众号的appId
     * @param uri 登陆后跳转的url
     * @return 用于微信用户静默授权的的url
     */
    public static String genGetBaseURL(String appId, String uri){
        return genURL(appId,uri,Scope.BASE);
    }


    /**
     * 生成微信授权获取用户信息用的url
     * @author luoxinyuan
     * @param appId 众号的appId
     * @param uri 登陆后跳转的url
     * @return 用于微信用户主动授权的的url
     */
    public static String genGetUserURL(String appId, String uri){
        return genURL(appId,uri,Scope.USER_INFO);
    }
}