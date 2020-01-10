package com.springboot.config;

import com.springboot.intercepter.ClassInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfigAdapter implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //  /index/** 表示 index 底下所有 接口都被 拦截
        String[] addPathPatterns={
                "/index/**"
        };
        // 表示 index  底下的 test 和 find/{id}/{name} 接口 不被拦截
        String[] excludePathPatterns={
                "/index/test",
                "/index/find/{id}/{name}"
        };
        // addPathPatterns 表示：添加的路径拦截
        // excludePathPatterns 表示：路径不被拦截
        registry.addInterceptor(new ClassInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);

    }
}
