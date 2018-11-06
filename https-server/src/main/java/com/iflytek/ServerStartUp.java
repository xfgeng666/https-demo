package com.iflytek;

import com.iflytek.netty.HttpsServer;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;

/**
 * 启动入口
 * Created by xfgeng on 2018/7/9.
 */
public class ServerStartUp {
    private static Logger logger = LoggerFactory.getLogger(ServerStartUp.class);

    public static void main(String[] args) {
        try {
            initLog();
            new Thread(new HttpsServer()).start();
        } catch (Exception e) {
            logger.error("start error ", e);
        }
    }

    /**
     * 初始化log4j设置，指定配置文件
     *
     * @throws Exception
     */
    private static void initLog() {
        String configPath = new File(ServerStartUp.class.getClassLoader().getResource("").getPath()).getPath();//开发环境
        if (configPath.endsWith("bin")) {//linux发布环境在bin目录运行
            configPath = configPath.substring(0, configPath.length() - 4);
        }
        String customizedPath = configPath  + File.separator + "config/log4j.properties";
        PropertyConfigurator.configure(customizedPath);
    }

}

