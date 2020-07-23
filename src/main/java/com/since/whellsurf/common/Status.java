package com.since.whellsurf.common;

/**
 * @author luoxinyuan
 */
public interface Status {

    Integer ACCOUNT_EXIST = 1;
    Integer ACCOUNT_NORMAL = 1;

    Integer ACTIVITY_VALID = 1;
    Integer ACTIVITY_RUNNING = 1;
    Integer ACTIVITY_END=2;


    Integer AWARD_CODE_LENGTH = 4;
    Integer AWARD_MAX_NUMBER=10;
    Integer AWARD_PROBABILITY =100;
    Integer AWARD_NORMAL =1;

    Integer SHOP_NOT_ACTIVATE = 2;
    Integer SHOP_ACTIVATE = 1;

    Integer REDEEM_STATUS_ALREADY =2;
    Integer REDEEM_STATUS_NOT=1;

    Integer ACCOUNT_AWARD_NOT_REDEEM = 1;
}
