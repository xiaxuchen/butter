package com.xxc.dao;

import com.xxc.better.dao.handler.param.GenerateKey;
import com.xxc.dao.mode.Insert;
import com.xxc.dao.mode.Select;
import com.xxc.dao.param.Param;
import com.xxc.dao.result.Column;
import com.xxc.dao.result.ID;
import com.xxc.dao.result.Result;
import com.xxc.entity.Message;

import java.util.List;

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class MessageDao
 * @Description
 * @createTime 2019-06-03 20:58
 */
public interface MessageDao {

    /**
     * 插入
     */
    @Insert("insert into msg(username,content) values(#{msg.user.username},#{msg.content})")
    int insertMsg(@Param("msg") Message msg);

    /**
     * 插入之后获取自增主键需要在参数中添加一个GenerateKey对象
     */
    @Insert("insert into msg(username,content) values(#{msg.user.username},#{msg.content})")
    int insertMsgWithGK(@Param("msg") Message msg, GenerateKey generateKey);

    //查询，@Result为结果集的映射，@ID表示主键字段，
    // @Column表示普通字段
    @Result(id = @ID(property = "id"),
        columns = {@Column(property = "content"),
                @Column(property = "updateTime",//当属性名称和列名不一样时都需要写明
                        column = "update_time")}
    )
    @Select("select update_time,content,id from msg where id=#{id}")
    Message findById(@Param("id")Integer id);

    @Result(id = @ID(property = "id"),
            columns = {@Column(property = "updateTime",//当属性名称和列名不一样时都需要写明
                            column = "update_time")},
            autowire = true,//使用autowire自动装配property和column相同的字段
            one = {@Result(id = @ID(property = "username"),autowire = true,
                    property = "user",
                    javaType = "com.xxc.entity.User")}
    )
    @Select("select u.*,update_time,content,id from msg,user u where id=#{id}")
    Message autoFindById(@Param("id")Integer id);

    //当结果是List时需要指定返回类型的javaType，其属于@Result注解
    //且当是一对一映射或者一对多映射时，需要配置one和many，其都是@Result，
    //但是需要写明javaType
    @Result(id = @ID(property = "id"),
            columns = {@Column(property = "content"),
                    @Column(property = "updateTime",
                            column = "update_time")},
            javaType = "com.xxc.entity.Message"
    )
    @Select("select update_time,content,id from msg")
    List<Message> findList();


    //一对一连表查询
    @Result(id = @ID(property = "id"),
            columns = {@Column(property = "content"),
                    @Column(property = "updateTime",
                            column = "update_time")},
            //这里表示的是msg的user的映射，当有多个一对一关系时可以配置多个，one和many都是数组
            one = {@Result(id = @ID(property = "username"),//user的主键字段
                columns = {@Column(property = "pwd"),//user的三个普通字段，由于数据库和字段名一致，只需要写property属性
                @Column(property = "sex"),
                        @Column(property = "phone")},
                    javaType = "com.xxc.entity.User",//表示映射的类型为user
                    property = "user"//表示映射为外层Result的user属性
            )}
    )
    @Select("select user.username,pwd,sex,phone,update_time,content,id from msg,user where id=${id} AND msg.username=user.username")
    Message findWithUserById(@Param("id")Integer id);
}
