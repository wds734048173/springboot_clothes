package org.lanqiao.clothes.config;

import org.lanqiao.clothes.interceptors.LoginInterceptor;
import org.lanqiao.clothes.interceptors.SaleLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: WDS
 * @Date: 2019/1/11 15:00
 * @Description:
 */
@Configuration
public class DefaultConfig implements WebMvcConfigurer {
    //视图转化器配置
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/manager").setViewName("/manager/index");
        registry.addViewController("/manager/").setViewName("/manager/index");
        registry.addViewController("/manager/toGoodsClassList").setViewName("/manager/goodsClassList");
        registry.addViewController("/manager/userInfo").setViewName("/manager/userInfo");

        registry.addViewController("/sale").setViewName("/sale/index");
        //跳转到登录页面
        registry.addViewController("/sale/login").setViewName("/sale/login");
        //跳转到修改密码页面
        registry.addViewController("/sale/salePwd").setViewName("/sale/salepwd");
        //跳转到注册页面
        registry.addViewController("/sale/cusRegister").setViewName("/sale/cusRegister");
        registry.addViewController("/sale/personalDetalis").setViewName("/sale/personalDetalis");
    }

    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/manager/*","/manager")
                .excludePathPatterns("/manager/login","/manager/register","/druid/*");
        registry.addInterceptor(new SaleLoginInterceptor())
                .addPathPatterns("/sale/addToCar","/sale/personal","/sale/MyOrder","/sale/shoppingCar","/sale/payOrder");
//                .excludePathPatterns("/sale","/sale/login","/sale/saleLogin","/sale/cusRegister","/sale/cusRegister1","/sale/cusregister","/sale/index");
    }

    //日期格式化
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter("yyyy-MM-dd"));
    }

    //登录页面做国际化
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
