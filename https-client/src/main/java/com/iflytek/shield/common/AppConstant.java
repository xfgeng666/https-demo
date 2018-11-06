package com.iflytek.shield.common;

import com.iflytek.shield.utils.AppConfig;

/**
 * Created by xfgeng on 2018/6/22.
 */
public final class AppConstant {

    public static final String API_GW_APPID = AppConfig.getValue("api.gw.appId");
    public static final String API_GW_APPSECRET = AppConfig.getValue("api.gw.appSecret");
    public static final String API_GW_PUBLICKEY = AppConfig.getValue("api.gw.publicKey");
    public static final String API_GW_HOST = AppConfig.getValue("api.gw.host");
    public static final Integer API_GW_HTTPPORT = AppConfig.getValue("api.gw.httpPort",4989);
    public static final Integer API_GW_HTTPSPORT = AppConfig.getValue("api.gw.httpsPort",443);
    public static final Integer API_GW_WEBSOCKETPORT = AppConfig.getValue("api.gw.websocketPort",4999);
    public static final String API_GW_STAGE = AppConfig.getValue("api.gw.stage");

    public static final String HTTPS_KEYS_TORE_PATH = "D://cert/client_test.p12";
    public static final String HTTPS_KEY_STORE_PWD = "123456";
    public static final String SSL_CONTEXT_INSTANCE_NAME = "TLS";
    public static final String KEY_MANAGER_FACTORY_INSTANCE_NAME = "SunX509";
    public static final String KEY_STORE_INSTANCE_NAME = "PKCS12";

}
