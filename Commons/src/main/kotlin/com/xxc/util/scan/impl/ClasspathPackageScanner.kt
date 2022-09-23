package com.xxc.util.scan.impl


import com.xxc.util.scan.PackageScanner
import com.xxc.util.scan.util.StringUtil
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.net.URL
import java.util.*
import java.util.jar.JarEntry
import java.util.jar.JarInputStream
import kotlin.collections.ArrayList

class ClasspathPackageScanner : PackageScanner {

    private var basePackage: String
    private var cl: ClassLoader? = null

    /**
     * 初始化
     * @param basePackage
     */
    constructor(basePackage: String) {
        this.basePackage = basePackage
        this.cl = javaClass.classLoader
    }

    constructor(basePackage: String, cl: ClassLoader) {
        this.basePackage = basePackage
        this.cl = cl
    }

    /**
     * 获取指定包下的所有字节码文件的全类名
     */
    @Throws(IOException::class)
    override fun getFullyQualifiedClassNameList(all:Boolean): List<String> {
        return if(all)
            doScan(basePackage, ArrayList())
        else
        {
            val list = ArrayList<String>()
            doScan(basePackage, ArrayList()).forEach{
                if(!it.removePrefix("$basePackage.").contains("."))
                {
                    list.add(it)
                }
            }
            list
        }
    }

    /**
     * doScan函数
     * @param basePackage
     * @param nameList
     * @return
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun doScan(basePackage: String, nameList: MutableList<String>): List<String> {
        val splashPath = StringUtil.dotToSplash(basePackage)
        val url: URL = cl?.getResource(splashPath) ?: return listOf()   //file:/D:/WorkSpace/java/ScanTest/target/classes/com/scan
        val filePath = StringUtil.getRootPath(url)
        val names: List<String>? // contains the name of the class file. e.g., Apple.class will be stored as "Apple"
        names = if (isJarFile(filePath)) {// 先判断是否是jar包，如果是jar包，通过JarInputStream产生的JarEntity去递归查询所有类
            readFromJarFile(filePath, splashPath)
        } else {
            readFromDirectory(filePath)
        }
        for (name in names!!) {
            if (isClassFile(name)) {
                nameList.add(toFullyQualifiedName(name, basePackage))
            } else {
                doScan("$basePackage.$name", nameList)
            }
        }
        return nameList
    }

    private fun toFullyQualifiedName(shortName: String, basePackage: String?): String {
        val sb = StringBuilder(basePackage!!)
        sb.append('.')
        sb.append(StringUtil.trimExtension(shortName))
        //打印出结果
        return sb.toString()
    }

    @Throws(IOException::class)
    private fun readFromJarFile(jarPath: String, splashedPackageName: String): List<String> {
        val jarIn = JarInputStream(FileInputStream(jarPath))
        var entry: JarEntry? = jarIn.nextJarEntry
        val nameList = ArrayList<String>()
        while (null != entry) {
            val name = entry.name
            if (name.startsWith(splashedPackageName) && isClassFile(name)) {
                nameList.add(name)
            }

            entry = jarIn.nextJarEntry
        }

        return nameList
    }

    private fun readFromDirectory(path: String): List<String>? {
        val file = File(path)
        val names = file.list() ?: return null
        names.forEach {
            println(it+"######")
        }
        return Arrays.asList(*names)
    }

    private fun isClassFile(name: String): Boolean {
        return name.endsWith(".class")
    }

    private fun isJarFile(name: String): Boolean {
        return name.endsWith(".jar")
    }
}