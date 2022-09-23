package com.xxc.homework.util.pool.exception;

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class PropertiesNotFoundException
 * @Description 配置文件找不到异常
 * @createTime 2019-04-23 14:20
 */
public class PropertiesNotFoundException extends RuntimeException {

    public PropertiesNotFoundException(String path) {
        super("找不到配置文件:"+path);
    }
}
