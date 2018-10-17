package com.linjingc.demo.test;

        import lombok.extern.slf4j.Slf4j;
        import org.springframework.jms.annotation.JmsListener;
        import org.springframework.messaging.handler.annotation.SendTo;
        import org.springframework.stereotype.Component;

/**
 * 创建消费者
 */
@Component
@Slf4j
public class Consumer {
    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "mytest.queue")
    @SendTo({"out.queue"})
    public String receiveQueue(String text) {
        System.out.println("Consumer收到的报文为:" + text);
        return "out.queue发送了";
    }
}