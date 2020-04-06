package com.tomorrow.websocketdemo.timer;

import com.tomorrow.websocketdemo.scoket.MyWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Description: TODO
 * @Author Tomorrow
 * @Date 2020/4/6 11:02 上午
 * @Version V1.0
 */

@Component
@EnableScheduling
public class TimeTask {

    private static Logger logger =  LoggerFactory.getLogger(TimeTask.class);

    @Scheduled(cron = "0/1 * * * * ?")
    public void test(){
        System.err.println("*********   定时任务执行   **************");
        CopyOnWriteArraySet<MyWebSocket> webSockets = MyWebSocket.getWebSockets();
        int i=0;
        webSockets.forEach(c->{
            try {
                c.sendMessage("定时发送" + new Date().toLocaleString());
            }catch (IOException e){
                e.printStackTrace();
            }

        });

        System.err.println("/n 定时任务完成.......");

    }
}
