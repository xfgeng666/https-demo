package com.iflytek.shield.https.ssl;

import com.iflytek.shield.common.AppConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * Created by xfgeng on 2018/7/10.
 */
public class SslContextFactory {
    private static Logger logger = LoggerFactory.getLogger(SslContextFactory.class);

    private static SSLContext sslContext;

    public static SSLContext getSSLContext(){
        long time1=System.currentTimeMillis();
        if(sslContext==null){
            String algorithm = Security.getProperty("ssl.KeyManagerFactory.algorithm");
            if (algorithm == null) {
                algorithm = AppConstant.KEY_MANAGER_FACTORY_INSTANCE_NAME;
            }
            try {
                //决定将哪一个认证证书发送给对端服务器
                KeyManagerFactory kmf = KeyManagerFactory.getInstance(algorithm);
                kmf.init(getkeyStore(),SslKeyStore.getKeyStorePassword());
                //决定对端的认证证书是否被信任
                TrustManagerFactory tmf=TrustManagerFactory.getInstance(algorithm);
                tmf.init(getTrustStore());
                //初始化SSLContext
                sslContext = SSLContext.getInstance(AppConstant.SSL_CONTEXT_INSTANCE_NAME);
                sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());
            }catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }catch (UnrecoverableKeyException e) {
                e.printStackTrace();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
        }
        long time2=System.currentTimeMillis();
        System.out.println("SSLContext 初始化时间："+(time2-time1));
        return sslContext;
    }


    public static KeyStore getkeyStore(){
        KeyStore keySotre=null;
        try {
            keySotre = KeyStore.getInstance(AppConstant.KEY_STORE_INSTANCE_NAME);
            keySotre.load(SslKeyStore.getKeyStoreStream(), SslKeyStore.getKeyStorePassword());
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keySotre;
    }
    public static KeyStore getTrustStore() {
        KeyStore trustKeyStore=null;
        try {
            trustKeyStore=KeyStore.getInstance(AppConstant.KEY_STORE_INSTANCE_NAME);
            trustKeyStore.load(SslKeyStore.getTrutsStoreStream(),SslKeyStore.getKeyStorePassword());
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trustKeyStore;
    }

}
