package com.since.whellsurf.ret;

/**
 * @author jayzh
 */
public interface ShopResult extends Result{
    Code NOT_FIND_SHOP_ACTIVITY=new Code("800_003","未找到商家创建的活动");
    Code SHOP_NOT_ACTIVATE = new Code("800_001","商家未激活");
}
