package com.since.whellsurf.ret;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luoxinyuan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ret {

    public  static  Ret success(Object data){
        return new Ret(Result.SUCCESS,data);
    }

    public static Ret error(Code code){
        return new Ret(code);
    }

    public Ret(Code error){
        this.code = error.getCode();
        this.errMsg = error.getErrMsg();
    }
    
    public Ret(Code error, Object data){
        this.code = error.getCode();
        this.data = data;
        this.errMsg = error.getErrMsg();
    }
    public String code;
    public Object data;
    public String errMsg;

}
