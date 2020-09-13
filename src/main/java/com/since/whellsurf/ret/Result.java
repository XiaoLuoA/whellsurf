package com.since.whellsurf.ret;

/**
 * @author luoxinyuan
 */
public interface Result {
    Code SUCCESS = new Code("0");
    Code NO_PERMISSION = new Code("500_001","您没有权限进行此操作，请先登录！");

}
