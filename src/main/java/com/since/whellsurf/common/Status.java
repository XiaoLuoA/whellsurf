package com.since.whellsurf.common;

/**
 * @author luoxinyuan
 */
public interface Status {

    Integer ACCOUNT_EXIST = 1;
    Integer ACTIVITY_VALID = 1;
    Integer ACTIVITY_INVALID = 2;
    Integer ACTIVITY_RUNNING = 1;
    Integer AWARD_CODE_LENGTH = 10;
    Integer AWARD_VALID = 1;

    Integer SHOP_NOT_ACTIVATE = 2;


    Integer REDEEM_STATUS_OK=2;
    Integer REDEEM_STATUS_NO=1;

    Integer ACTIVITY_YET_EXIT=1;
    Integer ACTIVITY_NOT_EXIT=2;
    Integer ACTIVITY_EXIT_INDEX=0;
    Integer ACTIVITY_END=2;
    Integer ACTIVITY_ZERO=0;
    Integer PROBABILITY=100;
    Integer AWARD_MAX_NUMBER=10;
    Integer AWARD_NORMAL =1;
    Integer ACTIVITGY_INSERT=1;
}
