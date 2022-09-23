package com.xxc.mvc.filter

import com.xxc.mvc.web.util.PathUtil
import java.io.IOException
import javax.servlet.*

/**
 * @class EncodingFilter
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-28 17:18
 */
const val DEFAULT_ENCODING = "UTF-8"

class AEncodingFilter : Filter {

    private var encoding: String? = null

    @Throws(ServletException::class)
    override fun init(filterConfig: FilterConfig) {
        PathUtil.init(filterConfig.servletContext)
        encoding = filterConfig.getInitParameter("encoding")
        if(encoding == null)
            encoding = DEFAULT_ENCODING
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        servletRequest.characterEncoding = encoding
        servletResponse.characterEncoding = encoding
        filterChain.doFilter(servletRequest, servletResponse)
    }

    override fun destroy() {}
}
