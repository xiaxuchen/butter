package com.xxc.better.sql

import com.xxc.better.sql.pool.Pool
import java.sql.Connection

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class RecycleableConnection
 * @Description
 * @createTime 2019-04-23 13:52
 */
class RecycleableConnection(private val delegate: Connection,
                            private val pool: Pool): Connection by delegate{

    override fun close() {
        pool.recycle(this)
    }

    override fun isClosed(): Boolean {
        return false
    }

    fun destory(){
        delegate.close()
    }
}
