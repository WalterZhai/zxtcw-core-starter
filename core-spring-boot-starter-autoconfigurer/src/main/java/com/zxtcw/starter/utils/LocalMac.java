package com.zxtcw.starter.utils;

import cn.hutool.core.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Walter(翟笑天)
 * @Date 2021/3/9
 */
public class LocalMac {

    static final Logger logger = LoggerFactory.getLogger(LocalMac.class);

    public static List<String> getMacs(){
        List<String> list = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
            while (enumeration.hasMoreElements()) {
                StringBuffer stringBuffer = new StringBuffer();
                NetworkInterface networkInterface = enumeration.nextElement();
                if (networkInterface != null) {
                    byte[] bytes = networkInterface.getHardwareAddress();
                    if (bytes != null) {
                        for (int i = 0; i < bytes.length; i++) {
                            if (i != 0) {
                                stringBuffer.append("-");
                            }
                            int tmp = bytes[i] & 0xff; // 字节转换为整数
                            String str = Integer.toHexString(tmp);
                            if (str.length() == 1) {
                                stringBuffer.append("0" + str);
                            } else {
                                stringBuffer.append(str);
                            }
                        }
                        String mac = stringBuffer.toString().toUpperCase();
                        list.add(mac);
                    }
                }
            }

        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return list;
    }


    public static boolean validMac(String lisMac){
        boolean flag = false;
        List<String> macs = getMacs();
        for(String s : macs){
            if(lisMac.equals(s)){
                flag = true;
            }
        }
        return flag;
    }

    public static boolean validDate(String beginStr,String endStr){
        boolean flag = false;
        Date now = new Date();
        Date begin = DateUtil.parse(beginStr);
        Date end = DateUtil.parse(endStr);
        if(now.after(begin) && now.before(end)){
            flag=true;
        }
        return flag;
    }


}
