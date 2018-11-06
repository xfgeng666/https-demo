package com.iflytek.common;

/**
 * Created by xfgeng on 2017/9/12.
 */
public final class AppConstant {

    public final static  String SUCCESS_CODE = "000000";
    public final static  String SUCCESS_MSG = "成功";

    public final static  String CONTENT_TYPE = "text/html;charset=utf-8";

    public static final Integer HTTPS_SERVER_PORT = 8443;
    public static final String HTTPS_SERVER_KEYSTORE_PATH = "D://cert/server_test.jks";
    public static final String HTTPS_SERVER_KEYSTORE_PWD = "123456";
    public static final String HTTPS_SERVER_CERT_PWD = "123456";
    public static final String SSL_CONTEXT_INSTANCE_NAME = "TLS";
    public static final String KEY_MANAGER_FACTORY_INSTANCE_NAME = "SunX509";
    public static final String KEY_STORE_INSTANCE_NAME = "JKS";
    public static final Integer NETTY_HTTP_OBJECT_AGGREGATOR_SIZE = 104857600;
    public static final Integer NETTY_TCP_BACKLOG = 1024;

}
