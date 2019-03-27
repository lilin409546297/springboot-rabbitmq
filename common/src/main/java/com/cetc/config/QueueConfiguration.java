package com.cetc.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 第一种：使用默认的交换机（direct模式）
 */
@Configuration
public class QueueConfiguration {

    /**
     * 声明队列：队列有五个参数（String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments）
     * name：队列名称
     * durable：持久性
     * exclusive：排他性（独立性）
     * autoDelete：自动删除
     * arguments：其他相关参数
     * @return
     */
    @Bean
    public Queue queue() {
        return new Queue("queue", false);
    }
}
