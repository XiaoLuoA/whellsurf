package com.since.whellsurf.ret;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luoxinyuan
 */
@AllArgsConstructor
@NoArgsConstructor
public class Ret {


    public  static  Ret success(Object data){
        return new Ret(Result.SUCCESS,data).setOk(true);
    }

    public static Ret success(){
        return new Ret(Result.SUCCESS).setOk(true);
    }

    public static Ret error(){
        return new Ret().setOk(false);
    }

    public static Ret error(Code code){
        return new Ret(code).setOk(false);
    }

    public static Ret noPermission(Object data){
        return Ret.error(Result.NO_PERMISSION).setData(data);
    }

    private Ret(Code error){
        this.code = error.getCode();
        this.errMsg = error.getErrMsg();
    }
    
    private Ret(Code error, Object data){
        this.code = error.getCode();
        this.data = data;
        this.errMsg = error.getErrMsg();
    }
    private String code;
    private Object data;
    private String errMsg;

    @JsonIgnore
    private boolean isOk;

    public boolean isOk() {
        return isOk;
    }

    public Ret setOk(boolean ok) {
        isOk = ok;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Ret setCode(String code) {
        this.code = code;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Ret setData(Object data) {
        this.data = data;
        return this;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public Ret setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
