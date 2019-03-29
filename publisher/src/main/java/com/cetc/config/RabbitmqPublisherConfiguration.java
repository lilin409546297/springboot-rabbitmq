package com.cetc.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class RabbitmqPublisherConfiguration {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public RabbitTemplate rabbitTemplate() {
        //1、设置publisher-confirms为true
        //2、发布确认，只是在exchange范围
        //3、如果没有exchange,则false.如果过为true,则说明发送到exchange成功
        rabbitTemplate.setConfirmCallback((correlationData, ack, s) -> {
            if (ack) {
                System.out.println("send success");
            } else {
                System.out.println("send fail");
            }
        });
        //1、设置publisher-returns为true
        //2、如果没有发布成功，则将消息返回。当然这只是在接受消息层，不是exchange。
        rabbitTemplate.setReturnCallback((message, id, reason, exchange, routingKey) -> {
            StringBuffer buffer = new StringBuffer();
            buffer.append("----------------------------------------\n");
            buffer.append("接受消息: {0},失败!\n");
            buffer.append("消息ID: {1}\n");
            buffer.append("原因: {2}\n");
            buffer.append("exchange: {3}\n");
            buffer.append("routingKey: {4}\n");
            buffer.append("----------------------------------------");
            MessageFormat messageFormat = new MessageFormat(buffer.toString());
            String text = messageFormat.format(new Object[]{new String(message.getBody()), id, reason, exchange, routingKey});
            System.out.println(text);

        });
        return rabbitTemplate;
    }

    @Bean
    public AsyncRabbitTemplate asyncRabbitTemplate(DirectMessageListenerContainer container) {
        AsyncRabbitTemplate asyncRabbitTemplate = new AsyncRabbitTemplate(rabbitTemplate, container);
        return asyncRabbitTemplate;
    }

    @Bean
    public DirectMessageListenerContainer directMessageListenerContainer(ConnectionFactory connectionFactory) {
        DirectMessageListenerContainer container = new DirectMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("rpc");
        //这里我改成手动了，但是没有好的方式去获取channel,然后ack.所以我这里使用的自动。
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        //这里可以使用默认的执行器：SimpleAsyncTaskExecutor（但是，这里不是采用的线程池而是直接new Thread）
        container.setTaskExecutor(new ThreadPoolExecutor(5, 60, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3000)));
        return container;
    }
}
