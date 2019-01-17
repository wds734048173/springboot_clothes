package org.lanqiao.clothes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootClothesApplicationTests {
    @Autowired
    private static AmqpAdmin amqpAdmin;

    //项目初始化消息通道
    @Test
    public static void createRabbitMQ(){
        //增加（increase）、减少（decrease）
        //1、创建交换机
        //新增商品库存
        amqpAdmin.declareExchange(new DirectExchange("addStock.exchange"));
        //增加商品库存
        amqpAdmin.declareExchange(new DirectExchange("upStock.exchange"));
        //减少商品库存
        amqpAdmin.declareExchange(new DirectExchange("downStock.exchange"));
        //2、创建队列
        amqpAdmin.declareQueue(new Queue("addStock.queue"));
        amqpAdmin.declareQueue(new Queue("upStock.queue"));
        amqpAdmin.declareQueue(new Queue("downStock.queue"));
        //3、绑定队列
        amqpAdmin.declareBinding(new Binding("addStock.queue", Binding.DestinationType.QUEUE,"addStock.exchange","addStock#",null));
        amqpAdmin.declareBinding(new Binding("upStock.queue", Binding.DestinationType.QUEUE,"upStock.exchange","upStock#",null));
        amqpAdmin.declareBinding(new Binding("downStock.queue", Binding.DestinationType.QUEUE,"downStock.exchange","downStock#",null));
    }

}

