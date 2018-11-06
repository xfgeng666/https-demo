package com.iflytek.shield;

import com.iflytek.shield.https.MyHttpsClient;

/**
 * 主方法
 * Created by xfgeng on 2018/6/5.
 */
public class HttpsMain {

    public static void main(String[] args) {
        String result = MyHttpsClient.sendGet("https://localhost:8443");
        System.out.println("result:"+result);
    }

}
