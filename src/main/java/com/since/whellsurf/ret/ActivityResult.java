package com.since.whellsurf.ret;
/**
 * @author drj
 */
public interface ActivityResult extends Result{
    Code ACTIVITY_IS_RUNNING = new Code("500_401","已经有活动正在开展");//status=1 活动正在执行
    Code ACTIVITY_IS_OUTDATED = new Code("500_402","活动已经失效");//status=2 活动失效
    Code ACTIVITYID_EXCEPTION=new Code("500_403","参数异常");
    Code ACTIVITY_INSERT_FAIL = new Code("500_404","插入失败");
    Code ACTIVITY_NOT_FIND = new Code("500_405","活动不存在");
}
