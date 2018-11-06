package com.iflytek.netty;

import com.iflytek.common.AppConstant;
import com.iflytek.netty.ssl.SslContextFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HTTPS服务，监听给定端口的HTTPS请求
 * Created by xfgeng on 2018/7/9.
 */
public class HttpsServer implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(HttpsServer.class);

    @Override
    public void run() {
        logger.debug("https server start...");
        logger.debug("https server port:"+AppConstant.HTTPS_SERVER_PORT);
        try {
            this.start(AppConstant.HTTPS_SERVER_PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动netty监听https端口
     *
     * @param port
     * @throws Exception
     */
    public void start(int port) throws Exception {
        //netty IO主线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //netty 数据处理线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            // SSL报文处理
                            pipeline.addFirst(new SslHandler(SslContextFactory.createSSLEngine()));
                            // server端发送的是httpResponse，所以要使用HttpResponseEncoder进行编码
                            pipeline.addLast(new HttpResponseEncoder());
                            // server端接收到的是httpRequest，所以要使用HttpRequestDecoder进行解码
                            pipeline.addLast(new HttpRequestDecoder());
                            // 用于处理HTTP报文转换
                            pipeline.addLast(new HttpObjectAggregator(AppConstant.NETTY_HTTP_OBJECT_AGGREGATOR_SIZE));
                            // 用于处理大文件传输
                            pipeline.addLast(new ChunkedWriteHandler());
                            // 用于处理请求，执行请求拦截器
                            pipeline.addLast(new RequestHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG, AppConstant.NETTY_TCP_BACKLOG)//设置TCP BACKLOG参数
                    .childOption(ChannelOption.SO_KEEPALIVE, true);//设置TCP KEEPALIVE参数
            // 绑定、监听端口
            ChannelFuture f = b.bind(port).sync();
            // 成功绑定到端口之后,给channel增加一个 管道关闭的监听器并同步阻塞,直到channel关闭,线程才会往下执行,结束进程
            f.channel().closeFuture().sync();
        } finally {
            // 通道关闭时释放线程组资源
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }


}