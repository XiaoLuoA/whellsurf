package com.since.whellsurf.ret;



public interface AccountResult extends Result {

    Code ACCOUNT_NOT_RIGHT = new Code("600_401","用户没有权限");
    Code ACCOUNT_NOT_LOGIN = new Code("900_403","用户还未登录，请先登录!");
}
