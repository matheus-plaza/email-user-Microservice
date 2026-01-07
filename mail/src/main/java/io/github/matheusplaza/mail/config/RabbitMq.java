package io.github.matheusplaza.mail.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMq {

    @Value("${QUEUE_NAME}")
    private final String queueName;

    public RabbitMq(@Value("${QUEUE_NAME}") String mailQueue) {
        this.queueName = mailQueue;
    }

    @Bean
    public Queue getQueue() {
        return new Queue(queueName, true);
    }

}
