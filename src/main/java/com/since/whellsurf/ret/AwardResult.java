package com.since.whellsurf.ret;

public interface AwardResult extends  Result{
    Code AWARD_PROBABILITY_WRONG = new Code("700_401","奖品概率设置不为100%");
    Code AWARD_NUMBER_EXCEED = new Code ("700_402","商品数量设置超过10");
}
