package com.since.whellsurf.common;

/**
 * @author luoxinyuan
 */
public interface Config {
    String HOST = "http://since.easy.echosite.cn";
    String SHOP_INDEX = "/wx/shopGreet";
    String ACTIVITY_INDEX = "/wx/accountGreet";


    String LOTTERY_HOST = "http://since.easy.echosite.cn/accountGreet";
    String appId = "wx55cd46481d4e28d6";


    String SHOP_NO_PERMISSION_REDIRECT = HOST+Config.SHOP_INDEX;

    String USER_NO_PERMISSION_REDIRECT = "";

    /**
     * 必须正常状态商户才能访问的URL
     */
    String[] shopURL = {
        "/activity/addActivity",
            "/to/addAward"
    };
}
