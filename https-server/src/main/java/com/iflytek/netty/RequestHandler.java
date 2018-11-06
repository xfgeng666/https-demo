package com.iflytek.netty;

import com.google.gson.Gson;
import com.iflytek.common.AppConstant;
import com.iflytek.model.ResultDto;
import com.iflytek.model.UserInfo;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * 处理HTTP/HTTPS请求的handler
 * Created by xfgeng on 2018/7/9.
 */
public class RequestHandler extends ChannelInboundHandlerAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {

        if (msg instanceof FullHttpRequest) {
            //设置是否时https请求
            doResponseOnSuccess(ctx);
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error(cause.getMessage());
        ctx.close();
    }


    /**
     * 拦截器请求处理成功，进行成功响应
     *
     */
    private void doResponseOnSuccess(ChannelHandlerContext ctx) throws UnsupportedEncodingException {
        UserInfo user = new UserInfo();
        user.setName("xfgeng");
        user.setAge(18);
        ResultDto result = new ResultDto(AppConstant.SUCCESS_CODE,AppConstant.SUCCESS_MSG,user);
        String jsonStr = new Gson().toJson(result);
        logger.error("[doResponse jsonStr]"+jsonStr);
        FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1,
                OK, Unpooled.wrappedBuffer(jsonStr.getBytes("UTF-8")));
        fullHttpResponse.headers().set(CONTENT_TYPE, "text/plain;charset=UTF-8");
        fullHttpResponse.headers().set(CONTENT_LENGTH,fullHttpResponse.content().readableBytes());
        //输出响应
        ctx.writeAndFlush(fullHttpResponse);
    }

}