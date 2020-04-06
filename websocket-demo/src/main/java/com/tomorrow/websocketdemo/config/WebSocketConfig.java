package com.tomorrow.websocketdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Description: TODO
 * @Author Tomorrow
 * @Date 2020/4/6 10:58 上午
 * @Version V1.0
 */

@Configuration
public class WebSocketConfig {


    @Bean
    public ServerEndpointExporter serverEndpointExporter(){

        return new ServerEndpointExporter();

    }
}
