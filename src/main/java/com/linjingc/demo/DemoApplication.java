package com.linjingc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@SpringBootApplication
@EnableJms//这个是jms 注解
public class DemoApplication {

	/**
	 * 开启接收top请求
	 *  将pubSubDomain设置成了true，表示该Listener负责处理Topic
	 * 默认是 Queue
	 */
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory) {
		DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
		bean.setPubSubDomain(true);
		bean.setConnectionFactory(activeMQConnectionFactory);
		return bean;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
