package com.example.websocketdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
/**
 * @ProjectName: webSocketdemo
 * @Package: com.example.websocketdemo.config
 * @ClassName: WebSocketConfig
 * @Author: hejie
 * @Description:
 * @Date: 2021/8/19 11:47 下午
 * @Version: 1.0
 */

@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
