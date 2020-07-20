package com.since.whellsurf.ret;

/**
 * @author jayzh
 */
public interface ShopResult extends Result{
    Code NOT_FIND_SHOP_ACTIVITY=new Code("800_003","未找到商家创建的活动");
    Code SHOPE_IS_NOT_EXIST = new Code("800_001","没有此商家");
}
