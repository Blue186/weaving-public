package com.wen.weaving.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Interceptor implements WebMvcConfigurer {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor()
//    }

    //本地可以取消？
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        配置图片路径映射
//        String path = System.getProperty("user.dir")+"/torch_server/app_server/src/main/resources/static/";
        String path = System.getProperty("user.dir")+"/static/";
        registry.addResourceHandler("/static/**").addResourceLocations("file:"+path);
    }
}
