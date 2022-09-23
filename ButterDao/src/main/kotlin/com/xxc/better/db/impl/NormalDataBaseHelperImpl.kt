package com.xxc.better.db.impl

import com.xxc.better.db.DataBaseHelper
import com.xxc.better.sql.bean.SQLBean
import com.xxc.util.logger.factory.LoggerFactory
import java.sql.*
import java.util.concurrent.TimeoutException

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class NormalDataBaseHelperImpl
 * @Description
 * @createTime 2019-04-29 10:35
 */
class NormalDataBaseHelperImpl : DataBaseHelper() {
    val logger = LoggerFactory.getLogger(NormalDataBaseHelperImpl::class.java)
    private fun createPreparedStatement(con: Connection,sql: String, vararg params: Any?,isGenerateKey: Boolean = false): PreparedStatement? {
        try {
            val ps = if(isGenerateKey)
                con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)
            else
                con.prepareStatement(sql)
            for (i in params.indices) {
                ps.setObject(i + 1, params[i])
            }
            return ps
        } catch (e: SQLException) {
            e.printStackTrace()
        } catch (e: TimeoutException) {
            e.printStackTrace()
        }
        return null
    }


    override fun update(sql: String, vararg params: Any?, c:Connection?): Int {
        logger.info("$sql ===> ${getParamString(*params)}")
        var closeable = false
        var conn = c
        if(conn == null)
        {
            closeable = true
            conn = connection
        }
        val ps = createPreparedStatement(conn,sql, *params)
        if (ps != null) {
            try {
                return ps.executeUpdate()
            } catch (e: SQLException) {
                e.printStackTrace()
            }finally {
                ps.close()
                if (closeable) {
                    try {
                        conn.close()
                    } catch (e: SQLException) {
                        e.printStackTrace()
                    }
                }
            }

        }
        return 0
    }

    override fun updateWithGenerateKey(sql: String, generateKey: Array<Int>, vararg params: Any?, c:Connection?): Int {
        logger.info("$sql ==> ${getParamString(*params)}")
        var closeable = false
        var conn = c
        if(conn == null)
        {
            closeable = true
            conn = connection
        }
        val ps = createPreparedStatement(conn,sql, *params,isGenerateKey = true)
        if (ps != null) {
            try {
                val result = ps.executeUpdate()
                val generatedKeys = ps.generatedKeys
                //目前只处理一条的情况
                if(generatedKeys.next())
                {
                    generateKey[0] = generatedKeys.getInt(1)
                }
                return result
            } catch (e: SQLException) {
                e.printStackTrace()
            }finally {
                try {
                    ps.close()
                    if (closeable) {
                            conn.close()
                    }
                } catch (e: SQLException) {
                    e.printStackTrace()
                }
            }

        }
        return 0
    }

    /**
     * 查询
     * @param sql SQL语句
     * //@param resultType 结果类型
     * @param params 参数
     * //@param <T> 结果类型
     * @return 查询结果
    </T> */
    override fun query(sql: String, vararg params: Any?, c:Connection?): List<HashMap<String, SQLBean>> {
        logger.info("$sql ===> ${getParamString(*params)}")
        var closeable = false
        var conn = c
        if(conn == null)
        {
            closeable = true
            conn = connection
        }
        val ps = createPreparedStatement(conn,sql, *params)
        var rs:ResultSet? = null
        if (ps != null) {
            try {
                rs =  ps.executeQuery()
                if(rs != null)
                {
                    val metaData = rs.metaData
                    val count = metaData.columnCount
                    val data = ArrayList<HashMap<String, SQLBean>>(count)
                    while(rs.next())
                    {
                        val map = HashMap<String, SQLBean>()
                        for(i in 1..count)
                        {
                            val columnName = metaData.getColumnName(i)
                            map[columnName] = SQLBean(rs.getObject(i),metaData.getColumnType(i),columnName)
                        }
                        data.add(map)
                    }
                    return data
                }
            } catch (e: SQLException) {
                e.printStackTrace()
            }finally {
                try {
                    ps.close()
                    rs?.close()
                    if (closeable) {
                        conn.close()
                    }
                } catch (e: SQLException) {
                    e.printStackTrace()
                }
            }
        }
        return emptyList()
    }

    private fun getParamString(vararg params:Any?):String
    {
        return params.map{it}.foldIndexed(StringBuilder()) {index, acc, item ->
            acc.append(item)
            if(index != params.size-1)
                acc.append(",")
            acc
        }.toString()
    }
}
