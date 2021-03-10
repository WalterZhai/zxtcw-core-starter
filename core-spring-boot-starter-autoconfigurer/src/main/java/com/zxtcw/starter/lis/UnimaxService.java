package com.zxtcw.starter.lis;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import com.zxtcw.starter.Impl.UnimaxImpl;
import com.zxtcw.starter.exception.UnimaxException;
import com.zxtcw.starter.release.Lis;
import com.zxtcw.starter.utils.ApplicationContextGetBeanHelper;
import com.zxtcw.starter.utils.LocalMac;
import org.apache.coyote.http11.Http11NioProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

/**
 * @author Walter(翟笑天)
 * @Date 2021/3/9
 */
@Service
public class UnimaxService implements UnimaxImpl {

    static final Logger logger = LoggerFactory.getLogger(UnimaxService.class);

    @Override
    public Lis releaseLis() {
        Lis lis = new Lis();
        try {
            File file =new File(".","license/zxtcw_lis");
            if(file==null){
                throw new UnimaxException("License file not found!");
            }
            BufferedReader reader = null;
            StringBuffer sbf = new StringBuffer();
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();

            logger.info(sbf.toString());

            String result = Base64.decodeStr(Base64.decodeStr(Base64.decodeStr(sbf.toString())));


            String[] params = result.split(";");

            Integer isDev = Integer.parseInt(params[0]);

            if(isDev==1){
                if(LisConstant.PROD_PUBLIC_KEY.equals(params[1])){
                    if(LocalMac.validMac(params[2])){
                        if(LisConstant.AUTOGRAPH.equals(params[3])){
                            if(LocalMac.validDate(params[4],params[5])){
                                lis = new Lis("prod", DateUtil.parse(params[4]),DateUtil.parse(params[5]),Integer.parseInt(params[6]));
                                setConcurrency(lis.getConcurrency());
                            }else{
                                throw new UnimaxException("License validation failed!");
                            }
                        }else{
                            throw new UnimaxException("License validation failed!");
                        }
                    }else{
                        throw new UnimaxException("License validation failed! Mac failed!");
                    }
                }else{
                    throw new UnimaxException("License validation failed!");
                }
            }else if(isDev==0){
                if(LisConstant.DEV_PUBLIC_KEY.equals(params[1])){
                    if(LocalMac.validMac(params[2])){
                        if(LisConstant.AUTOGRAPH.equals(params[3])){
                            if(LocalMac.validDate(params[4],params[5])){
                                lis = new Lis("dev", DateUtil.parse(params[4]),DateUtil.parse(params[5]),Integer.parseInt(params[6]));
                                setConcurrency(lis.getConcurrency());
                            }else{
                                throw new UnimaxException("License validation failed!");
                            }
                        }else{
                            throw new UnimaxException("License validation failed!");
                        }
                    }else{
                        throw new UnimaxException("License validation failed! Mac failed!");
                    }
                }else{
                    throw new UnimaxException("License validation failed!");
                }
            }else{
                throw new UnimaxException("License validation failed!");
            }

        }catch (IOException e){
            logger.info(e.getMessage());
            System.exit(0);
        }
        return lis;
    }


    public void setConcurrency(Integer concurrency){
        TomcatServletWebServerFactory factory = (TomcatServletWebServerFactory) ApplicationContextGetBeanHelper.getBean("tomcatServletWebServerFactory");
        factory.addConnectorCustomizers(connector -> {
            Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
            protocol.setMaxThreads(200);
            logger.info("Concurrency " + concurrency);
            protocol.setMaxConnections(concurrency);
        });
    }


}
