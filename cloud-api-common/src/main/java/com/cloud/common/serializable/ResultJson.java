package com.cloud.common.serializable;

import java.io.Serializable;

/**
 * @Description
 * @Project spring-cloud
 * @Package com.cloud.common.serializable
 * @Author xuzhenkui
 * @Date 2020/4/5 9:35
 */
public class ResultJson<T> implements Serializable {
    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ResultJson success(){
        return success("");
    }

    public static ResultJson success(Object o){
        return new ResultJson(ResultCode.SUCCESS, o);
    }

    public static ResultJson failure(ResultCode resultCode){
        return failure(resultCode, "");
    }

    public static ResultJson failure(ResultCode resultCode, Object o){
        return new ResultJson(resultCode,o);
    }

    public ResultJson() {
    }

    public ResultJson(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultJson(ResultCode resultCode){
        setResultCode(resultCode);
    }

    public ResultJson(ResultCode resultCode, T data){
        setResultCode(resultCode);
        this.data = data;
    }

    public void setResultCode(ResultCode resultCode){
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }


}
