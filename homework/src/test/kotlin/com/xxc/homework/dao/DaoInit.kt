package com.xxc.homework.dao

import com.xxc.better.sql.pool.Pool

/**
 * @class DaoInit
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-12 23:02
 */
class DaoInit {

    companion object {

        fun init(){
            Pool.init("F:\\javaweb\\project\\Homework\\homework\\src\\main\\webapp\\jdbc.properties")
        }
    }

}