package com.shul.rabbitmqserver.rabbitmq;

import com.shul.rabbitmqserver.configurer.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class SenderMessage implements RabbitTemplate.ConfirmCallback {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /*
    * 由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
    * 构造方法注入rabbitTemplate
    * */
    private RabbitTemplate rabbitTemplate;

    public SenderMessage(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
    }

    public void sendMsg(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
//        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A, RabbitConfig.ROUTINGKEY_A, content, correlationId);
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_A, content);
    }

    public void sendApiTestA(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
//        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A, RabbitConfig.apiTestROUTINGKEY_A, content, correlationId);
        rabbitTemplate.convertAndSend(RabbitConfig.apiTest_A, content);
    }

    public void sendApiTestB(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
//        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A, RabbitConfig.apiTestROUTINGKEY_B, content, correlationId);
        rabbitTemplate.convertAndSend(RabbitConfig.apiTest_B, content);
    }
    /*
    * 回调
    * */
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        logger.info(" 回调id:" + correlationData);
        if (b) {
            logger.info("消息成功消费");
        } else {
            logger.info("消息消费失败:" + s);
        }
    }
}
