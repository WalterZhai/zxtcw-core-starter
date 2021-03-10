package com.zxtcw.starter;

import com.zxtcw.starter.Impl.UnimaxImpl;
import com.zxtcw.starter.release.Lis;
import com.zxtcw.starter.utils.ApplicationContextGetBeanHelper;
import org.apache.coyote.http11.Http11NioProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @comment 核心自动配置
 * @author Walter(翟笑天)
 * @date 2021/3/4
 */
@Configuration
public class CoreAutoConfiguration {


    @Resource
    private UnimaxImpl unimaxImpl;

    @Bean("license")
    public Lis validLicense(){
        return unimaxImpl.releaseLis();
    }


}
