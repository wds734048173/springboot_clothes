package org.lanqiao.clothes;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootClothesApplication {

   /* @Autowired
    RabbitTemplate rabbitTemplate;*/

    public static void main(String[] args) {
        SpringApplication.run(SpringbootClothesApplication.class, args);
    }
}

