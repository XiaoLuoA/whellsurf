package com.since.whellsurf.ret;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author luoxinyuan
 * 返回值的Code参数
 */
@Getter
@AllArgsConstructor
public class Code {
    String code;
    String errMsg;
    public Code(String code){
        this.code = code;
    }
}
