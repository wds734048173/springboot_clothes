package org.lanqiao.clothes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootClothesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootClothesApplication.class, args);
        createRabbitMQ();
    }
    //项目初始化消息通道
    public static void createRabbitMQ(){

    }

}

