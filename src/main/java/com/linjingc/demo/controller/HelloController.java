package com.linjingc.demo.controller;

import com.linjingc.demo.test.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTempQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;

/**
 * @author cxc
 * @date 2018/10/17 16:09
 */
@RestController
@Slf4j
public class HelloController {

    @Autowired
    private Producer producer;

    @RequestMapping("/")
    public String index() {
        return "看这里这里是activctiveMq主页";
    }

    /**
     * 发送一个queue请求
     */
    @RequestMapping("/hello")
    public String hello() {
        Destination destination = new ActiveMQQueue("mytest.queue");

        producer.sendMessage(destination, "queue看不到我");
        return "发送一个请求";
    }

    /**
     * 发送一个Topic请求
     */
    @RequestMapping("/hello1")
    public String hello1() {
        Destination destination = new ActiveMQTopic("mytest.topic");

        producer.sendMessage(destination, "queue请求看不到我");
        return "发送一个请求";
    }


    //接收消息的话 就直接使用监听器
    //test1 监听top
    @JmsListener(destination = "mytest.topic", containerFactory = "jmsListenerContainerTopic")   //这个注解是监听信息 主题  名称
    public void receiveTop(String text) {
        log.info("嘿嘿 小美接收到请求了   -->" + text);
    }

    @JmsListener(destination = "mytest.topic", containerFactory = "jmsListenerContainerTopic")   //这个注解是监听信息 主题  名称
    public void receiveTop1(String text) {
        log.info("嘿嘿 小明接收到请求了   -->" + text);
    }

    /**
     * 接收sendto()的队列
     */
    @JmsListener(destination = "out.queue")
    public void receiveTop2(String text) {
        log.info("嘿嘿 看看你返回给我啥请求了   -->" + text);
    }


    /**
     * 发送一个临时queue请求
     */
    @RequestMapping("/hello3")
    public String hello3() throws InterruptedException {
        Destination destination = new ActiveMQTempQueue("mytest.queue1");
        producer.sendMessage(destination, "临时queue请求看不到我");
        Thread.sleep(3000);
        return "发送一个请求";
    }

    /**
     * 接收sendto()的队列
     */
    @JmsListener(destination = "out.queue1")
    public void receiveQueue(String text) {
        log.info("嘿嘿 看看你返回给我啥请求了   -->" + text);
    }

}
