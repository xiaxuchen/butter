package com.xxc;

import com.xxc.better.dao.handler.param.GenerateKey;
import com.xxc.better.dao.session.factory.SqlSessionFactory;
import com.xxc.better.db.DataBaseHelper;
import com.xxc.better.sql.pool.Pool;
import com.xxc.dao.MessageDao;
import com.xxc.entity.Message;
import com.xxc.entity.User;

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class Main
 * @Description
 * @createTime 2019-06-03 21:04
 */
public class Main {

    public static void main(String[] args)
    {
        Pool.Companion.init("F:\\javaweb\\project\\Homework\\src\\main\\webapp\\jdbc.properties");
        DataBaseHelper.init(Pool.Companion.getInstance());
        Message message = new Message();
        User user = new User();
        message.setUser(user);
        user.setUsername("xxc");
        message.setContent("hello world");
        final MessageDao messageDao = SqlSessionFactory.getSqlSession()
                .open(MessageDao.class);
        //###########直接插入######################
        System.out.println(messageDao.insertMsg(message));
        //############获取自增主键的插入#############
        GenerateKey gk = new GenerateKey();
        System.out.println(messageDao.insertMsgWithGK(message, gk));
        System.out.println(gk.getKey());
        //##################查询##################
        System.out.println(messageDao.findById(9));
        //##################查询多条记录##################
        System.out.println(messageDao.findList());
        //##################一对一查询
        System.out.println(messageDao.findWithUserById(9));
        //##################自动装配
        System.out.println(messageDao.autoFindById(32));
    }
}
