package com.since.whellsurf.common;

/**
 * @author luoxinyuan
 */
public interface Config {
    String HOST = "http://wxtest.easy.echosite.cn";
    String SHOP_INDEX = "/wx";
    String ACTIVITY_INDEX = "/wx/account/drawPrize/";

    String LOTTERY_HOST = "http://wxtest.easy.echosite.cn/accountGreet";
    String appId = "wx55cd46481d4e28d6";


    String SHOP_NO_PERMISSION_REDIRECT = "";

    String USER_NO_PERMISSION_REDIRECT = "";

    /**
     * 必须正常状态商户才能访问的URL
     */
    String[] shopURL = {
        "/activity/addActivity",
    };
}
