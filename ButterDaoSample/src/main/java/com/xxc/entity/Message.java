package com.xxc.entity;

import java.util.Date;

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class Message
 * @Description
 * @createTime 2019-06-03 20:59
 */
public class Message {

    private User user;

    private String content;

    private Date updateTime;

    private Integer id;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "user=" + user +
                ", content='" + content + '\'' +
                ", updateTime=" + updateTime +
                ", id=" + id +
                '}';
    }
}
