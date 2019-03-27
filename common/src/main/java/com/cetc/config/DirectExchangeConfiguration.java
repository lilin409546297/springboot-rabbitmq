package com.cetc.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 第三种：Direct模式（直连模式，默认交换机也是这种类型）
 */
@Configuration
public class DirectExchangeConfiguration {

    @Bean
    public Queue queue3() {
        return new Queue("queue3", false);
    }

    @Bean
    public Queue queue4() {
        return new Queue("queue4", false);
    }

    /**
     * 参数和topic的交换机类型一样
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("direct", false, false);
    }

    @Bean
    public Binding binding3() {
        return BindingBuilder.bind(queue3()).to(directExchange()).with("direct.3");
    }

    @Bean
    public Binding binding4() {
        return BindingBuilder.bind(queue4()).to(directExchange()).with("direct.4");
    }
}
