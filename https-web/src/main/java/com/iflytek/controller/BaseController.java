package com.iflytek.controller;

import com.google.gson.Gson;
import com.iflytek.common.AppConstant;
import com.iflytek.model.ResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xfgeng on 2018/7/8.
 */
public class BaseController {
    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected void doResponse(HttpServletResponse response, String str){
        response.setContentType(AppConstant.CONTENT_TYPE);
        try {
            PrintWriter writer = response.getWriter();
            writer.write(str);
            writer.flush();
        } catch (IOException e) {
            logger.error("doResponse error:",e);
        }
    }

    protected void doSuccess(HttpServletResponse response, Object data){
        ResultDto result = new ResultDto(AppConstant.SUCCESS_CODE,AppConstant.SUCCESS_MSG,data);
        String jsonStr = new Gson().toJson(result);
        this.doResponse(response,jsonStr);
    }



}
