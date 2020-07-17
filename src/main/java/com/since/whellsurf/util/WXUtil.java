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
        genURL("wx55cd46481d4e28d6","http://wxtest.easy.echosite.cn/wx/redirect/wx55cd46481d4e28d6/greet");
    }

    /**
     * 生成微信登陆用的url
     * @param appId 公众号的appid3
     * @param uri 登陆后跳转的url
     * @return 能用于微信登陆的url
     */
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public static String genURL(String appId, String uri){
        String appidStr = "appid="+appId;
        String redirectUri = "redirect_uri="+URLEncoder.encode(uri, StandardCharsets.UTF_8);
        String ret =  "https://open.weixin.qq.com/connect/oauth2/authorize?"+appidStr+"&"+redirectUri
                +"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        System.out.println(ret);
        return ret;
    }
}
