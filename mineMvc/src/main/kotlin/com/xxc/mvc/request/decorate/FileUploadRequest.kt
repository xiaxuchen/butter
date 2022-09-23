package com.xxc.mvc.request.decorate

import com.xxc.mvc.file.MultiFile
import com.xxc.util.logger.factory.LoggerFactory
import org.apache.commons.fileupload.FileItem
import org.apache.commons.fileupload.disk.DiskFileItemFactory
import org.apache.commons.fileupload.servlet.ServletFileUpload
import java.util.*
import javax.servlet.http.HttpServletRequest
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/**
 * @class FileUploadRequest
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-04 17:40
 */
class FileUploadRequest(private val request: HttpServletRequest):HttpServletRequest by request{

    val logger = LoggerFactory.getLogger(FileUploadRequest::class.java)

    private var parameters:HashMap<String,Array<String>> = HashMap()

    private var outsMap:HashMap<String,Array<MultiFile>> = HashMap()

    private var isFileUpload:Boolean = false

    init {
        if (this.contentType != null && this.contentType.contains("multipart/form-data")) {
            isFileUpload = true
            val factory = DiskFileItemFactory()
            //2、创建一个文件上传解析器
            val upload = ServletFileUpload(factory)
            parse(upload.parseParameterMap(request))
        }
    }

    override fun getParameter(name: String?): String? {
        if (isFileUpload) {
            if(parameters[name] == null || parameters[name]!!.isEmpty())
            {
                return null
            }
            return parameters[name]!![0]
        }
        return request.getParameter(name)
    }

    override fun getParameterMap(): MutableMap<String, Array<String>> {
        if(isFileUpload)
            return parameters
        return request.parameterMap
    }

    override fun getParameterNames(): Enumeration<String> {
        if(isFileUpload)
            return object :Enumeration<String>{
                override fun hasMoreElements(): Boolean {
                    return parameters.keys.iterator().hasNext()
                }

                override fun nextElement(): String {
                    return parameters.keys.iterator().next()
                }

            }
        return request.parameterNames
    }

    override fun getParameterValues(name: String?): Array<String>? {
        if(name == null)
            return null
        if(isFileUpload)
        {
            return parameters[name]
        }
        return request.getParameterValues(name)
    }

    private fun parse(items:Map<String,List<FileItem>>)
    {
        items.forEach{ (key,list)->
            val parameters:ArrayList<String> = ArrayList()
            val outs:ArrayList<MultiFile> = ArrayList()
            list.forEach{
                if(it.isFormField)
                    parameters.add(it.getString("UTF-8"))
                else
                {
                    val multiFile = MultiFile()
                    multiFile.uploadName = it.name
                    multiFile.name = it.name.substring(maxOf(it.name.lastIndexOf('/'),
                            it.name.lastIndexOf('\\'),0))
                    multiFile.inputStream = it.inputStream
                    multiFile.size = it.size
                    multiFile.type = it.contentType
                    outs.add(multiFile)
                }
            }
            if(!parameters.isEmpty())
                this.parameters[key] = parameters.toTypedArray()
            if(!outs.isEmpty())
                this.outsMap[key] = outs.toTypedArray()
        }
        println(parameters)
    }

    fun getFile(name:String?):MultiFile?{
        if(name == null)
            return null
        println(outsMap.toString() + "###")
        logger.info(outsMap)
        return outsMap[name]?.toList()?.get(0)
    }

    /**
     * 获取同一个字段的多个文件
     */
    fun getFiles(name: String?):List<MultiFile>?
    {
        if(name == null)
            return null
        return outsMap[name]?.toList()
    }
}