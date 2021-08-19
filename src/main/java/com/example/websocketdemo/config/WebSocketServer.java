package com.example.websocketdemo.config;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ProjectName: webSocketdemo
 * @Package: com.example.websocketdemo.config
 * @ClassName: WebSocketServer
 * @Author: hejie
 * @Description:
 * @Date: 2021/8/19 11:52 下午
 * @Version: 1.0
 */
@Component
@ServerEndpoint(value = "/webSocket/{UserCode}")
public class WebSocketServer {

    // 在多线程访问的时候，使用线程安全的ConCurrentHashMap对象    usercode 与 session  对应
    private static ConcurrentHashMap<String, Session> connections = new ConcurrentHashMap<>();

    /**
     * 打开连接
     * @param session
     * @param UserCode
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("UserCode") String UserCode) {
        System.out.println("=====用户Id=====" + UserCode);
        // 接收到客户端的请求，可以做一些其他业务逻辑处理，比如可以把该IP存储到数据库
        // 避免当前服务断开后，与客户端服务失去连接
        // 这时就可以使用到预加载处理，项目当中自定义的MyApplicationRunner类
        connections.put(UserCode, session);
    }

    /**
     * 接收消息
     * @param text
     */
    @OnMessage
    public void onMessage(String text,Session session) {
       connections.entrySet().forEach(s->
       {
           if (s.getValue().equals(session)) {
               System.out.println(s.getKey());
           }
       });
    }

    /**
     * 异常处理
     * @param throwable
     */
    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    /**
     * 关闭连接
     * @param UserCode
     */
    @OnClose
    public void onClosing(@PathParam("UserCode")  String UserCode) throws IOException {
        connections.remove(UserCode);
    }

    /**
     * 根据IP发送消息
     * @param UserCode
     * @param text
     */
    public void send(String UserCode, String text) {
        try {
            Session session = connections.get(UserCode);
            if (session != null && session.isOpen()) {
                session.getAsyncRemote().sendText(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 遍历群发消息
     * @param text
     */
    public void send(String text) {
        for (ConcurrentHashMap.Entry<String, Session> entry : connections.entrySet()) {
            send(entry.getKey(), text);
        }
    }

}
