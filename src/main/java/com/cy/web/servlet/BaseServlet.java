package com.cy.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 替换HttpServlet，根据请求的最后一段路径进行方法分发
 */
public class BaseServlet extends HttpServlet {
    @Override
    //根据请求的最后一段路径进行方法分发
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求路径
        String uri = req.getRequestURI();

        //2.获取最后一段路径
        int index = uri.lastIndexOf('/');
        String substring = uri.substring(index+1);

        //3.执行方法
        //获取字节码对象
        Class<? extends BaseServlet> aClass = this.getClass();
        //获取方法Method对象
        try {
            Method method = aClass.getMethod(substring,HttpServletRequest.class,HttpServletResponse.class);
            //执行方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
