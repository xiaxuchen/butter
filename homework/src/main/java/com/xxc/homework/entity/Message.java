package com.xxc.homework.entity;

import com.xxc.better.dao.session.factory.SqlSessionFactory;
import com.xxc.homework.dao.MessageDao;

import java.util.Date;
import java.util.List;

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class Message
 * @Description
 * @createTime 2019-05-06 0:03
 */
public class Message {

    private static final MessageDao MESSAGE_DAO = SqlSessionFactory.getSqlSession().open(MessageDao.class);

    public static final int PAGE_SIZE = 10;

    private String content;//留言内容

    private Integer id;//留言id

    private Date updateTime;//更新时间

    private User user;//发言人

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 留言
     * @return 是否留言成功
     */
    public boolean leaveMessage(){
        return MESSAGE_DAO.insert(this) != 0;
    }

    /**
     * 删除留言
     * @return 是否成功删除
     */
    public boolean deleteMessage()
    {
        if (id == null) {
            return false;
        }
        return MESSAGE_DAO.deleteMessage(this.getId()) != 0;
    }

    /**
     * 更新信息
     * @return 是否更新成功
     */
    public boolean updateMessage(){
        return MESSAGE_DAO.alterMessage(this) != 0;
    }

    /**
     * 获取某一用户的信息
     * @param username 用户名
     * @param position 起始位置
     * @return 某一用户的PAGE_SIZE个留言
     */
    public static List<Message> getMineMessages(String username,int position)
    {
        if (username == null) {
            return null;
        }
        return MESSAGE_DAO.selectByUsername(username, position, PAGE_SIZE);
    }

    /**
     * 从position开始获取所有的信息
     * @param position 起始位置
     * @return PAGE_SIZE个留言
     */
    public static List<Message> getMessages(int position){
        return MESSAGE_DAO.selectMessages(position, PAGE_SIZE);
    }

    public static int getSize(){
        System.out.println(MESSAGE_DAO.getSize());
        return (MESSAGE_DAO.getSize()+1)/PAGE_SIZE;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", id=" + id +
                ", updateTime=" + updateTime +
                ", user=" + user +
                '}';
    }
}
