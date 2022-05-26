package com.pigeon.affairsmanagements.utils;

public enum Status {
    SUCCESS(200, "success"),
    ERROR(500, "error"),
    UNKNOWN(400, "unknown"),

    //用户错误
    USER_NOT_LOG_IN(230,"用户未登录"),
    USER_LOGIN_ERROR(231, "用户名或密码错误"),
    USER_NOT_EXIST(232, "用户不存在"),
    USER_HAS_EXISTED(233,"用户已存在");

    private final Integer code;
    private final String msg;

    Status(Integer _code, String _msg)
    {
        this.code = _code;
        this.msg = _msg;
    }

    public Integer getCode()
    {
        return code;
    }

    public String getMsg()
    {
        return msg;
    }


}
