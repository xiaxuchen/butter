package com.xxc.homework.util.db.exception;

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class PoolNotSetException
 * @Description
 * @createTime 2019-04-29 10:33
 */
public class PoolNotSetException extends RuntimeException {

    public PoolNotSetException() {
    }

    public PoolNotSetException(String message) {
        super(message);
    }
}
