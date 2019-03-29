package com.cetc.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RpcConfiguration {

    @Bean
    public Queue rpc() {
        return new Queue("rpc", false);
    }

    @Bean
    public DirectExchange rpcExchange() {
        return new DirectExchange("rpcExchange", false, false);
    }

    @Bean
    public Binding rpcBinding() {
        return BindingBuilder.bind(rpc()).to(rpcExchange()).with("rpcRoutingKey");
    }
}
