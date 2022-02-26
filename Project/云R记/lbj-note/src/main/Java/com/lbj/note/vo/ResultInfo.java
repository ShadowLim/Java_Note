package com.lbj.note.vo;

/**
 *  封装返回的结果
 *      状态码
 *          成功 = 1 失败 = 0
 *      提示信息
 *          返回对象（任意类型 比如：字符串 JavaBean 集合 Map）
 *
 */
public class ResultInfo<T> {
    private Integer code;   // 状态码
    private String msg;     // 提示信息
    private T result;      // 返回的对象

    // getter()方法
    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getResult() {
        return result;
    }

    // setter()方法
    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
