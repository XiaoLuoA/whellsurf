package com.since.whellsurf.common;

/**
 * @author luoxinyuan
 */
public interface Status {

    Integer ACCOUNT_EXIST = 1;
    Integer ACTIVITY_VALID = 1;
    Integer ACTIVITY_INVALID = 2;

    Integer AWARD_CODE_LENGTH = 10;
    Integer Award_Valid = 1;

    /**
     * @author jayzh
     */
    /*
    * REDEEM_STATUS_OK means already redeemed
    * REDEEM_STATUS_NO means Not yet awarded
    * */
    Integer REDEEM_STATUS_OK=2;
    Integer REDEEM_STATUS_NO=1;

    Integer ACTIVITY_YET_EXIT=1;
    Integer ACTIVITY_NOT_EXIT=2;
    Integer ACTIVITY_EXIT_INDEX=0;
}
