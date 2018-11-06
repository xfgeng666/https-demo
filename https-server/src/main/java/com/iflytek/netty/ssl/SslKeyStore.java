package com.iflytek.netty.ssl;

import com.iflytek.common.AppConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by xfgeng on 2018/7/10.
 */
public class SslKeyStore {
    private static Logger logger = LoggerFactory.getLogger(SslKeyStore.class);

    /**
     * 读取密钥
     * @date 2012-9-11
     * @version V1.0.0
     * @return InputStream
     */
    public static InputStream getKeyStoreStream() {
        InputStream inStream = null;
        try {
            inStream = new FileInputStream(AppConstant.HTTPS_SERVER_KEYSTORE_PATH);
        } catch (FileNotFoundException e) {
            logger.error("读取密钥文件失败", e);
        }
        return inStream;
    }

    /**
     * 获取安全证书密码 (用于创建KeyManagerFactory)
     * @date 2012-9-11
     * @version V1.0.0
     * @return char[]
     */
    public static char[] getCertificatePassword() {
        return AppConstant.HTTPS_SERVER_CERT_PWD.toCharArray();
    }

    /**
     * 获取密钥密码(证书别名密码) (用于创建KeyStore)
     * @date 2012-9-11
     * @version V1.0.0
     * @return char[]
     */
    public static char[] getKeyStorePassword() {
        return AppConstant.HTTPS_SERVER_KEYSTORE_PWD.toCharArray();
    }

}
