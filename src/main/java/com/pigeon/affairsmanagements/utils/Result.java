package com.pigeon.affairsmanagements.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable
{
    int code;
    String message;
    T data;


    public Result(Status _status, T _data)
    {
        code = _status.getCode();
        message = _status.getMsg();
        data = data;
    }
}
