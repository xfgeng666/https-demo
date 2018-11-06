package com.iflytek.netty.ssl;

import com.iflytek.common.AppConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManagerFactory;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.Security;

/**
 * Created by xfgeng on 2018/7/10.
 */
public class SslContextFactory {
    private static Logger logger = LoggerFactory.getLogger(SslContextFactory.class);

    /**针对于服务器端配置*/
    private static SSLContext sslContext = null;

    static {
        long time1=System.currentTimeMillis();
        String algorithm = Security.getProperty("ssl.KeyManagerFactory.algorithm");
        if (algorithm == null) {
            algorithm = AppConstant.KEY_MANAGER_FACTORY_INSTANCE_NAME;
        }
        try {
            //初始化证书库（包含服务端和信任的客户端证书）
            KeyStore ks = KeyStore.getInstance(AppConstant.KEY_STORE_INSTANCE_NAME);
            ks.load(SslKeyStore.getKeyStoreStream(), SslKeyStore.getKeyStorePassword());
            //决定将哪一个认证证书发送给对端服务器
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(algorithm);
            kmf.init(ks, SslKeyStore.getCertificatePassword());
            //决定对端的认证证书是否被信任
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(algorithm);
            tmf.init(ks) ;
            //初始化SSLContext
            sslContext = SSLContext.getInstance(AppConstant.SSL_CONTEXT_INSTANCE_NAME);
            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());
        } catch (Exception e) {
            logger.error("初始化server SSL失败", e);
        }
        long time2=System.currentTimeMillis();
        logger.debug("SSLContext 初始化时间："+(time2-time1));
    }



    public static SSLEngine createSSLEngine() {
        SSLEngine sslEngine = sslContext.createSSLEngine();
        sslEngine.setUseClientMode(false);
        sslEngine.setNeedClientAuth(true);
        return sslEngine ;
    }

}
