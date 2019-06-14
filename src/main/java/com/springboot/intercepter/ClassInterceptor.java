package com.springboot.intercepter;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


// 拦截器

public class ClassInterceptor implements HandlerInterceptor {

    //浏览器发出请求到 Controller层之前的处理。决定走不走 Controller, return ture 就走Controller层，反之亦然。
    //这个是最主要的方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取 请求路径
//        System.out.println("进入拦截器");
        Map<String, String[]> parameterMap = request.getParameterMap();
        boolean flag = false;
        for(Map.Entry<String, String[]> map : parameterMap.entrySet()){
            if(map.getValue().length==0){
                response.setCharacterEncoding("UTF-8");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,"请检查参数格式");
                break;
            }else if(StringUtils.isEmpty(map.getValue()[0])){
                //不添加 返回 字符串格式  显示中文时 会乱码 或者用？ 代替中文
                response.setCharacterEncoding("UTF-8");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,"参数不能为空");
                break;
            }
            flag = true;
        }


        return flag;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
