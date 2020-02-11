package com.shul.rabbitmqserver.rabbitmq;

import com.shul.rabbitmqserver.configurer.RabbitConfig;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ReceiverMessage {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SenderMessage senderMessage;

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.QUEUE_A)
    public void processA(byte[] body) throws InterruptedException {
        String content = new String(body);
        System.out.println(content);
        if (StringUtils.isNotEmpty(content)){
            Thread.sleep(2000);
            senderMessage.sendApiTestA("接收处理队列A当中的消息： " + content+"；现在返回消息");

        }
        logger.info("接收处理队列A当中的消息： " + content);
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.QUEUE_B)
    public void processB(byte[] body) throws InterruptedException {
        String content = new String(body);
        System.out.println(content);
        if (StringUtils.isNotEmpty(content)){
            Thread.sleep(2000);
            senderMessage.sendApiTestB("接收处理队列B当中的消息： " + content+"；我要提醒你一下");

        }
        logger.info("接收处理队列B当中的消息： " + content);
    }
}
