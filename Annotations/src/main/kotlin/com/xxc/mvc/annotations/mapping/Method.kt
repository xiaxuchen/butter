package com.xxc.mvc.annotations.mapping

/**
 * @class Method
 * @author 夏旭晨
 * @version 1.0.0
 * @Description 请求方式
 * @createTime 2019-05-27 10:40
 */
enum class Method {
    POST,GET,PUT,PATCH,DELETE,Any;

    companion object {
        @Throws(Exception::class)
        fun valueOf(annotation: Annotation):Method{
            when(annotation){
                is PostMapping -> return POST
                is GetMapping -> return GET
                is PutMapping -> return PUT
                is DeleteMapping -> return DELETE
                is Mapping -> return annotation.methods[0]
            }
            throw Exception("注解不匹配")
        }

        fun valuesOf(annotation: Annotation):Array<Method>{
            when(annotation){
                is PostMapping -> return arrayOf(POST)
                is GetMapping -> return arrayOf(GET)
                is PutMapping -> return arrayOf(PUT)
                is DeleteMapping -> return arrayOf(DELETE)
                is Mapping -> return annotation.methods
            }
            return arrayOf()
        }
    }
}