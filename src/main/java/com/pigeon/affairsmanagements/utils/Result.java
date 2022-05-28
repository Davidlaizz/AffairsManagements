package com.pigeon.affairsmanagements.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable
{
    int code;
    String msg;
    T data;


    public Result(Status _status, T _data)
    {
        code = _status.getCode();
        msg = _status.getMsg();
        data = _data;
    }

    public Result(Status _status, String _msg, T _data)
    {
        code = _status.getCode();
        msg = _msg;
        data = _data;
    }

}
