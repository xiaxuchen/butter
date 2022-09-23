package com.xxc.util.scan.util

import java.net.URL

object StringUtil {
    /**
     * "file:/home/whf/cn/fh" -> "/home/whf/cn/fh"
     * "jar:file:/home/whf/foo.jar!cn/fh" -> "/home/whf/foo.jar"
     */
    fun getRootPath(url: URL): String {
        val fileUrl = url.file.replace("%20".toRegex(), " ")
        val pos = fileUrl.indexOf('!')

        return if (-1 == pos) {
            fileUrl
        } else fileUrl.substring(5, pos)

    }

    /**
     * "cn.fh.lightning" -> "cn/fh/lightning"
     * @param name
     * @return
     */
    fun dotToSplash(name: String): String {
        return "/"+name.replace("\\.".toRegex(), "/")
    }

    /**
     * "Apple.class" -> "Apple"
     */
    fun trimExtension(name: String): String {
        val pos = name.indexOf('.')
        return if (-1 != pos) {
            name.substring(0, pos)
        } else name

    }

    /**
     * /application/home -> /home
     * @param uri
     * @return
     */
    fun trimURI(uri: String): String {
        val trimmed = uri.substring(1)
        val splashIndex = trimmed.indexOf('/')

        return trimmed.substring(splashIndex)
    }
}