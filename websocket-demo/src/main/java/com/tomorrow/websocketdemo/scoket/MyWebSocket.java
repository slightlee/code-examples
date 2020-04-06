package com.tomorrow.websocketdemo.scoket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *
 * @ServerEndpoint(value = "/wsdemo") 前端通过此 URI 和后端交互，建立连接
 *
 * @Component 将此类交给 spring 管理
 *
 * @OnOpen websocket 建立连接的注解，前端触发上面 URI 时会进入此注解标注的方法
 *
 * @OnClose 顾名思义关闭连接，销毁 session
 *
 * @OnMessage 收到前端传来的消息后执行的方法
 *
 * @Author Tomorrow
 * @Date 2020/4/6 11:03 上午
 * @Version V1.0
 */
@ServerEndpoint(value = "/wsdemo")
@Component
public class MyWebSocket {

    private static int onlineCount = 0;

    private static CopyOnWriteArraySet<MyWebSocket> webSockets = new CopyOnWriteArraySet<MyWebSocket>();

    private Session session;


    @OnOpen
    public void OnOpen(Session session){
        this.session = session;
        webSockets.add(this);
        addOnlineCount();
        System.out.println("有新连接加入！当前在线人数为:" + getOnlineCount());

        try {
            sendMessage("连接已建立成功.");
        } catch (Exception e) {
            System.out.println("IO异常");
        }
    }

    @OnClose
    public void onClose(){
        webSockets.remove(this);
        subOnlineCount();
        System.out.println("有一连接关闭！当前在线人数为:" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message,Session session){
        System.out.println("来自客户端的消息:" + message);
    }

    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount ++ ;
    }

    private static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount -- ;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public static CopyOnWriteArraySet<MyWebSocket> getWebSockets() {
        return webSockets;
    }

    public static void setWebSockets(CopyOnWriteArraySet<MyWebSocket> webSockets) {
        MyWebSocket.webSockets = webSockets;
    }
}
