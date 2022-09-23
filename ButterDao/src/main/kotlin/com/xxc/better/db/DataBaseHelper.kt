package com.xxc.better.db


import com.xxc.better.db.exception.PoolNotSetException
import com.xxc.better.db.impl.NormalDataBaseHelperImpl
import com.xxc.better.sql.bean.SQLBean
import com.xxc.better.sql.pool.Pool
import com.xxc.util.logger.factory.LoggerFactory
import java.sql.Connection
import java.util.concurrent.TimeoutException

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class DataBaseHelper
 * @Description
 * @createTime 2019-04-29 10:23
 */
abstract class DataBaseHelper {

    var pool: Pool? = null

    val connection: Connection
        @Throws(TimeoutException::class)
        get() {
            if (pool != null)
                return pool!!.getConnection()
            throw PoolNotSetException("使用DataBaseHelper之前必须显示调用init方法进行初始化")
        }

    /**
     * 设置数据库助手的实现类
     * @param helper
     */
    @Synchronized
    fun setDataBaseImpl(helper: DataBaseHelper) {
        INSTANCE = helper
        if (helper.pool == null)
            helper.pool = STATIC_POOL
    }

    abstract fun query(sql: String, vararg params: Any?, c:Connection?): List<HashMap<String,SQLBean>>

    abstract fun update(sql: String, vararg params: Any?, c:Connection?): Int

    abstract fun updateWithGenerateKey(sql:String,generateKey: Array<Int>,vararg params: Any?, c:Connection?):Int

    companion object {

        val logger = LoggerFactory.getLogger(DataBaseHelper.javaClass)

        @JvmStatic
        private var INSTANCE: DataBaseHelper? = null

        @JvmStatic
        private var STATIC_POOL: Pool? = null

        @JvmStatic
        fun init(pool: Pool) {
            DataBaseHelper.STATIC_POOL = pool
        }

        @JvmStatic
        val instance: DataBaseHelper
            get() {
                if (INSTANCE == null) {
                    synchronized(DataBaseHelper::class.java) {
                        if (INSTANCE == null) {
                            if (STATIC_POOL == null)
                                STATIC_POOL = Pool.getInstance()
                            if (STATIC_POOL == null)
                                throw PoolNotSetException("使用DataBaseHelper之前必须对Pool进行初始化")
                            INSTANCE = NormalDataBaseHelperImpl()
                            INSTANCE!!.pool = STATIC_POOL
                        }
                    }
                }
                return INSTANCE!!
            }

        /**
         * 查询
         * @param sql SQL语句
         * @param params 参数
         * @return 查询结果
         */
        @JvmOverloads
        @JvmStatic
        fun QUERY(sql: String, vararg params: Any?,c:Connection? = null): List<HashMap<String, SQLBean>> {
            return instance.query(sql, *params,c = c)
        }


        /**
         * 更新数据
         * @param sql sql语句
         * @param params 参数
         * @return 影响记录条数
         */
        @JvmOverloads
        @JvmStatic
        fun UPDATE(sql: String,vararg params: Any?,c:Connection? = null): Int {
            return instance.update(sql, *params,c = c)
        }

        @JvmOverloads
        @JvmStatic
        fun UPDATEWITHGENERATEKEY(sql:String,generateKey: Array<Int>,vararg params: Any?,c:Connection? = null):Int{
            return instance.updateWithGenerateKey(sql,generateKey,*params,c = c)
        }

        @JvmStatic
        fun TRANSACTION(f:(conn: Connection)->Unit){

            with(instance.connection)
            {
                autoCommit = false
                try {
                    f(this)
                    commit()
                    logger.info("事务提交成功")
                }catch (e:Exception)
                {
                    e.printStackTrace()
                    rollback()
                    logger.error("事务发生错误,回滚:${e.message}")
                }
            }
        }
    }

}
