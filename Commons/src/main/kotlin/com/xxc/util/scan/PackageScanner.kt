package com.xxc.util.scan

import java.io.IOException



/**
 * @class PackageScanner
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-28 20:51
 */
interface PackageScanner {
    @Throws(IOException::class)
    fun getFullyQualifiedClassNameList(all:Boolean): List<String>
}