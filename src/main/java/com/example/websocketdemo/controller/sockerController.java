package com.example.websocketdemo.controller;

import com.example.websocketdemo.config.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: webSocketdemo
 * @Package: com.example.websocketdemo.controller
 * @ClassName: sockerController
 * @Author: hejie
 * @Description:
 * @Date: 2021/8/20 12:19 上午
 * @Version: 1.0
 */
@RestController
public class sockerController {

    @Autowired
    WebSocketServer webSocketServer;


    @GetMapping("/send")
        void   send(){
      webSocketServer.send("123","hhhhh");
    }





}
