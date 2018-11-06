package com.iflytek.shield.https.ssl;

import com.iflytek.shield.common.AppConstant;
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
            inStream = new FileInputStream(AppConstant.HTTPS_KEYS_TORE_PATH);
        } catch (FileNotFoundException e) {
            logger.error("读取密钥文件失败", e);
        }
        return inStream;
    }

    /**
     * 读取密钥
     * @date 2012-9-11
     * @version V1.0.0
     * @return InputStream
     */
    public static InputStream getTrutsStoreStream() {
        InputStream inStream = null;
        try {
            inStream = new FileInputStream(AppConstant.HTTPS_KEYS_TORE_PATH);
        } catch (FileNotFoundException e) {
            logger.error("读取密钥文件失败", e);
        }
        return inStream;
    }

    /**
     * 获取密钥密码(证书别名密码) (用于创建KeyStore)
     * @date 2012-9-11
     * @version V1.0.0
     * @return char[]
     */
    public static char[] getKeyStorePassword() {
        return AppConstant.HTTPS_KEY_STORE_PWD.toCharArray();
    }

}
