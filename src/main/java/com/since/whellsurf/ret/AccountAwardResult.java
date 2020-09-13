package com.since.whellsurf.ret;

/**
 * @author jayzh
 */
public interface AccountAwardResult extends Result {
    Code NOT_FIND_ACCOUNT_AWARD =new Code("800_002","未找到该中奖信息");
    Code NOT_FIND_NOT_REDEEMS = new Code("900_11","未找到与此活动相关的未兑奖信息");
    Code NOT_FIND_REDEEMS = new Code("900_12","未找到与此活动相关的已兑奖信息");
    Code NOT_FIND_ALLREDEEMS = new Code("900_13","未找到与此活动相关的全部兑奖信息");

}
