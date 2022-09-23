package com.xxc.homework.dao;

import com.xxc.dao.mode.Delete;
import com.xxc.dao.mode.Insert;
import com.xxc.dao.mode.Select;
import com.xxc.dao.mode.Update;
import com.xxc.dao.param.Param;
import com.xxc.dao.result.Column;
import com.xxc.dao.result.ID;
import com.xxc.dao.result.Result;
import com.xxc.homework.entity.Message;

import java.util.List;

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class MessageDao
 * @Description
 * @createTime 2019-05-12 21:48
 */
public interface MessageDao {

    /**
     * 通过所属用户名获取留言信息
     * @param username 所属用户名
     * @param len 取出的记录条数
     * @param position 从何处开始取
     * @return 留言信息
     */
    @Select("SELECT * FROM msg WHERE username=#{username} ORDER BY update_time DESC LIMIT #{position},#{len}")
    @Result(id = @ID(property = "id"),
            columns = {@Column(property = "updateTime",//当属性名称和列名不一样时都需要写明
                    column = "update_time")},
            autowire = true,//使用autowire自动装配property和column相同的字段
            one = {@Result(id = @ID(property = "username"),autowire = true,
                    property = "user",
                    javaType = "com.xxc.homework.entity.User")},
            javaType = "com.xxc.homework.entity.Message"
    )
    List<Message> selectByUsername(@Param("username") String username, @Param("position") int position,@Param("len") int len);

    /**
     * 获取消息
     * @param position 开始位置
     * @param len 长度
     * @return 消息
     */
    @Select("SELECT * FROM msg ORDER BY update_time DESC LIMIT #{position},#{len}")
    @Result(id = @ID(property = "id"),
            columns = {@Column(property = "updateTime",//当属性名称和列名不一样时都需要写明
                    column = "update_time")},
            autowire = true,//使用autowire自动装配property和column相同的字段
            one = {@Result(id = @ID(property = "username"),autowire = true,
                    property = "user",
                    javaType = "com.xxc.homework.entity.User")},
        javaType = "com.xxc.homework.entity.Message")
    List<Message> selectMessages(@Param("position") int position,@Param("len") int len);

    /**
     * 通过id删除消息
     * @param id 消息id
     * @return 影响条数
     */
    @Delete("DELETE FROM msg WHERE id=#{id}")
    int deleteMessage(@Param("id") int id);


    /**
     * 修改消息
     * @param msg 消息
     * @return 影响条数
     */
    @Update("UPDATE msg SET content=#{msg.content} WHERE id=#{msg.id}")
    int alterMessage(@Param("msg") Message msg);

    /**
     * 插入一条留言记录
     * @param msg 留言信息
     * @return 更新值
     */
    @Insert("INSERT INTO msg(content,username) VALUES(#{msg.content},#{msg.user.username})")
    int insert(@Param("msg") Message msg);

    @Select("select count(*) from msg")
    int getSize();
}
