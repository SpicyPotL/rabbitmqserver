package com.shul.rabbitmqserver.rabbit;/*
package com.chinaoly.apiservice.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiverMessage {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.apiTest_A)
    public void processApiTestA(String content) {
        logger.info("接收处理队列apiTest_A当中的消息： " + content);
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.apiTest_B)
    public void processApiTestB(String content) {
        logger.info("接收处理队列apiTest_B当中的消息： " + content);
    }
}
*/
