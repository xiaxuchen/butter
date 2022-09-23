package com.xxc.util.extensions.io

import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

/**
 * @class InputStream
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-13 2:01
 */
/**
 * 将输入流中的内容输出到指定输出流，并会自动关闭输入输出流
 */
fun InputStream.write(out:OutputStream):Boolean{
    var len: Int
    val bytes = ByteArray(1024)
    len = read(bytes)
    try {
        while( len != -1)
        {
            out.write(bytes,0,len)
            len = read(bytes)
        }
    }catch (e:Exception)
    {
        e.printStackTrace()
        return false
    }finally {
        try {
            close()
            out.close()
        }catch (e:Exception)
        {
            e.printStackTrace()
        }
    }
    return true
}

/**
 * 将输入流中的内容输出到指定文件，并会自动关闭
 */
fun InputStream.write(file:File):Boolean{
    return write(FileOutputStream(file))
}