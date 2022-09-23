package com.xxc.mvc.file

import com.xxc.mvc.web.util.PathUtil
import com.xxc.util.extensions.io.write
import java.io.File
import java.io.InputStream
import java.util.*

/**
 * @class MultiFile
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-06 10:56
 */
class MultiFile {

    var name:String = ""

    var uploadName:String = ""

    var inputStream:InputStream? = null

    var size:Long = 0

    var type:String = ""

    /**
     * 使用hash打散存储
     */
    fun hashScatterWrite(p:String = "/"):Pair<Boolean,String>{
        val path = p+if(!p.endsWith("/"))
            "/"
        else
            ""
        if(inputStream == null)
            return Pair(false,"")
        val a = name.hashCode()
        var bin = Integer.toBinaryString(a)
        //位数 32位
        bin = "0000000000000000000000000000000$bin"
        bin = bin.substring(bin.length - 32)
        //0xf表示16进制的15，二进制是1111，& a 可以取a的最后一位 1010
        val b = a and 0xf
        bin = Integer.toBinaryString(b)//4的二进制 0100
        println(Integer.toHexString(b))
        bin = "0000000000000000000000000000000$bin"
        bin = bin.substring(bin.length - 32)

        //转换成16进制
        val first = Integer.toHexString(b)


        val c = a shr 4 and 0xf
        bin = Integer.toBinaryString(c)
        bin = "0000000000000000000000000000000$bin"
        bin = bin.substring(bin.length - 32)

        val second = Integer.toHexString(c)
        val newPath = "${PathUtil.getPath(path)}/$first/$second"
        val parent = File(newPath)
        if(!parent.exists())
            parent.mkdirs()
        val fName = "${UUID.randomUUID()}${name.substring(name.lastIndexOf("."))}"
        val file = File(newPath,fName)
        try {
            file.createNewFile()
            file.setWritable(true)
            inputStream!!.write(file)
        }catch (e:Exception)
        {
            e.printStackTrace()
            return Pair(false,path)
        }
        return Pair(true,"$path$first/$second/$fName")
    }
}