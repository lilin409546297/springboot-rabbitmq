package com.cetc.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "rpc")
public class RpcListener {

    @RabbitHandler
    public String rpcListener(String text, Channel channel, Message message) throws IOException {
        System.out.println("rpcServer:" + text);
        MessageProperties messageProperties = message.getMessageProperties();
        channel.basicAck(messageProperties.getDeliveryTag(), false);
        return "success";
    }
}
