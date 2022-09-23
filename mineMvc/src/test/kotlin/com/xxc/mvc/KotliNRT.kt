package com.xxc.mvc

import com.xxc.mvc.controller.ControllerManager
import com.xxc.util.scan.impl.ClasspathPackageScanner
import org.junit.Test
import java.io.IOException

/**
 * @class KotliNRT
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-13 2:48
 */
class KotliNRT {

    @Test
    fun name() {
        try {
            println(ClasspathPackageScanner("com.xxc.mvc",ControllerManager.javaClass.classLoader).getFullyQualifiedClassNameList(true))
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}