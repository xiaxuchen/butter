package com.xxc.homework.util.pool.exception;

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class PropertiesErrorException
 * @Description 配置文件出错异常
 * @createTime 2019-04-23 14:24
 */
public class PropertiesErrorException extends RuntimeException{

    public PropertiesErrorException(String message)
    {
        super(message);
    }
}
