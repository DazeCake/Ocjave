package com.dazecake.ocjave.channel;
import io.micrometer.common.util.StringUtils;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


@Component
@ServerEndpoint("/wsserver/{id}")
public class WebSocketServer {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    private static int onlineCount = 0;
    private static final ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    private Session session;
    private String id = "";

    // 创建连接
    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        this.session = session;
        this.id = id;
        if (webSocketMap.containsKey(id)) {
            webSocketMap.remove(id);
            webSocketMap.put(id, this);
        } else {
            webSocketMap.put(id, this);
            addOnlineCount();
        }
        logger.info("WebSocket:User[" + id + "]has succeed connected！");
        try {
            sendMessage("Connect Succeed");
        } catch (IOException e) {
            logger.error("WebSocket:network error！");
        }
    }

    // 接收客户端消息
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        logger.info("WebSocket:received User[" + id + "]message：" + message);
        sendMessage("received message：" + message);
    }

    // 服务端主动推送信息
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    // 向指定客户端发送消息
    public static void sendInfo(String message, @PathParam("id") String id) throws IOException {
        if (StringUtils.isNotBlank(id) && webSocketMap.containsKey(id)) {
            webSocketMap.get(id).sendMessage(message);
        } else {
            logger.error("WebSocket:User[" + id + "]not found, send failed！");
        }
    }

    // 异常处理
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("WebSocket:User error：" + error.toString());
        error.printStackTrace();
    }

    // 客户端主动断开连接
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(id)) {
            webSocketMap.remove(id);
            subOnlineCount();
        }
        logger.info("WebSocket:User[" + id + "]had disconnected！");
    }

    // 在线数量
    public static synchronized int getOnLineCount() {
        return onlineCount;
    }

    // 增加在线数量
    public static synchronized void addOnlineCount() {
        onlineCount++;
    }

    // 减少在线数量
    public static synchronized void subOnlineCount() {
        onlineCount--;
    }
}