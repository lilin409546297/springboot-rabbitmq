package com.cetc.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 第四种：fanout模式（发布/订阅模式）
 */
@Configuration
public class FanoutExchangeConfiguration {

    @Bean
    public Queue queue5() {
        return new Queue("queue5", false);
    }

    @Bean
    public Queue queue6() {
        return new Queue("queue6", false);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout", false, false);
    }

    /**
     * 这里的绑定不需要routingKey
     * @return
     */
    @Bean
    public Binding binding5() {
        return BindingBuilder.bind(queue5()).to(fanoutExchange());
    }

    /**
     * 相比于topic,fanout只能全部发送,topic可以更具匹配规则进行
     * @return
     */
    @Bean
    public Binding binding6() {
        return BindingBuilder.bind(queue6()).to(fanoutExchange());
    }
}
