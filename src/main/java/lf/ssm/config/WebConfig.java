package lf.ssm.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lf.ssm.interceptor.LoginIntercepter;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Classname IndexViewConfig
 * @Date 2019/4/15 15:17
 * @Created by liufeng
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {  //配置映射
       // registry.addViewController( "/" ).setViewName( "index" ); //如果使用模板thymeleaf引擎则不要写
//        registry.addViewController( "/index" ).setViewName( "" );
//        registry.addViewController( "/login" ).setViewName( "login" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) { //添加拦截器
        //静态资源 css js img 已经做好了静态资源映射
        registry.addInterceptor(new LoginIntercepter()).addPathPatterns("/**").
                excludePathPatterns("/index","/","login","/index.html");
        super.addInterceptors(registry);
    }

    //防止id转换为String时丢失精度
    @Bean("jackson2ObjectMapperBuilderCustomizer")
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        Jackson2ObjectMapperBuilderCustomizer customizer = new Jackson2ObjectMapperBuilderCustomizer() {
            @Override
            public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
                jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance)
                        .serializerByType(Long.TYPE, ToStringSerializer.instance);
            }
        };
        return customizer;
    }
}
