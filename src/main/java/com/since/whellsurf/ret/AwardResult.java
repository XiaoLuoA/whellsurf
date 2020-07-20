package com.since.whellsurf.ret;

/**
 * @author 王英豪111
 */
public interface AwardResult extends  Result{
    Code AWARD_CODE_NOT_FOUND = new Code("900_401","兑奖码不存在!");
    Code GET_AWARD_FAIL = new Code("900_402","抽奖失败!");
    Code GET_AWARD_REPEAT = new Code("900_405","只能抽奖一次!");
    Code REDEEM_FAIL = new Code("900_406","兑奖失败，不能重复兑奖!");
    Code AWARD_PROBABILITY_WRONG = new Code("700_401","奖品概率设置不为100%");
    Code AWARD_NUMBER_EXCEED = new Code ("700_402","商品数量设置超过10");
    Code AWARD_UNKNOW_EXCEPTION = new Code("700_403","抽奖时遇到未知异常");

}
