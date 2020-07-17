package com.since.whellsurf.ret;

public interface ActivityResult extends Result{
    Code ACTIVITY_IS_RUNNING = new Code("500_401","已经有活动正在开展");//status=1 活动正在执行
    Code ACTIVITY_IS_OUTDATED = new Code("500_402","活动已经失效");//status=2 活动失效
    Code ACTIVITY_IS_NOT_CREATED = new Code("500_403","活动未创建");//status=3 活动没有创建


}
