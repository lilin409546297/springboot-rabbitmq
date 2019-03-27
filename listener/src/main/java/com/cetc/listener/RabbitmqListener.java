package com.cetc.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitmqListener {

    //1.默认队列
    @RabbitListener(queues = "queue")
    public void queueDouble1(String text, Channel channel, Message message) throws IOException {
        System.out.println("queueDouble1:" + text);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = "queue")
    public void queueDouble2(String text, Channel channel, Message message) throws IOException {
        System.out.println("queueDouble2:" + text);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    //2.主题队列
    @RabbitListener(queues = "queue1")
    public void queue1(String text, Channel channel, Message message) throws IOException {
        System.out.println("queue1:" + text);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = "queue2")
    public void queue2(String text, Channel channel, Message message) throws IOException {
        System.out.println("queue2:" + text);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    //3.直连队列
    @RabbitListener(queues = "queue3")
    public void queue3(String text, Channel channel, Message message) throws IOException {
        System.out.println("queue3:" + text);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = "queue4")
    public void queue4(String text, Channel channel, Message message) throws IOException {
        System.out.println("queue4:" + text);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    //4.广播队列
    @RabbitListener(queues = "queue5")
    public void queue5(String text, Channel channel, Message message) throws IOException {
        System.out.println("queue5:" + text);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = "queue6")
    public void queue6(String text, Channel channel, Message message) throws IOException {
        System.out.println("queue6:" + text);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    //5.消息头队列
    @RabbitListener(queues = "queue7")
    public void queue7(String text, Channel channel, Message message) throws IOException {
        System.out.println("queue7:" + text);
        System.out.println("header7:" + message.getMessageProperties().getHeaders().get("header"));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = "queue8")
    public void queue8(String text, Channel channel, Message message) throws IOException {
        System.out.println("queue8:" + text);
        System.out.println("header8:" + message.getMessageProperties().getHeaders().get("header"));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
