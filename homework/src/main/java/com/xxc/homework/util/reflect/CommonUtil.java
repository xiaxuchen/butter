package com.xxc.homework.util.reflect;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class CommonUtil
 * @Description
 * @createTime 2019-04-23 16:29
 */
public class CommonUtil {

    /**
     * 把结果集转化为javaBean
     * @param rs 结果集
     * @param type javaBean类型
     * @param <T> javaBean类型
     * @return javaBeam
     */
    public static <T> T toBean(ResultSet rs,Class<T> type)
    {
        try {
            ResultSetMetaData data = rs.getMetaData();
            if(!rs.next())
                return null;
            final T bean = type.newInstance();
            //逐个赋值
            for(int i = 0;i<data.getColumnCount();i++)
            {
                String fieldName = data.getColumnName(i+1);
                Field declaredField = type.getDeclaredField(fieldName);
                declaredField.setAccessible(true);
                PropertyDescriptor pd = new PropertyDescriptor(declaredField.getName(), type);
                pd.getWriteMethod().invoke(bean,rs.getObject(i+1));
            }
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将结果集转换为javaBean的集合
     * @param rs 结果集
     * @param type javaBean类型
     * @param <T> javaBean类型
     * @return javaBean集合
     */
    public static <T> List<T> toBeanList(ResultSet rs,Class<T> type)
    {
        List<T> beans = new ArrayList<>();
        T bean;
        while((bean = toBean(rs,type)) != null)
        {
            beans.add(bean);
        }
        return beans;
    }
}
