package com.cetc.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 第五种：headers模式（消息头模式）
 */
@Configuration
public class HeadersExchangeConfiguration {

    @Bean
    public Queue queue7() {
        return new Queue("queue7", false);
    }

    @Bean
    public Queue queue8() {
        return new Queue("queue8", false);
    }

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange("headers", false, false);
    }

    /**
     * 确认header是否存在
     * @return
     */
    @Bean
    public Binding binding5() {
        return BindingBuilder.bind(queue7()).to(headersExchange()).where("header").exists();
    }

    @Bean
    public Binding binding6() {
        return BindingBuilder.bind(queue8()).to(headersExchange()).where("header").exists();
    }
}
