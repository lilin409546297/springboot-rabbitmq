package com.cetc.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "queue")
public class RabbitmqHandlerListener {

    @RabbitHandler
    public void messageHandler(String text, Channel channel, Message message) throws IOException {
        System.out.println("queueDouble3:" + text);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
