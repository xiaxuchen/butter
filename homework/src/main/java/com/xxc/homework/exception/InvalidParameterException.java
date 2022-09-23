package com.xxc.homework.exception;

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class InvalidParameterException
 * @Description
 * @createTime 2019-04-29 10:58
 */
public class InvalidParameterException extends RuntimeException {

    public InvalidParameterException() {
    }

    public InvalidParameterException(String message) {
        super(message);
    }
}
