package com.xxc.homework.util.pool.exception;

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class ConnectTimeOutException
 * @Description 连接超时异常
 * @createTime 2019-04-23 15:10
 */
public class ConnectTimeOutException extends Exception {

    public ConnectTimeOutException(String message) {
        super(message);
    }
}
