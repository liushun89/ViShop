package com.vishop.web.front.exception;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with vishop.
 * User : Homiss
 * Date : 2016/1/14
 * Time : 23:08
 */
public class ExceptionHandlerController extends SimpleMappingExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Object handler,
                                              Exception ex) {

        // Expose ModelAndView for chosen error view.
        String viewName = determineViewName(ex, request);
        if (viewName != null) {//JSP格式返回
            if(!(request.getHeader("accept").indexOf("application/json")>-1 || request.getHeader("X-Requested-With").indexOf("XMLHttpRequest")>-1)){//如果不是异步请求
                // Apply HTTP status code for error views, if specified.
                // Only apply it if we're processing a top-level request.
                Integer statusCode = determineStatusCode(request, viewName);
                if (statusCode != null) {
                    applyStatusCodeIfPossible(request, response, statusCode);
                    return getModelAndView(viewName, ex, request);
                }
            }else{//JSON格式返回
                Map model=new HashMap();
                if(this.logger.isDebugEnabled()){
                    model.put("debug", true);
                }//exception
                model.put("msg", ex.getMessage());
                model.put("failure", true);
                try {
                    response.getWriter().write("有异常啦!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return new ModelAndView();
            }
            return null;
        }
        else {
            return null;
        }
    }
}
