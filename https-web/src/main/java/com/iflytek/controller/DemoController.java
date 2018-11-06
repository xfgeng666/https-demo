package com.iflytek.controller;

import com.iflytek.common.AppConstant;
import com.iflytek.model.ResultDto;
import com.iflytek.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by xfgeng on 2018/7/8.
 */
@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController{
    private static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @RequestMapping("/main")
    public String main(){
        logger.debug("main start...");
        return "main";
     }

    @RequestMapping("/httpsTest")
    public void httpsTest(HttpServletResponse response){
        logger.debug("httpsTest start...");
        UserInfo user = new UserInfo();
        user.setName("xfgeng");
        user.setAge(18);
        super.doSuccess(response,user);
    }

}
